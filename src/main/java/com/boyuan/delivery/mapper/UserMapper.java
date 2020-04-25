package com.boyuan.delivery.mapper;

import com.boyuan.delivery.model.BynUser;

public interface UserMapper {

    BynUser getUserByUserName(String username);

    BynUser getUserByPhone(String phone);

    int insertUser(BynUser user);

}
