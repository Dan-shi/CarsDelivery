/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.mapper;

import com.boyuan.delivery.model.Configuration;

public interface ConfigurationMapper {
    Configuration getConfigByKey(String configKey);
}
