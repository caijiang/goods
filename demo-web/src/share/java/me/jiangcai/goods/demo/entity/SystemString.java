/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 系统字符串
 *
 * @author CJ
 */
@Entity
@Setter
@Getter
public class SystemString {

    @Id
    @Column(length = 50)
    private String id;
    private String value;

}
