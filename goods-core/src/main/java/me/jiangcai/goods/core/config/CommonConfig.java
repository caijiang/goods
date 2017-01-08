/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.core.config;

import me.jiangcai.lib.jdbc.JdbcSpringConfig;
import me.jiangcai.lib.resource.ResourceSpringConfig;
import me.jiangcai.lib.spring.logging.LoggingConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 核心服务 加载者
 *
 * @author CJ
 */
@Configuration
// BracketSpringConfig.class, GAASpringConfig.class
// UpgradeSpringConfig.class,
@Import({ResourceSpringConfig.class, JdbcSpringConfig.class, LoggingConfig.class})
class CommonConfig {


}
