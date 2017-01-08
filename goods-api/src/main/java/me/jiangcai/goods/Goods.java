/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods;

import java.math.BigDecimal;
import java.util.List;

/**
 * 这就是商品，只要是参与订单过程的那就是商品，一个规格，一个样式同样也是商品
 *
 * @author CJ
 */
public interface Goods {

    /**
     * 如果参与运算的商品并不具备实际属性，则依赖于其引用的商品
     *
     * @return 引用商品，如果为null表示自身就是
     */
    Goods getReferenceGoods();

    /**
     * 通常用于获取他们的规格
     *
     * @return 所有被引用的商品
     */
    List<? extends Goods> getAllReferencedGoods();

    default Goods getFinalReferenceGood() {
        Goods goods = getReferenceGoods();
        if (goods != null)
            return goods.getFinalReferenceGood();
        return this;
    }

    /**
     * @return 数据库主键，业务无关的
     */
    Long getId();

    /**
     * @return 毋庸置疑，商品必须得有一个名字
     */
    String getName();

    void setName(String name);

    /**
     * @return 商品当然也得有价格
     */
    Number getPrice();

    void setPrice(BigDecimal price);

    /**
     * @return 初始价格
     */
    Number getOriginalPrice();

    /**
     * @return 商品既然可见，则需有售卖者；无售卖者的商品无法售卖
     */
    Seller getSeller();

    void setSeller(Seller seller);

    /**
     * @return 商品持有者
     */
    TradeEntity getOwner();

    void setOwner(TradeEntity owner);

    /**
     * @return 所有图片
     */
    List<? extends GoodsImage> getGoodsImages();

    /**
     * @return 标题图片
     */
    default GoodsImage getTitleGoodsImage() {
        return getGoodsImages().get(0);
    }

    void addGoodsImage(GoodsImage goodsImage);

    boolean isEnable();

    void setEnable(boolean enable);

    String getStockStyle();
}
