package com.boyuan.delivery.controller;

import com.boyuan.delivery.model.BynUser;
import com.boyuan.delivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Secured("USER")
    @GetMapping("test")
    public String load() {
        return "This is my first blog";
    }

    @Secured("USER")
    @GetMapping("name")
    public BynUser getUserByUserName(@RequestParam(value = "username") String username){
        return userService.getUserByUserName(username);
    }
}