/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.stock;

import me.jiangcai.goods.Goods;
import me.jiangcai.goods.exception.ShortageStockException;
import me.jiangcai.goods.lock.GoodsThreadSafe;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author CJ
 */
public interface StockService {

    boolean support(Goods goods);

    @Transactional
    @GoodsThreadSafe
    Collection<? extends StockToken> lock(Goods goods, int count) throws ShortageStockException;

    @Transactional
    void release(Goods goods, StockToken token);

    @Transactional(readOnly = true)
    long usableCount(Goods goods);
}
