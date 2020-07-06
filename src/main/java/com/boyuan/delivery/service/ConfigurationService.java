/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.service;

import com.boyuan.delivery.model.Configuration;

/**
 * Get configuration from database
 */
public interface ConfigurationService {
    /**
     * Get configuration by key
     *
     * @param configKey
     * @return
     */
    Configuration getConfigByKey(String configKey);
}
