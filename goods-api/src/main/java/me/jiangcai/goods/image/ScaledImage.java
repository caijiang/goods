/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.image;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 自带多种分辨率的图片
 * 分辨率有几种，一种是固定提供的，一种是按照用途的
 *
 * @author CJ
 */
@Embeddable
@Data
public class ScaledImage {

    private String resourcePath;
    private int width;
    private int height;
    private String format;
    @Column(name = "USG")
    private ImageUsage usage;

}
