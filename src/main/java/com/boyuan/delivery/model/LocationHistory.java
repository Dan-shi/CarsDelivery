/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LocationHistory {

    private Long locId;

    @NotNull
    private Long orderId;

    @NotBlank
    private String location;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    private String description;

    private Date createTime;

    private Date updateTime;


}
