/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.core.service;

import me.jiangcai.goods.Goods;
import me.jiangcai.goods.core.entity.SimpleGoodsImage;
import me.jiangcai.goods.demo.entity.DemoGoods;
import me.jiangcai.goods.service.ManageGoodsService;
import me.jiangcai.goods.test.GoodsServletTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.UUID;

/**
 * @author CJ
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
public class ManageGoodsServiceTest extends GoodsServletTest {


    @Autowired
    private ManageGoodsService manageGoodsService;

    @Test
    public void go() throws IOException {
        Goods goods = manageGoodsService.addGoods(() -> new DemoGoods(), null, null, null
                , UUID.randomUUID().toString(), randomPrice(), () -> new SimpleGoodsImage());
        System.out.println(goods);
    }

}