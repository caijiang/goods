/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.trade;

import me.jiangcai.goods.TradedGoods;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 订单
 *
 * @author CJ
 */
public interface Trade {

    /**
     * @return 订单中包括的商品
     */
    Set<TradedGoods> getGoods();

    /**
     * @return 创建时间
     */
    LocalDateTime getCreatedTime();

//    Set<PayInfo> getPaySet();

    /**
     * @return 成功支付的支付信息
     */
    PayInfo getSuccessPay();

    /**
     * @return 当前正在进行的支付信息
     */
    PayInfo getCurrentPay();
    // 还有物流
}
