/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.controller;

import com.boyuan.delivery.constant.CommonConstant.WebUrlMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebUIController {
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("msg", "index");
        return WebUrlMapping.WEB_PREFIX + WebUrlMapping.WEB_INDEX;
    }

    @GetMapping("/boyuan/makeOrder")
    public String makeOrder(Model model){
        model.addAttribute("msg", "index");
        return WebUrlMapping.WEB_PREFIX + WebUrlMapping.WEB_ORDER;
    }

    @GetMapping("/boyuan/about")
    public String about(Model model){
        model.addAttribute("msg", "index");
        return WebUrlMapping.WEB_PREFIX + WebUrlMapping.WEB_ABOUT;
    }

    @GetMapping("/boyuan/cases")
    public String cases(Model model){
        model.addAttribute("msg", "index");
        return WebUrlMapping.WEB_PREFIX + WebUrlMapping.WEB_CASES;
    }

    @GetMapping("/boyuan/news")
    public String news(Model model){
        model.addAttribute("msg", "index");
        return WebUrlMapping.WEB_PREFIX + WebUrlMapping.WEB_NEWS;
    }

    @GetMapping("/boyuan/newsDetail")
    public String newsDetail(Model model){
        model.addAttribute("msg", "index");
        return WebUrlMapping.WEB_PREFIX + WebUrlMapping.WEB_NEWS_DETAIL;
    }

}
