package com.boyuan.delivery.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@MapperScan("com.boyuan.delivery.mapper")
public class CommonConfig {

    @Bean
    public CacheManager caffeineCacheManager(){
        return new CaffeineCacheManager();
    }

    @Bean
    @ConfigurationProperties(prefix = "boyuan")
    public CommonConfigProperties commonConfigProperties(){return new CommonConfigProperties();}

}
