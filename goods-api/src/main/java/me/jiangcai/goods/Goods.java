/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods;

import java.util.List;

/**
 * 这就是商品
 *
 * @author CJ
 */
public interface Goods {

    /**
     * @return 毋庸置疑，商品必须得有一个名字
     */
    String getName();

    /**
     * @return 商品当然也得有价格
     */
    Number getPrice();

    /**
     * @return 商品既然可见，则需有售卖者；无售卖者的商品无法售卖
     */
    Seller getSeller();

    /**
     * @return 商品持有者
     */
    TradeEntity getOwner();

    /**
     * @return 所有图片
     */
    List<? extends GoodsImage> getGoodsImages();


}
