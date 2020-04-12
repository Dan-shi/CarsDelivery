/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.controller;

import com.boyuan.delivery.common.ResultUtils;
import com.boyuan.delivery.constant.CommonConstant;
import com.boyuan.delivery.model.Blog;
import com.boyuan.delivery.model.Result;
import com.boyuan.delivery.service.BlogService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("blog")
public class BlogController {

    protected final Log logger = LogFactory.getLog(BlogController.class);

    @Autowired
    private BlogService blogService;

    /**
     * Create new blog
     *
     * @param blog
     * @return
     */
    @PostMapping("create")
    public Result createBlog(@RequestBody Blog blog) {

        try {
            return ResultUtils.buildResultByResultInfo(this.blogService.createBlog(blog));
        } catch (Exception e) {
            logger.error("CreateOrder error", e);
            return ResultUtils.buildErrorResult(e.getMessage());
        }

    }

    /**
     * Update order
     *
     * @param blog
     * @return
     */
    @PostMapping("update")
    public Result updateBlog(@RequestBody Blog blog) {

        try {
            return ResultUtils.buildResultByResultInfo(this.blogService.updateBlog(blog));
        } catch (Exception e) {
            logger.error("UpdateBlog error", e);
            return ResultUtils.buildErrorResult(e.getMessage());
        }

    }

    /**
     * Delete blog
     *
     * @param blogId
     * @return
     */
    @GetMapping("delete")
    public Result deleteBlog(@PathVariable(value = "blogId") Long blogId) {

        try {
            return ResultUtils.buildResultByResultInfo(this.blogService.deleteBlogById(blogId));
        } catch (Exception e) {
            logger.error("DeleteBlog error", e);
            return ResultUtils.buildErrorResult(e.getMessage());
        }

    }

    /**
     * Get blog by order id
     *
     * @param blogId
     * @return
     */
    @GetMapping("getBlog")
    public Result getBlogById(@PathVariable(value = "blogId") Long blogId) {
        try {
            return ResultUtils.buildResultWithBody(this.blogService.getBlogById(blogId));
        } catch (Exception e) {
            logger.error("Get blog error", e);
            return ResultUtils.buildErrorResult(e.getMessage());
        }
    }
}
