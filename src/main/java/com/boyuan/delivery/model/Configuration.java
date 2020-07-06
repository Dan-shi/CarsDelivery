/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Configuration {
    private Long configId;

    private String configName;

    private String description;

    private String configKey;

    private String configValue;

    private Boolean isActive;

    private Date createTime;

    private Date updateTime;
}
