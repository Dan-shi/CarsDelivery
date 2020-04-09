package com.boyuan.delivery.service;

import com.boyuan.delivery.common.SecurityUtils;
import com.boyuan.delivery.common.ValidationUtils;
import com.boyuan.delivery.constant.CommonConstant;
import com.boyuan.delivery.mapper.UserMapper;
import com.boyuan.delivery.model.BynUser;
import com.boyuan.delivery.model.UserRole;
import com.boyuan.delivery.model.ValidationResult;
import org.apache.commons.lang3.StringUtils;
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
     * @param user
     */
    public int createUser(BynUser user) {
        if(!this.validatorUserInfo(user)){
            return CommonConstant.Status.ERROR;
        }

        String encryptPwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptPwd);
        //Add basic user role
        user.setUserRole(UserRole.builder().roleId(CommonConstant.UserRole.USER_ROLE_ID).build());
        int result = userMapper.insertUser(user);

        if(result > 0){
            return CommonConstant.Status.SUCCESS;
        }

        return CommonConstant.Status.ERROR;
    }


    /**
     * Validate user field
     * @param user
     * @return true: user is legal; false: user is not legal
     */
    private boolean validatorUserInfo(BynUser user) {
        SecurityUtils.trimStringFieldOrSetNull(user);

        ValidationResult result = ValidationUtils.validateEntity(user);
        if(result.isHasErrors()){
            logger.error("User validation ERROR: " + result.getErrorMsg().toString());
            return false;
        }

        return true;
    }

    @Cacheable("users")
    public BynUser getUserByUserName(String username){
        logger.info("Get user from DB username: "+ username);
        return this.userMapper.getUserByUserName(username);
    }


    @Cacheable("salt")
    public String getSalt(){
        return "123456ef";
    }


    @CacheEvict(cacheNames = "users")
    public void deleteUserLoginInfo(String username) {}

}
