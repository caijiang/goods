/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.demo.service.impl;

import me.jiangcai.goods.Goods;
import me.jiangcai.goods.demo.entity.DemoGoods;
import me.jiangcai.goods.demo.entity.GoodsCode;
import me.jiangcai.goods.demo.entity.pk.GoodsCodePK;
import me.jiangcai.goods.demo.repository.GoodsCodeRepository;
import me.jiangcai.goods.exception.ShortageStockException;
import me.jiangcai.goods.lock.GoodsThreadSafe;
import me.jiangcai.goods.stock.StockService;
import me.jiangcai.goods.stock.StockToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * @author CJ
 */
@Service("demoStockService")
public class DemoStockService implements StockService {

    @Autowired
    private GoodsCodeRepository goodsCodeRepository;

    @Override
    public boolean support(Goods goods) {
        return goods instanceof DemoGoods;
    }

    @Override
    @GoodsThreadSafe
    public Collection<? extends StockToken> lock(Goods goods, int count) throws ShortageStockException {
        List<GoodsCode> codeList = goodsCodeRepository.findAll((root, query, cb) -> cb.and(
                cb.equal(root.get("goods"), goods)
                , cb.isFalse(root.get("used"))
        ), new PageRequest(0, count)).getContent();

        if (codeList.size() < count)
            throw new ShortageStockException();

        codeList.forEach(goodsCode -> {
            goodsCode.setUsed(true);
            goodsCode.setUsedTime(LocalDateTime.now());
        });

        return codeList;
    }

    @Override
    public void release(Goods goods, StockToken token) {
        goodsCodeRepository.getOne(new GoodsCodePK((me.jiangcai.goods.core.entity.Goods) goods, token.productSKUCode())).setUsed(false);
    }

    @Override
    public long usableCount(Goods goods) {
        return goodsCodeRepository.countByGoodsAndUsedFalse((DemoGoods) goods);
    }
}
