/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.service;

import me.jiangcai.goods.Buyer;
import me.jiangcai.goods.Goods;
import me.jiangcai.goods.TradedGoods;
import me.jiangcai.goods.event.TradePayEvent;
import me.jiangcai.goods.exception.IllegalGoodsException;
import me.jiangcai.goods.exception.ShortageStockException;
import me.jiangcai.goods.stock.StockToken;
import me.jiangcai.goods.trade.Trade;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 订单也就是交易服务
 *
 * @author CJ
 */
public interface TradeService {

    /**
     * 建立订单
     * 如果超时未支付，则会被关闭
     *
     * @param tradeSupplier      订单生成器
     * @param tradeSaver         订单保存者
     * @param tradeLoader        订单载入者
     * @param tradedGoodsCreator 就某一商品而创建的在售商品处理者
     * @param goods              商品
     * @param count              数量
     * @param buyer              购买者
     * @param lockDuration       库存锁定时间
     * @return 已建立的订单
     * @throws IllegalGoodsException  商品已下架或者什么的
     * @throws ShortageStockException 库存不足
     */
    @Transactional
    Trade createTrade(Supplier<Trade> tradeSupplier, Function<Trade, Trade> tradeSaver
            , Function<Trade, Trade> tradeLoader, BiFunction<Goods, StockToken[], TradedGoods> tradedGoodsCreator
            , Goods goods, int count, Buyer buyer, Duration lockDuration) throws IllegalGoodsException
            , ShortageStockException;

    /**
     * 检查一个订单，会有以下情况
     * <ul>
     * <li>没有在指定时间内支付时，关闭订单 {@link me.jiangcai.goods.event.TradeCloseEvent} </li>
     * </ul>
     *
     * @param trade       订单
     * @param tradeLoader 订单刷新者
     * @return 新的订单信息
     */
    @Transactional
    Trade checkTrade(Trade trade, Function<Trade, Trade> tradeLoader);

    /**
     * 订单支付事件的发生
     *
     * @param event event
     */
    @Transactional
    @EventListener(TradePayEvent.class)
    void tradePayEvent(TradePayEvent event);

    // 销售者

}
