package com.buoyuan.delivery.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Secured("USER")
    @GetMapping("test")
    public String load() {
        return "This is my first blog";
    }

//    public BynUser getUserByUserName(String userName){
//
//    }
}
