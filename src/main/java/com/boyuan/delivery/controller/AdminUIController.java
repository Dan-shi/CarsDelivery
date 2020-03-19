package com.boyuan.delivery.controller;

import com.boyuan.delivery.constant.CommonConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminUIController {

    @GetMapping("login")
    public String adminLogin(Model model){
        model.addAttribute("msg", "一段信息");
        return CommonConstant.AdminUrlMapping.ADMIN_LOGIN;
    }
}
