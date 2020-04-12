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

    List<Blog> getBlogList(int startPage, int limitSize);

    List<Blog> getBlogList(long startBlogId, int limitSize);
}
