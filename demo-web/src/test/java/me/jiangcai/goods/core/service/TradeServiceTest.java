/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.core.service;

import me.jiangcai.goods.Goods;
import me.jiangcai.goods.demo.entity.DemoGoods;
import me.jiangcai.goods.demo.entity.DemoTrade;
import me.jiangcai.goods.exception.IllegalGoodsException;
import me.jiangcai.goods.exception.ShortageStockException;
import me.jiangcai.goods.service.ManageGoodsService;
import me.jiangcai.goods.service.TradeService;
import me.jiangcai.goods.stock.StockService;
import me.jiangcai.goods.test.GoodsServletTest;
import me.jiangcai.goods.trade.Trade;
import me.jiangcai.goods.trade.TradeStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author CJ
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
public class TradeServiceTest extends GoodsServletTest {

    @Autowired
    private TradeService tradeService;
    @Autowired
    private ManageGoodsService manageGoodsService;
    @Autowired
    private StockService demoStockService;

    @Test
    public void go() throws IOException, InterruptedException {

        int imagesCount = 1 + random.nextInt(3);
        String[] images = new String[imagesCount];
        for (int i = 0; i < images.length; i++) {
            images[i] = randomImageResourcePath();
        }

        Goods goods = manageGoodsService.addGoods(DemoGoods::new
                , goods1 -> demoGoodsService.saveGoods((DemoGoods) goods1), null, null
                , UUID.randomUUID().toString(), randomPrice(), images);

        try {
            createRandomTrade(goods);
            throw new AssertionError("应当无法使用");
        } catch (IllegalGoodsException ignored) {
            //继续
        }

        goods = manageGoodsService.enableGoods(goods, goods1 -> demoGoodsService.saveGoods((DemoGoods) goods1));

        try {
            createRandomTrade(goods);
            throw new AssertionError("库存应当不足");
        } catch (ShortageStockException ignored) {
            //继续
        }

        demoGoodsService.addGoodsCode((DemoGoods) goods, UUID.randomUUID().toString());
        assertThat(demoStockService.usableCount(goods))
                .isEqualByComparingTo(1L);

        Semaphore semaphore = new Semaphore(0);

        GoodsServletTest.tradeCloseEventHook = event -> {
            new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
                semaphore.release();
            }).start();
        };
// 建立一个订单
        Trade dbTrade = createRandomTrade(goods, Duration.ofMillis(1000));

        System.out.println(dbTrade);
        assertThat(demoStockService.usableCount(goods))
                .isEqualByComparingTo(0L);

        semaphore.tryAcquire(4, TimeUnit.SECONDS);

        assertThat(demoGoodsService.loadTrade((DemoTrade) dbTrade).getStatus())
                .isEqualByComparingTo(TradeStatus.closed);
        assertThat(demoStockService.usableCount(goods))
                .isEqualByComparingTo(1L);

    }

    private Trade createRandomTrade(Goods goods) {
        return createRandomTrade(goods, Duration.ofMinutes(30));
    }

    private Trade createRandomTrade(Goods goods, Duration duration) {
        return tradeService.createTrade(() -> new DemoTrade(), trade -> demoGoodsService.saveTrade((DemoTrade) trade)
                , trade -> demoGoodsService.loadTrade((DemoTrade) trade)
                , (goods1, token) -> demoGoodsService.createTradedGoods((DemoGoods) goods1, token)
                , goods, 1, null, duration);
    }
}
