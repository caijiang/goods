/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods;

import me.jiangcai.goods.image.ImageUsage;
import me.jiangcai.goods.image.ScaledImage;

import java.util.Set;

/**
 * 商品图片
 *
 * @author CJ
 */
public interface GoodsImage {
    /**
     * @return 所有的图片
     */
    Set<ScaledImage> getScaledImages();

    /**
     * @return 这是可选的描述
     */
    String getDescription();

    default ScaledImage forUsage(ImageUsage usage) {
        return getScaledImages().stream()
                .filter(scaledImage -> scaledImage.getUsage() == usage)
                .findFirst()
                .orElse(getScaledImages().stream().findAny().orElseThrow(() -> new IllegalStateException("没有任何可用图片")));
    }

    default ScaledImage getDefaultImage() {
        return forUsage(ImageUsage.preview);
    }
}
