/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.demo.entity;

import lombok.Getter;
import lombok.Setter;
import me.jiangcai.goods.demo.entity.pk.GoodsCodePK;
import me.jiangcai.goods.stock.StockToken;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 电子券类商品的电子券
 *
 * @author CJ
 */
@Entity
@Setter
@Getter
@Table(indexes = {@Index(columnList = "used")})
@IdClass(GoodsCodePK.class)
public class GoodsCode implements StockToken {
    public static final int CodeLength = 50;
    @Id
    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private DemoGoods goods;
    @Id
    @Column(length = CodeLength)
    private String code;

    @Column(columnDefinition = "datetime")
    private LocalDateTime createdTime;
    @Column(columnDefinition = "datetime")
    private LocalDateTime usedTime;
    private boolean used;

    @Override
    public String productSKUCode() {
        return code;
    }
}
