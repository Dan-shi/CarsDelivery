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
public class Blog {

    private Long blogId;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private String author;

    /**
     * image url
     */
    private String imageUrl;

    @NotNull
    private Integer blogType;

    private Boolean isActive;

    @NotBlank
    private String content;

    private Date createTime;

    private Date updateTime;


}
