/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.demo.loader;

import me.jiangcai.goods.demo.DemoConfig;
import me.jiangcai.goods.startup.ServiceLoader;
import me.jiangcai.goods.startup.config.StartupConfig;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author CJ
 */
public class DemoLoader extends ServiceLoader {
    public static final Class<?>[] GoodsConfigClasses = new Class<?>[]{DemoConfig.class};
    public static final Class<?>[] ManageConfigClasses = new Class<?>[]{StartupConfig.class};

    @Override
    protected Class<?>[] getGoodsServletConfigClasses() {
        return GoodsConfigClasses;
    }

    @Override
    protected Class<?>[] getManageServletConfigClasses() {
        return ManageConfigClasses;
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }
}
