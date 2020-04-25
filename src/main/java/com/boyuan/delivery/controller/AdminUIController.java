package com.boyuan.delivery.controller;

import com.boyuan.delivery.constant.CommonConstant.UserRole;
import com.boyuan.delivery.service.AdminUserService;
import com.boyuan.delivery.service.JwtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.boyuan.delivery.constant.CommonConstant.AdminUrlMapping;

@Controller
@RequestMapping("admin")
public class AdminUIController {

    /**
     * Login page
     *
     * @param model
     * @return
     */
    @GetMapping("login")
    public String adminLoginPage(Model model) {
        return AdminUrlMapping.ADMIN_PREFIX + AdminUrlMapping.ADMIN_LOGIN;
    }

    /**
     * Order management
     *
     * @param model
     * @return
     */
    @Secured(UserRole.ADMIN)
    @GetMapping("order")
    public String adminOrderPage(Model model) {
        return AdminUrlMapping.ADMIN_PREFIX + AdminUrlMapping.ADMIN_ORDER;
    }

    /**
     * Blog and case management
     *
     * @param model
     * @return
     */
    @Secured(UserRole.ADMIN)
    @GetMapping("paper")
    public String adminPaperPage(Model model) {
        return AdminUrlMapping.ADMIN_PREFIX + AdminUrlMapping.ADMIN_PAPER;
    }
}
