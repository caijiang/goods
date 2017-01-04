/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 提供的唯一入口配置
 *
 * @author CJ
 */
@Configuration
@Import({CommonConfig.class})
@EnableJpaRepositories("me.jiangcai.goods.core.repository")
public class ServiceConfig {
}
