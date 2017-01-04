/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.startup.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author CJ
 */
@Configuration
@ComponentScan(
        {"me.jiangcai.goods.startup.service", "me.jiangcai.goods.startup.controller"})
public class StartupConfig {
}
