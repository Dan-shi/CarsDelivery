/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.service;

import com.boyuan.delivery.enumeration.ResultInfo;
import com.boyuan.delivery.model.Blog;

import java.util.List;

public interface BlogService {

    ResultInfo createBlog(Blog blog);

    ResultInfo updateBlog(Blog blog);

    ResultInfo deleteBlogById(long blogId);

    Blog getBlogById(long blogId);

    int getPageCount(int blogType, int limit);

    List<Blog> getBlogsByPage(int blogType, int pageNum, int limit);

    List<Blog> getBlogsByBlogId(int blogType, long lastBlogId, int limit);
}
