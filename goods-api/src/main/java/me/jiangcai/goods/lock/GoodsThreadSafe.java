/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.lock;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表示这个方法是以线程安全进行的
 * 系统总是以第一个参数作为锁对象进行线程安全保证，如果有任意参数实现了 {@link GoodsThreadSafeLocker}则会第一个实现者作为锁
 *
 * @author CJ
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface GoodsThreadSafe {
}
