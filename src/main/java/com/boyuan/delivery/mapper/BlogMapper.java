/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.mapper;

import com.boyuan.delivery.model.Blog;

import java.util.List;

public interface BlogMapper {

    int createBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlogById(long blogId);

    Blog getBlogById(long blogId);

    int getPageCount(int limit);

    List<Blog> getBlogListByPage(int offset, int limit);

    List<Blog> getBlogListByBlogId(long lastBlogId, int limit);
}
