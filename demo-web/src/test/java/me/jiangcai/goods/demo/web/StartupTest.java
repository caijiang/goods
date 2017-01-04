/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.demo.web;

import me.jiangcai.goods.test.ManageServletTest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * @author CJ
 */
public class StartupTest extends ManageServletTest {

    private static final Log log = LogFactory.getLog(StartupTest.class);

    @Test
    public void go() {
        log.info("what!");
    }

}