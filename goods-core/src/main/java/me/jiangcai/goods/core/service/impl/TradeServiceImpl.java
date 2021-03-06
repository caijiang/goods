/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.core.service.impl;

import me.jiangcai.goods.Buyer;
import me.jiangcai.goods.Goods;
import me.jiangcai.goods.TradedGoods;
import me.jiangcai.goods.event.TradeCloseEvent;
import me.jiangcai.goods.event.TradeDispatchRemindEvent;
import me.jiangcai.goods.event.TradePayEvent;
import me.jiangcai.goods.exception.IllegalGoodsException;
import me.jiangcai.goods.exception.ShortageStockException;
import me.jiangcai.goods.service.TradeService;
import me.jiangcai.goods.stock.StockManageService;
import me.jiangcai.goods.stock.StockService;
import me.jiangcai.goods.stock.StockToken;
import me.jiangcai.goods.trade.Trade;
import me.jiangcai.goods.trade.TradeStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author CJ
 */
@Service
public class TradeServiceImpl implements TradeService {

    private static final Log log = LogFactory.getLog(TradeServiceImpl.class);

    @Autowired
    private StockManageService stockManageService;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private TaskScheduler tradeCloseScheduler;
    // 我们需要定时获取订单 并且进行处理

    @Override
    public Trade createTrade(Supplier<Trade> tradeSupplier, Function<Trade, Trade> tradeSaver
            , Function<Trade, Trade> tradeLoader, BiFunction<Goods, StockToken[], TradedGoods> tradedGoodsCreator, Goods goods, int count
            , Buyer buyer, Duration lockDuration) throws IllegalGoodsException
            , ShortageStockException {

        log.debug("Try createTrade for " + goods + " count:" + count + " to:" + buyer);

        if (!goods.isEnable())
            throw new IllegalGoodsException();
// 检查库存 因为库存这玩意儿可能存在不同的供应商 所以我们存在一个玩意儿 叫做库存供应商支持者
        StockService stockService = stockManageService.stockService(goods);

        Trade trade = tradeSupplier.get();
        trade.setStatus(TradeStatus.ordered);

        trade.setBuyer(buyer);

        trade.setCreatedTime(LocalDateTime.now());
        trade.setCloseTime(LocalDateTime.now().plus(lockDuration));

        final Collection<? extends StockToken> collection = stockService.lock(goods, count);

        TradedGoods tradedGoods = tradedGoodsCreator.apply(goods, collection.toArray(new StockToken[collection.size()]));
        trade.addTradedGoods(tradedGoods);

        Trade savedTrade = tradeSaver.apply(trade);

        tradeCloseScheduler.scheduleWithFixedDelay(() -> {
            log.debug("auto check trade:" + savedTrade);
            applicationContext.getBean(TradeService.class).checkTrade(savedTrade, tradeLoader);
        }, lockDuration.toMillis() + 1000L);

        return savedTrade;
    }

    @Override
    public Trade checkTrade(Trade trade, Function<Trade, Trade> tradeLoader) {
        trade = tradeLoader.apply(trade);

        if (trade.getStatus() == TradeStatus.ordered && LocalDateTime.now().isAfter(trade.getCloseTime())) {
            // 先退货
            trade.getGoods().forEach(traded -> {
                StockService stockService = stockManageService.stockService(traded);
                Stream.of(traded.toStockToken()).forEach(t -> stockService.release(traded, t));
            });
            trade.setStatus(TradeStatus.closed);
            try {
                applicationEventPublisher.publishEvent(new TradeCloseEvent(trade));
            } catch (Throwable ex) {
                log.debug("Event", ex);
            }
        }

        return trade;
    }

    @Override
    public void tradePayEvent(TradePayEvent event) {
        // 订单被支付，应该做的事情
        // 状态确认是OK的
        // 或者是这个事件被多次调度？
        Trade trade = event.getTrade();

        // 如果已支付则
        if (trade.isPaidSuccess()) {
            log.debug("Pay Event on paid trade:" + trade);
            return;
        }

        if (trade.getStatus() != TradeStatus.ordered) {
            log.debug("Pay Event on no-pay-ready trade:" + trade);
            // 本身状态并不是在等待支付的
            // 应该是无法的
            if (trade.getStatus() == TradeStatus.closed) {
                trade.setStatus(TradeStatus.payOnClosed);
            }
            return;
        }

        trade.setStatus(TradeStatus.paid);
        trade.setPaidSuccess(true);
        applicationEventPublisher.publishEvent(new TradeDispatchRemindEvent(trade));
    }
}
