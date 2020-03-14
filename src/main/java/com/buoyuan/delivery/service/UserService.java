package com.buoyuan.delivery.service;

import com.buoyuan.delivery.mapper.UserMapper;
import com.buoyuan.delivery.model.BynUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = {RuntimeException.class, Error.class, Exception.class}, timeout = 300)
@Service
public class UserService {

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    private UserMapper userMapper;

    /**
     * BCryptPasswordEncoder is default type of passwordEncoder
     * As daoAuthenticationProvider use BCryptPasswordEncoder to compare user input password and stored password
     * Hence, we need to use same type passwordEncoder to do password encrypt when create new user
     * @param username
     * @param password
     */
    public void createUser(String username, String password) {
        String encryptPwd = passwordEncoder.encode(password);
        /**
         * @todo 保存用户名和加密后密码到数据库
         */
    }

    @Cacheable("users")
    public BynUser getUserByUserName(String username){
        return this.userMapper.getUserByUserName(username);
    }


    @Cacheable("salt")
    public String getSalt(){
        return "123456ef";
    }


    @CacheEvict(cacheNames = "users")
    public void deleteUserLoginInfo(String username) {}

}
