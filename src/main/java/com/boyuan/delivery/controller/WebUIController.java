/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.controller;

import com.boyuan.delivery.constant.CommonConstant.WebUrlMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebUIController {
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("msg", "index");
        return WebUrlMapping.WEB_PREFIX + WebUrlMapping.WEB_INDEX;
    }
}
