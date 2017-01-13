/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.test;

import me.jiangcai.goods.Goods;
import me.jiangcai.goods.demo.DemoConfig;
import me.jiangcai.goods.demo.entity.DemoGoods;
import me.jiangcai.goods.demo.entity.DemoTrade;
import me.jiangcai.goods.demo.service.DemoGoodsService;
import me.jiangcai.goods.event.TradeCloseEvent;
import me.jiangcai.goods.event.TradeDispatchRemindEvent;
import me.jiangcai.goods.event.TradePayEvent;
import me.jiangcai.goods.service.TradeService;
import me.jiangcai.goods.trade.Trade;
import me.jiangcai.lib.resource.service.ResourceService;
import me.jiangcai.lib.test.SpringWebTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;
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
    public static Consumer<TradeDispatchRemindEvent> tradeDispatchRemindEventHook;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected DemoGoodsService demoGoodsService;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private TradeService tradeService;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    protected BigDecimal randomPrice() {
        return new BigDecimal(1 + random.nextInt(3000000));
    }

    /**
     * @return 随机生成的图片资源路径
     */
    protected String randomImageResourcePath() throws IOException {
        String name = "tmp/" + UUID.randomUUID().toString() + ".png";
        resourceService.uploadResource(name, applicationContext.getResource("classpath:/1.png").getInputStream());
        return name;
    }

    protected void payTrade(Trade dbTrade) {
        applicationEventPublisher.publishEvent(new TradePayEvent(dbTrade, null));
    }

    protected Trade createRandomTrade(Goods goods) {
        return createRandomTrade(goods, Duration.ofMinutes(30));
    }

    protected Trade createRandomTrade(Goods goods, Duration duration) {
        return tradeService.createTrade(() -> new DemoTrade(), trade -> demoGoodsService.saveTrade((DemoTrade) trade)
                , trade -> demoGoodsService.loadTrade((DemoTrade) trade)
                , (goods1, token) -> demoGoodsService.createTradedGoods((DemoGoods) goods1, token)
                , goods, 1, null, duration);
    }

    @Configuration
    static class Config {

        @EventListener(TradeCloseEvent.class)
        public void tradeClose(TradeCloseEvent event) {
            if (tradeCloseEventHook != null)
                tradeCloseEventHook.accept(event);
        }

        @EventListener(TradeDispatchRemindEvent.class)
        public void tradeDispatch(TradeDispatchRemindEvent event) {
            if (tradeDispatchRemindEventHook != null)
                tradeDispatchRemindEventHook.accept(event);
        }

    }
}
