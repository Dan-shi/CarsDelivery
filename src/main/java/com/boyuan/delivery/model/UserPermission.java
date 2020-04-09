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
public class UserPermission {
    
    private Long permissionId;

    private String permissionName;

    private String permissionKey;

    private String permissionDescription;

    private String permissionValue;

    private Long roleId;

    private Date createTime;

    private Date updateTime;
}
