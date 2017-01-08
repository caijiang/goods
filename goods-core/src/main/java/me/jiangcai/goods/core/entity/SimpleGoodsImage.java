/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.core.entity;

import lombok.Getter;
import lombok.Setter;
import me.jiangcai.goods.GoodsImage;
import me.jiangcai.goods.image.ScaledImage;

import javax.persistence.Embeddable;
import java.util.Set;

/**
 * @author CJ
 */
//@Entity
@Embeddable
@Setter
@Getter
public class SimpleGoodsImage implements GoodsImage {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    private Set<ScaledImage> imageSet;
    private String description;

    @Override
    public Set<ScaledImage> getScaledImages() {
        return imageSet;
    }
}
