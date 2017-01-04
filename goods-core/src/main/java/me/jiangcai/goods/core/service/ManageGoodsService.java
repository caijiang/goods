/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.core.service;

import me.jiangcai.goods.Goods;
import me.jiangcai.goods.Seller;
import me.jiangcai.goods.TradeEntity;

import java.math.BigDecimal;
import java.util.function.Supplier;

/**
 * 管理goods的服务
 *
 * @author CJ
 */
public interface ManageGoodsService {

    // 添加一样货物 给什么什么售卖者，或者持有者

    /**
     * 添加商品,执行这个动作需是经营者
     *
     * @param goodsSupplier 负责初始化Goods的
     * @param seller        经营者
     * @param tradeEntity   拥有者(货物实际的所有者，比如供应商)
     * @param name          名称
     * @param price         价格
     * @param imagePaths    图片path
     * @return 新增的商品
     * @see me.jiangcai.lib.resource.service.ResourceService#getResource(String)
     */
    Goods addGoods(Supplier<Goods> goodsSupplier, Seller seller, TradeEntity tradeEntity, String name, BigDecimal price
            , String... imagePaths);


}
