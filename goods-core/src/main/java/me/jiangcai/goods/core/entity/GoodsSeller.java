/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.core.entity;

import lombok.Getter;
import lombok.Setter;
import me.jiangcai.goods.Seller;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 商品销售者
 *
 * @author CJ
 */
@Setter
@Getter
@Entity
public class GoodsSeller implements Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
