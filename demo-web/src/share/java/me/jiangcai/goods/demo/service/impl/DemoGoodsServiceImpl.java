/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.demo.service.impl;

import me.jiangcai.goods.core.repository.GoodsRepository;
import me.jiangcai.goods.core.repository.TradeRepository;
import me.jiangcai.goods.demo.entity.DemoGoods;
import me.jiangcai.goods.demo.entity.DemoTrade;
import me.jiangcai.goods.demo.entity.DemoTradedGoods;
import me.jiangcai.goods.demo.entity.GoodsCode;
import me.jiangcai.goods.demo.repository.GoodsCodeRepository;
import me.jiangcai.goods.demo.service.DemoGoodsService;
import me.jiangcai.goods.stock.StockToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author CJ
 */
@Service
public class DemoGoodsServiceImpl implements DemoGoodsService {

    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private TradeRepository tradeRepository;
    @Autowired
    private GoodsCodeRepository goodsCodeRepository;

    @Override
    public void addGoodsCode(DemoGoods goods, String code) {
        GoodsCode goodsCode = new GoodsCode();
        goodsCode.setGoods(goods);
        goodsCode.setCode(code);
        goodsCode.setCreatedTime(LocalDateTime.now());
        goodsCodeRepository.save(goodsCode);
    }

    @Override
    public DemoGoods saveGoods(DemoGoods goods) {
        return goodsRepository.save(goods);
    }

    @Override
    public DemoTrade saveTrade(DemoTrade trade) {
        return tradeRepository.saveAndFlush(trade);
    }

    @Override
    public DemoTrade loadTrade(DemoTrade trade) {
        return (DemoTrade) tradeRepository.getOne(trade.getId());
    }

    @Override
    public DemoTradedGoods createTradedGoods(DemoGoods goods, StockToken[] token) {
        DemoTradedGoods goods1 = new DemoTradedGoods();
        goods1.setGoods(goods);
        goods1.setCode(token[0].productSKUCode());
        return goods1;
    }
}
