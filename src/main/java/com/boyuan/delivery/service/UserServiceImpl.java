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
        SecurityUtils.trimStringFieldOrSetNull(user);
        if(this.validatorUserInfo(user)){
            String encryptPwd = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptPwd);
            //Add basic user role
            user.setUserRole(UserRole.builder().roleId(CommonConstant.UserRole.USER_ROLE_ID).build());
            int result = userMapper.insertUser(user);

            if(result > 0){
                return CommonConstant.Status.SUCCESS;
            }
        }

        return CommonConstant.Status.ERROR;
    }

    /**
     * In case of malicious input from user, do security check here.
     * Trim all string field
     * @return
     */
    private void securityUserTransform(BynUser user){

//        if(StringUtils.isBlank(user.getUserName())){
//            user.setUserName(null);
//        } else {
//            user.setUserName(user.getUserName().trim());
//        }
//
//        if(StringUtils.isBlank(user.getPassword())){
//            user.setPassword(null);
//        } else {
//            user.setPassword(user.getPassword().trim());
//        }
//
//        if(StringUtils.isBlank(user.getPhone())){
//            user.setPhone(null);
//        } else {
//            user.setPhone(user.getPhone().trim());
//        }
//
//        if(StringUtils.isBlank(user.getBirthday())){
//            user.setBirthday(null);
//        } else {
//            user.setBirthday(user.getBirthday().trim());
//        }

    }

    /**
     * Validate user field
     * @param user
     * @return true: user is legal; false: user is not legal
     */
    private boolean validatorUserInfo(BynUser user) {

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
