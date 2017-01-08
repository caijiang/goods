/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.lock;

/**
 * @author CJ
 */
public interface GoodsThreadSafeLocker {

    /**
     * @return 以此为锁的对象
     */
    Object lockObject();

}
