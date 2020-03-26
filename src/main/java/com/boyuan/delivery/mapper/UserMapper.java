package com.boyuan.delivery.mapper;

import com.boyuan.delivery.model.BynUser;
import com.boyuan.delivery.model.UserPermission;
import com.boyuan.delivery.model.UserRole;

import java.util.List;

public interface UserMapper {

    BynUser getUserByUserName(String username);

    int insertUser(BynUser user);

    UserRole getUserRoleByRoleId(long roleId);

    List<UserPermission> getPermissionsByRoleId(long roleId);

}
