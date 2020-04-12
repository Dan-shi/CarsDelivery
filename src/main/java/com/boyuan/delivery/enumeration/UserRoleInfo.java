/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.enumeration;

/**
 * User role info
 */
public enum UserRoleInfo {
    ADMIN(1L, "Admin", "ADMIN"),
    USER(2L, "User", "USER");

    private long roleId;
    private String roleName;
    private String roleKey;

    UserRoleInfo(long roleId, String roleName, String roleKey) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleKey = roleKey;
    }

    public long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getRoleKey() {
        return roleKey;
    }
}
