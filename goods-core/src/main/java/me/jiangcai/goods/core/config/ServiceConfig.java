/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.core.config;

import me.jiangcai.goods.core.config.aop.MakeThreadSafe;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * 提供的唯一入口配置
 *
 * @author CJ
 */
@Configuration
@Import({CommonConfig.class})
@ComponentScan({"me.jiangcai.goods.core.service"})
@EnableJpaRepositories("me.jiangcai.goods.core.repository")
public class ServiceConfig {

    @Bean
    public TaskScheduler tradeCloseScheduler() {
        // 应该是允许覆盖的
        return new ThreadPoolTaskScheduler();
    }

    @Bean
    public MakeThreadSafe makeThreadSafe() {
        return new MakeThreadSafe();
    }

}
