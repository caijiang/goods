/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.startup.controller;

import me.jiangcai.goods.startup.Startup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author CJ
 */
@Controller
public class SimpleManageController {

    private static final Log log = LogFactory.getLog(SimpleManageController.class);

    @Autowired
    private Startup startup;

    @RequestMapping(method = RequestMethod.POST, value = "/restart")
    @ResponseStatus(HttpStatus.CREATED)
    public void restart() {
        startup.restart();
    }


}
