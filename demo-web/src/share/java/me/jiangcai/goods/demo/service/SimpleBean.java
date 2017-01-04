/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.demo.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author CJ
 */
@Service
public class SimpleBean {

    private static final Log log = LogFactory.getLog(SimpleBean.class);

    @PostConstruct
    public void init() {
        log.info("startup");
    }

    @PreDestroy
    public void stop() {
        log.info("stop");
    }

}
