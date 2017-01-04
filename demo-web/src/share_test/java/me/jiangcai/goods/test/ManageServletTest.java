/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.test;

import me.jiangcai.goods.demo.DemoConfig;
import me.jiangcai.goods.startup.Startup;
import me.jiangcai.goods.startup.config.StartupConfig;
import me.jiangcai.lib.test.SpringWebTest;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @author CJ
 */
@WebAppConfiguration
@ContextConfiguration(classes = {StartupConfig.class})
public abstract class ManageServletTest extends SpringWebTest {

    @Autowired
    private Startup startup;

    @Before
    public void setupContext() {
        AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
        servletAppContext.setServletContext(servletContext);
        servletAppContext.register(new Class<?>[]{
                DemoConfig.class
        });
        servletAppContext.refresh();
        servletAppContext.start();

        startup.updateGoodsContext(servletAppContext);
    }

}
