/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.controller;

import com.boyuan.delivery.constant.CommonConstant;
import com.boyuan.delivery.constant.CommonConstant.BlogType;
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
        List<Blog> cases = this.blogService.getBlogsPage(true, BlogType.CASES, 1, CommonConstant.Page.limit);
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
        List<Blog> news = this.blogService.getBlogsPage(true, BlogType.NEWS, 1, 6);
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
        assert blogId != null;
        Blog blog = this.blogService.getBlogById(blogId);
        List<Blog> blogsIdx = this.blogService.getBlogsIdx(blog.getBlogType());
        //find current blog index
        Blog currentBlog = blogsIdx.stream().filter(e -> blogId.equals(e.getBlogId())).findFirst().get();
        int curIdx = blogsIdx.indexOf(currentBlog);
        //set last blog index
        if (curIdx > 0) {
            model.addAttribute("lastBlog", blogsIdx.get(curIdx - 1));
        }
        //set nex blog index
        if (blogsIdx.size() > (curIdx + 1)) {
            model.addAttribute("nextBlog", blogsIdx.get(curIdx + 1));
        }
        model.addAttribute("blog", blog);
        return WebUrlMapping.WEB_PREFIX + WebUrlMapping.WEB_NEWS_DETAIL;
    }

}
