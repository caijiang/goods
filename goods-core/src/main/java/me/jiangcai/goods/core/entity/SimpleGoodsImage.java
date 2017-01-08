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

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

/**
 * @author CJ
 */
@Entity
@Setter
@Getter
public class SimpleGoodsImage implements GoodsImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private Set<ScaledImage> imageSet;
    @Column(length = 50)
    private String description;

    @Override
    public Set<ScaledImage> getScaledImages() {
        return imageSet;
    }

    @Override
    public void addScaledImage(ScaledImage image) {
        if (imageSet == null)
            imageSet = new HashSet<>();
        imageSet.add(image);
    }
}
