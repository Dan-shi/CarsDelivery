/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.service;

import com.boyuan.delivery.model.AdminUser;

/**
 * Admin user
 */
public interface AdminUserService {

    AdminUser getAdminUserByName(String adUserName);

    int createAdminUser(AdminUser adUser);

    int deleteAdminUserById(long adUserId);
}
