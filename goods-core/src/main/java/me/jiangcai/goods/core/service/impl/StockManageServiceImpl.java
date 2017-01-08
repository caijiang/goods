/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.core.service.impl;

import me.jiangcai.goods.Goods;
import me.jiangcai.goods.stock.StockManageService;
import me.jiangcai.goods.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author CJ
 */
@Service
public class StockManageServiceImpl implements StockManageService {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public StockService stockService(Goods goods) {
        return applicationContext.getBeansOfType(StockService.class).values().stream()
                .filter(stockService -> stockService.support(goods))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("没有支持的库存服务"));
    }
}
