/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.core.entity;

import lombok.Getter;
import lombok.Setter;
import me.jiangcai.goods.GoodsImage;
import me.jiangcai.goods.Seller;
import me.jiangcai.goods.TradeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author CJ
 */
@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
public class Goods implements me.jiangcai.goods.Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String name;
    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Override
    public Seller getSeller() {
        return null;
    }

    @Override
    public TradeEntity getOwner() {
        return null;
    }

    @Override
    public List<? extends GoodsImage> getGoodsImages() {
        return null;
    }
}
