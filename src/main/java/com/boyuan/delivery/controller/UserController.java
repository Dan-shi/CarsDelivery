package com.boyuan.delivery.controller;

import com.boyuan.delivery.common.utility.ResultUtils;
import com.boyuan.delivery.constant.CommonConstant.Permission;
import com.boyuan.delivery.enumeration.UserResult;
import com.boyuan.delivery.model.BynUser;
import com.boyuan.delivery.model.Result;
import com.boyuan.delivery.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    protected final Log logger = LogFactory.getLog(UserController.class);

    /**
     * User service
     */
    @Autowired
    private UserService userService;

    /**
     * Get user by user name
     *
     * @param userName
     * @return
     */
    @Secured(Permission.ADMIN)
    @GetMapping("name")
    public Result getUserByName(@RequestParam(value = "name") String userName) {

        try {
            return ResultUtils.buildResultWithBody(this.userService.getUserByUserName(userName));
        } catch (Exception e) {
            logger.error("getUserByUserName error", e);
            return ResultUtils.buildErrorResult(e.getMessage());
        }

    }

    /**
     * Get user by user name
     *
     * @param userName
     * @return
     */
    @Secured(Permission.VIEW_USER)
    @GetMapping("userName")
    public Result getCurrentUserByName(@RequestParam(value = "userName") String userName) {

        try {
            //TODO if token is not match user name then return prompt
            return ResultUtils.buildResultWithBody(this.userService.getUserByUserName(userName));
        } catch (Exception e) {
            logger.error("getUserByUserName error", e);
            return ResultUtils.buildErrorResult(e.getMessage());
        }

    }

    /**
     * Create new user
     *
     * @param user
     * @return
     */
    @Secured(Permission.CREATE_USER)
    @PostMapping("create")
    public Result createUser(@RequestBody BynUser user) {

        try {
            //check whether user exist
            BynUser customer = this.userService.getUserByPhone(user.getPhone());
            if (StringUtils.isNotBlank(customer.getUserName())) {
                return ResultUtils.buildResultByResultInfo(UserResult.USER_EXIST);
            }
            return ResultUtils.buildResultByResultInfo(this.userService.createUser(user));
        } catch (Exception e) {
            logger.error("Create User error", e);
            return ResultUtils.buildErrorResult(e.getMessage());

        }

    }
}
