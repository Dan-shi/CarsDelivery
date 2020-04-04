package com.boyuan.delivery.mapper;

import com.boyuan.delivery.model.BynUser;

public interface UserMapper {

    BynUser getUserByUserName(String username);

    int insertUser(BynUser user);

}
