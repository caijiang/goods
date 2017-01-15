/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods;

import me.jiangcai.goods.stock.StockToken;

import java.math.BigDecimal;

/**
 * 被交易的商品，或者叫做交易中的商品；这些商品也可以进入售后。
 *
 * @author CJ
 */
public interface TradedGoods extends Goods {

    /**
     * @return 数量
     */
    int getCount();

    /**
     * @return 取出仓库单号
     */
    StockToken[] toStockToken();

    /**
     * @return 小计金额
     */
    default BigDecimal getSubtotal() {
        return GoodsUtil.toBigDecimal(getPrice()).multiply(BigDecimal.valueOf(getCount()));
    }

}
