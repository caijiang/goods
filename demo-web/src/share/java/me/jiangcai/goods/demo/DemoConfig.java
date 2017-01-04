/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.demo;

import me.jiangcai.goods.core.config.ServiceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * 一份完整web的demo 配置
 *
 * @author CJ
 */
@Import(ServiceConfig.class)
@ImportResource(locations = "classpath:/datasource.xml")
@ComponentScan("me.jiangcai.goods.demo.service")
public class DemoConfig {
}
