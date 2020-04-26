package com.boyuan.delivery.controller;

import com.boyuan.delivery.common.utility.ResultUtils;
import com.boyuan.delivery.constant.CommonConstant.UserRole;
import com.boyuan.delivery.enumeration.UserResult;
import com.boyuan.delivery.model.BynUser;
import com.boyuan.delivery.model.Result;
import com.boyuan.delivery.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Get user by user name
     *
     * @param userName
     * @return
     */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("name")
    public Result getUserByUserName(@RequestParam(value = "userName") String userName) {

        try {
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
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
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
