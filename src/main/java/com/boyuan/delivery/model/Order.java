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
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Order {

    private long orderId;

    @NotNull
    private int orderStatus;

    /**
     * where does this order come from
     * like 58, baidu and so on.
     */
    @NotNull
    private int orderSource;

    @NotNull
    private int orderType;

    @NotNull
    private long adminUserId;

    @NotNull
    private int carType;

    @NotBlank
    private String carName;

    private String price;

    /**
     * Source location
     */
    @NotNull
    private String fromLocation;

    /**
     * Destination
     */
    @NotNull
    private String toLocation;

    /**
     * LocationHistory history
     */
    private List<LocationHistory> locationHis;

    /**
     * Customer name
     */
    private String cusName;

    /**
     * Customer phone number
     */
    @NotBlank
    @Pattern(regexp = "^1(3|4|5|6|7|8|9)\\d{9}$", message = "phone number format wrong")
    private String cusPhone;

    private Boolean isActive;

    private Date createTime;

    private Date updateTime;
}
