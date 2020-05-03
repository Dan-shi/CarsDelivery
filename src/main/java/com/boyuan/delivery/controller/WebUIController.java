/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.controller;

import com.boyuan.delivery.constant.CommonConstant;
import com.boyuan.delivery.constant.CommonConstant.WebUrlMapping;
import com.boyuan.delivery.model.Blog;
import com.boyuan.delivery.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebUIController {

    @Autowired
    private BlogService blogService;

    /**
     * Index page
     *
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(Model model) {
        return WebUrlMapping.WEB_PREFIX + WebUrlMapping.WEB_INDEX;
    }

    /**
     * Make order page
     *
     * @param model
     * @return
     */
    @GetMapping("/boyuan/makeOrder")
    public String makeOrder(Model model) {
        return WebUrlMapping.WEB_PREFIX + WebUrlMapping.WEB_ORDER;
    }

    /**
     * About boyuan page
     *
     * @param model
     * @return
     */
    @GetMapping("/boyuan/about")
    public String about(Model model) {
        return WebUrlMapping.WEB_PREFIX + WebUrlMapping.WEB_ABOUT;
    }

    /**
     * Deliver cases page
     *
     * @param model
     * @return
     */
    @GetMapping("/boyuan/cases")
    public String cases(Model model) {
        List<Blog> cases = this.blogService.getBlogsPage(true, 1, 1, CommonConstant.Page.limit);
        model.addAttribute("cases", cases);
        return WebUrlMapping.WEB_PREFIX + WebUrlMapping.WEB_CASES;
    }

    /**
     * News page
     *
     * @param model
     * @return
     */
    @GetMapping("/boyuan/news")
    public String news(Model model) {
        List<Blog> news = this.blogService.getBlogsPage(true, 0, 1, 6);
        // As thymeleaf will convert date time
        model.addAttribute("news", news);
        return WebUrlMapping.WEB_PREFIX + WebUrlMapping.WEB_NEWS;
    }

    /**
     * News detail page
     *
     * @param blogId
     * @param model
     * @return
     */
    @GetMapping("/boyuan/newsDetail")
    public String newsDetail(@RequestParam(value = "blogId") Long blogId, Model model) {
        Blog blog = blogService.getBlogById(blogId);
        model.addAttribute("blog", blog);
        return WebUrlMapping.WEB_PREFIX + WebUrlMapping.WEB_NEWS_DETAIL;
    }

}
