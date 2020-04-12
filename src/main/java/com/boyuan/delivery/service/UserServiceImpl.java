package com.boyuan.delivery.service;

import com.boyuan.delivery.common.ValidationUtils;
import com.boyuan.delivery.enumeration.CommonResult;
import com.boyuan.delivery.enumeration.ResultInfo;
import com.boyuan.delivery.enumeration.UserRoleInfo;
import com.boyuan.delivery.mapper.UserMapper;
import com.boyuan.delivery.model.BynUser;
import com.boyuan.delivery.model.UserRole;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = {RuntimeException.class, Error.class, Exception.class}, timeout = 300)
@Service
public class UserServiceImpl implements UserService {

    protected final Log logger = LogFactory.getLog(UserServiceImpl.class);

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    private UserMapper userMapper;

    /**
     * BCryptPasswordEncoder is default type of passwordEncoder
     * As daoAuthenticationProvider use BCryptPasswordEncoder to compare user input password and stored password
     * Hence, we need to use same type passwordEncoder to do password encrypt when create new user
     *
     * @param user
     */
    @Override
    public ResultInfo createUser(BynUser user) {
        if (!ValidationUtils.validatorObjInfo(user)) {
            return CommonResult.VALIDATION_ERROR;
        }

        //Encrypt password
        String encryptPwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptPwd);
        //Add basic user role
        user.setUserRole(UserRole.builder().roleId(UserRoleInfo.USER.getRoleId()).build());
        int result = userMapper.insertUser(user);

        if (result <= 0) {
            return CommonResult.CREATE_ERROR;
        }

        return CommonResult.SUCCESS;
    }

    @Cacheable("users")
    @Override
    public BynUser getUserByUserName(String username) {
        logger.info("Get user from DB username: " + username);
        return this.userMapper.getUserByUserName(username);
    }

    @Override
    @Cacheable("salt")
    public String getSalt() {
        return "123456ef";
    }

    @Override
    @CacheEvict(cacheNames = "users")
    public void deleteUserLoginInfo(String username) {}

}
