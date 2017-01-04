/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.startup;

import org.springframework.web.context.ConfigurableWebApplicationContext;

/**
 * 具备可管理功能的bean
 *
 * @author CJ
 */
public interface Startup {
    /**
     * @param goodsContext 核心context
     */
    void updateGoodsContext(ConfigurableWebApplicationContext goodsContext);

    /**
     * 重启核心context
     */
    void restart();
}
