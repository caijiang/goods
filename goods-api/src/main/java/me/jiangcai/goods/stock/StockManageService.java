/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.stock;

import me.jiangcai.goods.Goods;

/**
 * 系统提供的库存服务管理服务
 *
 * @author CJ
 */
public interface StockManageService {

    /**
     * 每一个商品都有可能使用不同的库存服务
     *
     * @param goods 商品
     * @return 可用服务
     */
    StockService stockService(Goods goods);
}
