/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.stock;

/**
 * 出库单号
 *
 * @author CJ
 */
public interface StockToken {

    /**
     * SKU是作为库存的最小单位，在这个场景中不仅是要指出具体货品甚至要指明具体库存；也就是产品唯一码
     *
     * @return 产品唯一码
     */
    String productSKUCode();
}
