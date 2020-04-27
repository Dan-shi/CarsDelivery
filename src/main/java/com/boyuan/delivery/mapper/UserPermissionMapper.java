/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.mapper;

import com.boyuan.delivery.model.UserPermission;

import java.util.List;

public interface UserPermissionMapper {

    List<UserPermission> getPermissionsById(long id);

    List<UserPermission> getPermissionsByRoleId(long roleId);
}
