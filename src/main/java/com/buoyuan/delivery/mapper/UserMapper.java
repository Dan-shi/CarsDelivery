package com.buoyuan.delivery.mapper;

import com.buoyuan.delivery.model.BynUser;

public interface UserMapper {
    BynUser getUserByUserName(String username);

    int insert(BynUser user);
}
