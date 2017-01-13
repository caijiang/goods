/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.event;

import lombok.Data;
import me.jiangcai.goods.trade.Trade;

import java.io.Serializable;

/**
 * 订单支付事件，支付代码需要发起该事件，确认一笔订单已经完成支付
 *
 * @author CJ
 */
@Data
public class TradePayEvent {
    /**
     * 相关订单号
     */
    private final Trade trade;
    /**
     * 唯一支付流水号
     */
    private final Serializable serializable;
}
