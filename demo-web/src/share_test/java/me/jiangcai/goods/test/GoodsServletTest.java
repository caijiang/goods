/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.test;

import me.jiangcai.goods.demo.DemoConfig;
import me.jiangcai.goods.demo.service.DemoGoodsService;
import me.jiangcai.lib.test.SpringWebTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;

/**
 * @author CJ
 */
@WebAppConfiguration
@ContextConfiguration(classes = {DemoConfig.class})
public abstract class GoodsServletTest extends SpringWebTest {

    @Autowired
    protected DemoGoodsService demoGoodsService;

    protected BigDecimal randomPrice() {
        return new BigDecimal(1 + random.nextInt(3000000));
    }
}
