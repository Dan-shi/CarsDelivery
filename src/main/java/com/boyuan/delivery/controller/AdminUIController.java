package com.boyuan.delivery.controller;

import com.boyuan.delivery.constant.CommonConstant.UserRole;
import org.springframework.security.access.annotation.Secured;
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
     * News management
     *
     * @param model
     * @return
     */
    @Secured(UserRole.ADMIN)
    @GetMapping("news")
    public String adminBlogPage(Model model) {
        return AdminUrlMapping.ADMIN_PREFIX + AdminUrlMapping.ADMIN_NEWS;
    }

    /**
     * Case management
     *
     * @param model
     * @return
     */
    @Secured(UserRole.ADMIN)
    @GetMapping("case")
    public String adminCasePage(Model model) {
        return AdminUrlMapping.ADMIN_PREFIX + AdminUrlMapping.ADMIN_CASE;
    }
}
