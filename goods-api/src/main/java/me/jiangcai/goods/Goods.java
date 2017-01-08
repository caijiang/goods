/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods;

import java.math.BigDecimal;
import java.util.List;

/**
 * 这就是商品
 *
 * @author CJ
 */
public interface Goods {

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
