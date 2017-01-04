/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.startup.service;

import me.jiangcai.goods.startup.Startup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ConfigurableWebApplicationContext;

/**
 * @author CJ
 */
@Service
public class StartupImpl implements Startup {
    private static final Log log = LogFactory.getLog(StartupImpl.class);
    private ConfigurableWebApplicationContext coreContext;

    @Override
    public void updateGoodsContext(ConfigurableWebApplicationContext goodsContext) {
        coreContext = goodsContext;
    }

    @Override
    public void restart() {
        if (coreContext == null)
            throw new IllegalStateException("with null coreContext");

        coreContext.stop();
        coreContext.start();
        coreContext.refresh();
    }
}
