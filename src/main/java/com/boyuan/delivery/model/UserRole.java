package com.boyuan.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRole {

    private Long roleId;

    private String roleName;

    private String roleKey;

    private String roleDescription;

    private List<UserPermission> userPermissions;

    private Date createTime;

    private Date updateTime;

}
