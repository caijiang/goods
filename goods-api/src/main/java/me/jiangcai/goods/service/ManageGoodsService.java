/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.service;

import me.jiangcai.goods.Goods;
import me.jiangcai.goods.GoodsImage;
import me.jiangcai.goods.Seller;
import me.jiangcai.goods.TradeEntity;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 管理goods的服务
 *
 * @author CJ
 */
public interface ManageGoodsService {

    // 添加一样货物 给什么什么售卖者，或者持有者

    /**
     * 添加商品,执行这个动作需是经营者
     *
     * @param goodsSupplier      负责初始化Goods的
     * @param goodsSaver         负责持久化
     * @param seller             经营者
     * @param tradeEntity        拥有者(货物实际的所有者，比如供应商)
     * @param name               名称
     * @param price              价格
     * @param goodsImageSupplier 可选的图片生成器
     * @param imagePaths         图片path  @return 新增的商品       @see me.jiangcai.lib.resource.service.ResourceService#getResource(String)
     */
    @Transactional
    Goods addGoods(Supplier<Goods> goodsSupplier, Function<Goods, Goods> goodsSaver, Seller seller
            , TradeEntity tradeEntity, String name
            , BigDecimal price
            , Supplier<GoodsImage> goodsImageSupplier, String... imagePaths) throws IOException;

    /**
     * 添加商品,执行这个动作需是经营者
     *
     * @param goodsSupplier 负责初始化Goods的
     * @param goodsSaver    负责持久化
     * @param seller        经营者
     * @param tradeEntity   拥有者(货物实际的所有者，比如供应商)
     * @param name          名称
     * @param price         价格
     * @param imagePaths    图片path  @return 新增的商品       @see me.jiangcai.lib.resource.service.ResourceService#getResource(String)
     */
    @Transactional
    Goods addGoods(Supplier<Goods> goodsSupplier, Function<Goods, Goods> goodsSaver, Seller seller
            , TradeEntity tradeEntity, String name
            , BigDecimal price, String... imagePaths) throws IOException;

    /**
     * 上架商品
     *
     * @param goods      指定商品
     * @param goodsSaver 负责持久化
     * @return 新的商品
     */
    @Transactional
    Goods enableGoods(Goods goods, Function<Goods, Goods> goodsSaver);
    // 库存
    // 那还是先做购买


}
