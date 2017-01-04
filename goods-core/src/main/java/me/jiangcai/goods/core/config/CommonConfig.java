/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.core.config;

import me.jiangcai.goods.core.GoodsVersion;
import me.jiangcai.goods.core.entity.SystemString;
import me.jiangcai.goods.core.repository.SystemStringRepository;
import me.jiangcai.lib.jdbc.JdbcSpringConfig;
import me.jiangcai.lib.resource.ResourceSpringConfig;
import me.jiangcai.lib.spring.logging.LoggingConfig;
import me.jiangcai.lib.upgrade.UpgradeSpringConfig;
import me.jiangcai.lib.upgrade.VersionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 核心服务 加载者
 *
 * @author CJ
 */
@Configuration
// BracketSpringConfig.class, GAASpringConfig.class
@Import({ResourceSpringConfig.class, UpgradeSpringConfig.class, JdbcSpringConfig.class, LoggingConfig.class})
class CommonConfig {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private SystemStringRepository systemStringRepository;

    @Bean
    @SuppressWarnings("unchecked")
    public VersionInfoService versionInfoService() {
        final String versionKey = "version.database";
        return new VersionInfoService() {

            @Override
            public <T extends Enum> T currentVersion(Class<T> type) {
                SystemString systemString = systemStringRepository.findOne(versionKey);
                if (systemString == null)
                    return null;
                return (T) GoodsVersion.valueOf(systemString.getValue());
            }

            @Override
            public <T extends Enum> void updateVersion(T currentVersion) {
                SystemString systemString = systemStringRepository.findOne(versionKey);
                if (systemString == null) {
                    systemString = new SystemString();
                    systemString.setId(versionKey);
                }
                systemString.setValue(currentVersion.name());
                systemStringRepository.save(systemString);
            }
        };
    }


}
