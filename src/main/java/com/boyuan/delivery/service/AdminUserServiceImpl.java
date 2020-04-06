/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.service;

import com.boyuan.delivery.mapper.AdminUserMapper;
import com.boyuan.delivery.model.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Cacheable("adminUsers")
    @Override
    public AdminUser getAdminUserByName(String adUserName) {
        return this.adminUserMapper.getAdminUserByName(adUserName);
    }

    @Override
    public int createAdminUser(AdminUser adUser) {
        return this.adminUserMapper.createAdminUser(adUser);
    }

    @Override
    public int deleteAdminUserById(long adUserId) {
        return this.adminUserMapper.deleteAdminUserById(adUserId);
    }
}
