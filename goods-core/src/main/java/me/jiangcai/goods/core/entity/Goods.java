/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.core.entity;

import lombok.Getter;
import lombok.Setter;
import me.jiangcai.goods.GoodsImage;
import me.jiangcai.goods.lock.GoodsThreadSafeLocker;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CJ
 */
@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Goods implements me.jiangcai.goods.Goods, GoodsThreadSafeLocker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;
    /**
     * 一般描述
     */
    @Column(length = 200)
    private String description;
    /**
     * 富文本详细描述
     */
    @Lob
    private String richDetail;
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    /**
     * 初始价格
     */
    @Column(precision = 10, scale = 2)
    private BigDecimal originalPrice;
    private boolean enable;
    @Column(length = 20)
    private String stockStyle;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<SimpleGoodsImage> goodsImages;
//    @ManyToOne(cascade = {CascadeType.REFRESH},optional = false)
//    private GoodsSeller seller;

    @Override
    public void addGoodsImage(GoodsImage goodsImage) {
        if (goodsImages == null)
            goodsImages = new ArrayList<>();

        goodsImages.add((SimpleGoodsImage) goodsImage);
    }

    @Override
    public me.jiangcai.goods.Goods getReferenceGoods() {
        return null;
    }

    @Override
    public List<? extends me.jiangcai.goods.Goods> getAllReferencedGoods() {
        return null;
    }

    @Override
    public Object lockObject() {
        return ("Goods-" + id).intern();
    }
}
