/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.startup.controller;

import me.jiangcai.goods.test.ManageServletTest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author CJ
 */
public class SimpleManageControllerTest extends ManageServletTest {

    private static final Log log = LogFactory.getLog(SimpleManageControllerTest.class);

    @Test
    public void go() throws Exception {
        mockMvc.perform(post("/restart"))
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }

}