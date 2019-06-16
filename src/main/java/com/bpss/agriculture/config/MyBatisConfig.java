package com.bpss.agriculture.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * @author Rocky Wu
 */
public class MyBatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer(){
        // 开启驼峰命名
        return configuration -> configuration.setMapUnderscoreToCamelCase(true);
    }

}
