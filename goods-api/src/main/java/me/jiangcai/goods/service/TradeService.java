/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.service;

import me.jiangcai.goods.Buyer;
import me.jiangcai.goods.Goods;
import me.jiangcai.goods.TradedGoods;
import me.jiangcai.goods.exception.IllegalGoodsException;
import me.jiangcai.goods.exception.ShortageStockException;
import me.jiangcai.goods.stock.StockToken;
import me.jiangcai.goods.trade.Trade;
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
     * @param tradeSupplier
     * @param tradeSaver
     * @param tradeLoader
     * @param tradedGoodsCreator
     * @param goods              商品
     * @param count              数量
     * @param buyer              购买者
     * @param lockDuration       库存锁定时间     @return 已建立的订单     @throws IllegalGoodsException  商品已下架或者什么的
     * @throws ShortageStockException 库存不足
     */
    @Transactional
    Trade createTrade(Supplier<Trade> tradeSupplier, Function<Trade, Trade> tradeSaver, Function<Trade, Trade> tradeLoader, BiFunction<Goods, StockToken, TradedGoods> tradedGoodsCreator, Goods goods, int count, Buyer buyer, Duration lockDuration) throws IllegalGoodsException
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

    // 销售者

}
