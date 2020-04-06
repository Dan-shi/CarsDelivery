package com.boyuan.delivery.controller;

import com.alibaba.fastjson.JSONObject;
import com.boyuan.delivery.common.ResultUtils;
import com.boyuan.delivery.constant.CommonConstant;
import com.boyuan.delivery.model.Result;
import com.boyuan.delivery.service.AdminUserService;
import com.boyuan.delivery.service.JwtUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.boyuan.delivery.constant.CommonConstant.AdminUrlMapping;

@Controller
@RequestMapping("admin")
public class AdminUIController {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private JwtUserService jwtUserService;

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @GetMapping("login")
    public String adminLoginPage(Model model) {
        model.addAttribute("msg", "一段信息");
        return AdminUrlMapping.ADMIN_PREFIX + AdminUrlMapping.ADMIN_LOGIN;
    }

    @PostMapping("login")
    public Result adminLogin(String userName, String password) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            JSONObject result = new JSONObject();
            result.put("message", "Username or password doesn't right");
            return ResultUtils.buildResult(CommonConstant.Status.ERROR, result);
        }

        UserDetails adminUser = jwtUserService.loadAdminUserByUsername(userName);
        if (adminUser == null) {
            JSONObject result = new JSONObject();
            result.put("message", "There is no according admin user in system");
            return ResultUtils.buildResult(CommonConstant.Status.ERROR, result);
        }

        //Verify user password
        String encryptSalt = passwordEncoder.encode(adminUser.getPassword());
        if (adminUser.getPassword().equals(encryptSalt)) {

            String token = this.jwtUserService.saveUserLoginInfo(adminUser);
            JSONObject result = new JSONObject();
            result.put("token", token);
            return ResultUtils.buildResult(CommonConstant.Status.SUCCESS, result);
        } else {
            JSONObject result = new JSONObject();
            result.put("message", "Username or password doesn't right");
            return ResultUtils.buildResult(CommonConstant.Status.ERROR, result);
        }

    }
}
