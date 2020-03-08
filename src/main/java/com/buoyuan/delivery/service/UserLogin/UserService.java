package com.buoyuan.delivery.service.UserLogin;

import com.buoyuan.delivery.model.BynUser;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

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
    public BynUser getUserByUserName(String userName){
        return BynUser.builder().userName("Jack").password(passwordEncoder.encode("jack-password")).build();
    }


    @Cacheable("salt")
    public String getSalt(){
        return "123456ef";
    }


    @CacheEvict(cacheNames = "users")
    public void deleteUserLoginInfo(String username) {}

}
