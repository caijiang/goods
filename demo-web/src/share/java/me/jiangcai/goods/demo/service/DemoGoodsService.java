/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.demo.service;

import me.jiangcai.goods.demo.entity.DemoGoods;
import me.jiangcai.goods.demo.entity.DemoTrade;
import me.jiangcai.goods.demo.entity.DemoTradedGoods;
import me.jiangcai.goods.stock.StockToken;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author CJ
 */
public interface DemoGoodsService {

    @Transactional
    void addGoodsCode(DemoGoods goods, String code);

    @Transactional
    DemoGoods saveGoods(DemoGoods goods);

    @Transactional
    DemoTrade saveTrade(DemoTrade trade);

    @Transactional(readOnly = true)
    DemoTrade loadTrade(DemoTrade trade);

    DemoTradedGoods createTradedGoods(DemoGoods goods, StockToken[] token);
}
