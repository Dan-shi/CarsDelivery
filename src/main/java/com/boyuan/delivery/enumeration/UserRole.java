/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.enumeration;

/**
 * User role info
 */
public enum UserRole {
    ADMIN(1L, "Admin", "ADMIN"),
    USER(2L, "User", "USER");

    private long roleId;
    private String roleName;
    private String roleKey;

    UserRole(long roleId, String roleName, String roleKey) {
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
