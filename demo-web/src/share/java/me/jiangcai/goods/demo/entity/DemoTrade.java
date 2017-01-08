/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.demo.entity;

import lombok.Getter;
import lombok.Setter;
import me.jiangcai.goods.Buyer;
import me.jiangcai.goods.TradedGoods;
import me.jiangcai.goods.payment.PaymentMethod;
import me.jiangcai.goods.trade.PayInfo;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author CJ
 */
@Setter
@Getter
@Entity
public class DemoTrade extends me.jiangcai.goods.core.entity.Trade {

    @ElementCollection
    private Set<DemoTradedGoods> tradedSet;

    @Override
    public Set<? extends TradedGoods> getGoods() {
        return tradedSet;
    }

    @Override
    public PayInfo getSuccessPay() {
        return null;
    }

    @Override
    public PayInfo getCurrentPay() {
        return null;
    }

    @Override
    public List<PaymentMethod> supportPaymentMethods() {
        return null;
    }

    @Override
    public void setBuyer(Buyer buyer) {

    }

    @Override
    public void addTradedGoods(TradedGoods tradedGoods) {
        if (tradedSet == null)
            tradedSet = new HashSet<>();
        tradedSet.add((DemoTradedGoods) tradedGoods);
    }
}
