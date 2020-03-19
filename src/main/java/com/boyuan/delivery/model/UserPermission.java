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
    
    private long permissionId;

    private String permissionName;

    private String permissionKey;

    private String permissionDescription;

    private String permissionValue;

    private long roleId;

    private Date createTime;

    private Date updateTime;
}
