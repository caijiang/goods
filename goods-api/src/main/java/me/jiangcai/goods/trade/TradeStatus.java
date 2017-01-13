/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.trade;

/**
 * @author CJ
 */
public enum TradeStatus {
    closed,
    /**
     * 已下单
     */
    ordered,
    /**
     * 部分支付
     */
    partialPaid,
    /**
     * 已付款
     */
    paid,
    /**
     * 已发货
     */
    sent,
    /**
     * 已确认
     */
    confirmed,
    /**
     * 异常状态:在不正确的状态被支付
     */
    payOnClosed

}
