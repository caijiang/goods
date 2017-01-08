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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 一份完整web的demo 配置
 *
 * @author CJ
 */
@Import({ServiceConfig.class, DataSupportConfig.class})
@ImportResource(locations = "classpath:/datasource.xml")
@ComponentScan("me.jiangcai.goods.demo.service")
@EnableJpaRepositories(basePackages = "me.jiangcai.goods.demo.repository")
public class DemoConfig {

//    @SuppressWarnings("SpringJavaAutowiringInspection")
//    @Autowired
//    private SystemStringRepository systemStringRepository;
//
//    @Bean
//    @SuppressWarnings("unchecked")
//    public VersionInfoService versionInfoService() {
//        final String versionKey = "version.database";
//        return new VersionInfoService() {
//
//            @Override
//            public <T extends Enum> T currentVersion(Class<T> type) {
//                SystemString systemString = systemStringRepository.findOne(versionKey);
//                if (systemString == null)
//                    return null;
//                return (T) GoodsVersion.valueOf(systemString.getValue());
//            }
//
//            @Override
//            public <T extends Enum> void updateVersion(T currentVersion) {
//                SystemString systemString = systemStringRepository.findOne(versionKey);
//                if (systemString == null) {
//                    systemString = new SystemString();
//                    systemString.setId(versionKey);
//                }
//                systemString.setValue(currentVersion.name());
//                systemStringRepository.saveGoods(systemString);
//            }
//        };
//    }
}
