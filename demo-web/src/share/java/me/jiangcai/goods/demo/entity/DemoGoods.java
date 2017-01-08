/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.demo.entity;

import lombok.Getter;
import lombok.Setter;
import me.jiangcai.goods.Seller;
import me.jiangcai.goods.core.entity.Goods;
import me.jiangcai.goods.core.entity.GoodsSeller;
import me.jiangcai.goods.core.entity.TradeEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author CJ
 */
@Setter
@Getter
@Entity
public class DemoGoods extends Goods {

    @ManyToOne
    private GoodsSeller seller;
    @ManyToOne
    private TradeEntity owner;

    @Override
    public void setSeller(Seller seller) {
        this.seller = (GoodsSeller) seller;
    }

    @Override
    public void setOwner(me.jiangcai.goods.TradeEntity owner) {
        this.owner = (TradeEntity) owner;
    }


}
