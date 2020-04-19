/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.service;

import com.boyuan.delivery.common.ValidationUtils;
import com.boyuan.delivery.enumeration.CommonResult;
import com.boyuan.delivery.enumeration.ResultInfo;
import com.boyuan.delivery.mapper.BlogMapper;
import com.boyuan.delivery.model.Blog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = {RuntimeException.class, Error.class, Exception.class}, timeout = 500)
public class BlogServiceImpl implements BlogService {

    protected final Log logger = LogFactory.getLog(BlogServiceImpl.class);

    @Autowired
    private BlogMapper blogMapper;


    @Override
    public ResultInfo createBlog(Blog blog) {
        if (!ValidationUtils.validatorObjInfo(blog)) {
            return CommonResult.VALIDATION_ERROR;
        }

        if (this.blogMapper.createBlog(blog) <= 0) {
            return CommonResult.CREATE_ERROR;
        }

        return CommonResult.SUCCESS;
    }

    @Override
    public ResultInfo updateBlog(Blog blog) {
        if (!ValidationUtils.validatorObjInfo(blog) || blog.getBlogId() == null) {
            return CommonResult.VALIDATION_ERROR;
        }

        if (this.blogMapper.updateBlog(blog) <= 0) {
            return CommonResult.UPDATE_ERROR;
        }

        return CommonResult.SUCCESS;
    }

    @Override
    public ResultInfo deleteBlogById(long blogId) {
        if (this.blogMapper.deleteBlogById(blogId) <= 0) {
            return CommonResult.DELETE_ERROR;
        }

        return CommonResult.SUCCESS;
    }

    @Override
    public Blog getBlogById(long blogId) {
        return this.blogMapper.getBlogById(blogId);
    }

    @Override
    public int getPageCount(int limit) {
        return this.blogMapper.getPageCount(limit);
    }

    @Override
    public List<Blog> getBlogListByPage(int pageNum, int limit) {
        if (pageNum < 0 || limit < 0) {
            this.logger.error("getBlogListByPage parameter error");
            return new ArrayList<>();
        }
        return this.blogMapper.getBlogListByPage(pageNum * limit, limit);
    }

    @Override
    public List<Blog> getBlogListByBlogId(long lastBlogId, int limit) {
        if (lastBlogId < 0 || limit < 0) {
            this.logger.error("getBlogListByBlogId parameter error");
            return new ArrayList<>();
        }
        return this.blogMapper.getBlogListByBlogId(lastBlogId, limit);
    }

}
