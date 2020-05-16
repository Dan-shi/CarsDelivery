/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.controller;

import com.boyuan.delivery.constant.CommonConstant.WebUrlMapping;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorPageController implements ErrorController {
    @Override
    public String getErrorPath() {
        return WebUrlMapping.WEB_PREFIX + WebUrlMapping.WEB_ERROR;
    }

    @RequestMapping
    public String error() {
        return getErrorPath();
    }
}
