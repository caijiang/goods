/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.demo.entity;

import lombok.Getter;
import lombok.Setter;
import me.jiangcai.goods.Goods;
import me.jiangcai.goods.GoodsImage;
import me.jiangcai.goods.Seller;
import me.jiangcai.goods.TradeEntity;
import me.jiangcai.goods.TradedGoods;
import me.jiangcai.goods.stock.StockToken;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.List;

/**
 * 它是展示购买的商品
 *
 * @author CJ
 */
@Embeddable
@Setter
@Getter
public class DemoTradedGoods implements TradedGoods, StockToken {

    @ManyToOne(optional = false)
    private DemoGoods goods;
    @Column(length = GoodsCode.CodeLength)
    private String code;

    @Override
    public Goods getReferenceGoods() {
        return goods.getReferenceGoods();
    }

    @Override
    public List<? extends Goods> getAllReferencedGoods() {
        return goods.getAllReferencedGoods();
    }

    @Override
    public Long getId() {
        return goods.getId();
    }

    @Override
    public String getName() {
        return goods.getName();
    }

    @Override
    public void setName(String name) {
        goods.setName(name);
    }

    @Override
    public Number getPrice() {
        return goods.getPrice();
    }

    @Override
    public void setPrice(BigDecimal price) {
        goods.setPrice(price);
    }

    @Override
    public Number getOriginalPrice() {
        return goods.getOriginalPrice();
    }

    @Override
    public Seller getSeller() {
        return goods.getSeller();
    }

    @Override
    public void setSeller(Seller seller) {
        goods.setSeller(seller);
    }

    @Override
    public TradeEntity getOwner() {
        return goods.getOwner();
    }

    @Override
    public void setOwner(TradeEntity owner) {
        goods.setOwner(owner);
    }

    @Override
    public List<? extends GoodsImage> getGoodsImages() {
        return goods.getGoodsImages();
    }

    @Override
    public void addGoodsImage(GoodsImage goodsImage) {
        goods.addGoodsImage(goodsImage);
    }

    @Override
    public boolean isEnable() {
        return goods.isEnable();
    }

    @Override
    public void setEnable(boolean enable) {
        goods.setEnable(enable);
    }

    @Override
    public String getStockStyle() {
        return goods.getStockStyle();
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public StockToken[] toStockToken() {
        return this;
    }

    @Override
    public String productSKUCode() {
        return code;
    }
}
