/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.event;

import lombok.Data;
import me.jiangcai.goods.trade.Trade;

/**
 * 订单关闭事件
 *
 * @author CJ
 */
@Data
public class TradeCloseEvent {
    private final Trade trade;

    public TradeCloseEvent(Trade trade) {
        this.trade = trade;
    }
}
