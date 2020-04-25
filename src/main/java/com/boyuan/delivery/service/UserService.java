/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.service;

import com.boyuan.delivery.enumeration.ResultInfo;
import com.boyuan.delivery.model.BynUser;

/**
 * User service which is used to manage user
 */
public interface UserService {

    /**
     * Create user
     *
     * @param user
     * @return
     */
    ResultInfo createUser(BynUser user);

    /**
     * Get user by user name
     *
     * @param username
     * @return
     */
    BynUser getUserByUserName(String username);


    /**
     * Get user by phone number
     *
     * @param phone
     * @return
     */
    BynUser getUserByPhone(String phone);

    /**
     * Delete user login cache
     *
     * @param username
     */
    void deleteUserLoginInfo(String username);

    /**
     * Get user password encrypt salt
     *
     * @return
     */
    String getSalt();

}
