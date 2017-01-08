/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.trade;

import me.jiangcai.goods.Buyer;
import me.jiangcai.goods.TradedGoods;
import me.jiangcai.goods.payment.PaymentMethod;

import java.time.LocalDateTime;
import java.util.List;
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
    Set<? extends TradedGoods> getGoods();

    /**
     * @param tradedGoods 添加下单商品
     */
    void addTradedGoods(TradedGoods tradedGoods);

    /**
     * @return 创建时间
     */
    LocalDateTime getCreatedTime();

//    Set<PayInfo> getPaySet();

    void setCreatedTime(LocalDateTime localDateTime);

    /**
     * @return 成功支付的支付信息
     */
    PayInfo getSuccessPay();
    // 还有物流

    /**
     * @return 当前正在进行的支付信息
     */
    PayInfo getCurrentPay();

    /**
     * @return 支持的支付方式
     */
    List<PaymentMethod> supportPaymentMethods();

    void setBuyer(Buyer buyer);

    /**
     * @return 即将关闭的时间
     */
    LocalDateTime getCloseTime();

    /**
     * @param localDateTime 设置即将关闭的时间
     */
    void setCloseTime(LocalDateTime localDateTime);

    TradeStatus getStatus();

    void setStatus(TradeStatus status);

}
