package com.buoyuan.delivery.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CommonConfig {

    @Bean
    public CacheManager caffeineCacheManager(){
        return new CaffeineCacheManager();
    }

}
