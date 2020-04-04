package com.boyuan.delivery.controller;

import com.boyuan.delivery.common.ResultUtils;
import com.boyuan.delivery.constant.CommonConstant;
import com.boyuan.delivery.model.BynUser;
import com.boyuan.delivery.model.Result;
import com.boyuan.delivery.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import com.boyuan.delivery.constant.CommonConstant.UserRole;

import java.sql.Timestamp;


@RestController
@RequestMapping("user")
public class UserController {

    protected final Log logger = LogFactory.getLog(UserController.class);

    //@TODO add init binder to secure input parameter
//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
//    }

    /**
     * User service
     */
    @Autowired
    private UserService userService;

    @Secured(UserRole.USER)
    @GetMapping("test")
    public String load() {
        return "This is my first blog";
    }

    /**
     * Get user by user name
     * @param userName
     * @return
     */
    @Secured(UserRole.USER)
    @GetMapping("name")
    public Result getUserByUserName(@RequestParam(value = "userName") String userName){

        try {
            return ResultUtils.buildResult(CommonConstant.Status.SUCCESS, this.userService.getUserByUserName(userName));
        } catch (Exception e){
            logger.error("getUserByUserName error", e);
            return ResultUtils.buildResult(CommonConstant.Status.ERROR, null);
        }

    }

    /**
     * Create new user
     * @param user
     * @return
     */
    @Secured(UserRole.USER)
    @PostMapping("create")
    public Result createUser(@RequestBody BynUser user){

        try {
            return ResultUtils.buildResult(CommonConstant.Status.SUCCESS, userService.createUser(user));
        } catch (Exception e){
            logger.error("Create User error", e);
            return ResultUtils.buildResult(CommonConstant.Status.ERROR, null);

        }

    }
}
