package com.buoyuan.delivery.configuration;

import com.buoyuan.delivery.service.UserLogin.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@MapperScan("com.buoyuan.delivery.mapper")
public class CommonConfig {

    @Bean
    public CacheManager caffeineCacheManager(){
        return new CaffeineCacheManager();
    }

    @Bean
    @ConfigurationProperties(prefix = "login")
    public CommonConfigProperties commonConfigProperties(){return new CommonConfigProperties();}

    @Bean
    public UserService userService(){
        return new UserService();
    }

}
