/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.controller;

import com.boyuan.delivery.common.utility.ResultUtils;
import com.boyuan.delivery.constant.CommonConstant;
import com.boyuan.delivery.constant.CommonConstant.Permission;
import com.boyuan.delivery.model.Blog;
import com.boyuan.delivery.model.Result;
import com.boyuan.delivery.service.BlogService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Secured(Permission.ADMIN)
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
    @Secured(Permission.ADMIN)
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
    @Secured(Permission.ADMIN)
    @GetMapping("delete")
    public Result deleteBlog(@RequestParam(value = "blogId") Long blogId) {

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
    @Secured(Permission.VIEW)
    @GetMapping("getBlog")
    public Result getBlogById(@RequestParam(value = "blogId") Long blogId) {
        try {
            return ResultUtils.buildResultWithBody(this.blogService.getBlogById(blogId));
        } catch (Exception e) {
            logger.error("Get blog error", e);
            return ResultUtils.buildErrorResult(e.getMessage());
        }
    }


    /**
     * Get page total number
     *
     * @return
     */
    @Secured(Permission.VIEW)
    @GetMapping("getPageCount")
    public Result getPageCount(@RequestParam(value = "isActive") boolean isActive,
                               @RequestParam(value = "blogType") int blogType) {
        try {
            return ResultUtils.buildResultWithBody(this.blogService.getPageCount(isActive, blogType, CommonConstant.Page.limit));
        } catch (Exception e) {
            logger.error("Get page count error", e);
            return ResultUtils.buildErrorResult(e.getMessage());
        }
    }

    /**
     * Get blog list by page number
     *
     * @return
     */
    @Secured(Permission.VIEW)
    @GetMapping("getBlogsPage")
    public Result getBlogsPage(@RequestParam(value = "isActive") boolean isActive,
                               @RequestParam(value = "blogType") int blogType,
                               @RequestParam(value = "pageNum") int pageNum) {
        try {
            return ResultUtils.buildResultWithBody(this.blogService.getBlogsPage(isActive, blogType, pageNum, CommonConstant.Page.limit));
        } catch (Exception e) {
            logger.error("Get blog by page number error", e);
            return ResultUtils.buildErrorResult(e.getMessage());
        }
    }

    /**
     * Get blog list by last blog primary id
     *
     * @param lastBlogId
     * @return
     */
    @Secured(Permission.VIEW)
    @GetMapping("getBlogsByBlogId")
    public Result getBlogsByBlogId(@RequestParam(value = "isActive") boolean isActive,
                                   @RequestParam(value = "blogType") int blogType,
                                   @RequestParam(value = "blogId") long lastBlogId) {
        try {
            return ResultUtils.buildResultWithBody(this.blogService.getBlogsPageByBlogId(isActive, blogType, lastBlogId, CommonConstant.Page.limit));
        } catch (Exception e) {
            logger.error("Get blog by last blog id error", e);
            return ResultUtils.buildErrorResult(e.getMessage());
        }
    }
}
