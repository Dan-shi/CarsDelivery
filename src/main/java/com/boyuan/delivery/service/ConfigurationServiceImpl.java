/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.service;

import com.boyuan.delivery.mapper.ConfigurationMapper;
import com.boyuan.delivery.model.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

public class ConfigurationServiceImpl implements ConfigurationService {

    @Autowired
    private ConfigurationMapper configurationMapper;

    @Cacheable("configuration")
    @Override
    public Configuration getConfigByKey(String configKey) {
        if (StringUtils.isNotBlank(configKey)) {
            return this.configurationMapper.getConfigByKey(configKey);
        }
        return null;
    }
}
