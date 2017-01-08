/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.test;

import me.jiangcai.goods.demo.DemoConfig;
import me.jiangcai.goods.demo.service.DemoGoodsService;
import me.jiangcai.goods.event.TradeCloseEvent;
import me.jiangcai.lib.test.SpringWebTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.function.Consumer;

/**
 * @author CJ
 */
@WebAppConfiguration
@ContextConfiguration(classes = {DemoConfig.class, GoodsServletTest.Config.class})
public abstract class GoodsServletTest extends SpringWebTest {

    /**
     * 发现订单关闭时间的钩子
     */
    public static Consumer<TradeCloseEvent> tradeCloseEventHook;
    @Autowired
    protected DemoGoodsService demoGoodsService;

    protected BigDecimal randomPrice() {
        return new BigDecimal(1 + random.nextInt(3000000));
    }

    @Configuration
    static class Config {

        @EventListener(TradeCloseEvent.class)
        public void tradeClose(TradeCloseEvent event) {
            tradeCloseEventHook.accept(event);
        }

    }
}
