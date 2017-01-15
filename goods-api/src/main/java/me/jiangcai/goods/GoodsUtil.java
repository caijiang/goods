/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods;

import java.math.BigDecimal;

/**
 * 一点点静态类
 *
 * @author CJ
 */
public class GoodsUtil {

    public static BigDecimal toBigDecimal(Number price) {
        BigDecimal decimal;
        if (price instanceof BigDecimal)
            decimal = (BigDecimal) price;
        else
            decimal = BigDecimal.valueOf(price.doubleValue());
        return decimal;
    }
}
