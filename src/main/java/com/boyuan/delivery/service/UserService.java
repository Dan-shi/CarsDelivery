/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.service;

import com.boyuan.delivery.model.BynUser;

/**
 * User service which is used to manage user
 */
public interface UserService {

    /**
     * Get user by user name
     * @param username
     * @return
     */
    BynUser getUserByUserName(String username);

    /**
     * Create user
     * @param user
     * @return
     */
    int createUser(BynUser user);

    /**
     * Get user password encrypt salt
     * @return
     */
    String getSalt();

    /**
     * Delete user login cache
     * @param username
     */
    void deleteUserLoginInfo(String username);

}
