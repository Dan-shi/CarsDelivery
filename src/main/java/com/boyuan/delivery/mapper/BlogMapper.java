/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.mapper;

import com.boyuan.delivery.model.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogMapper {

    int createBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlogById(long blogId);

    Blog getBlogById(long blogId);

    int getPageCount(@Param("isActive")boolean isActive,
                     @Param("blogType")int blogType,
                     @Param("limit")int limit);

    int getAllPageCount(int blogType, int limit);

    List<Blog> getBlogsPage(@Param("isActive") boolean isActive,
                            @Param("blogType") int blogType,
                            @Param("offset") int offset,
                            @Param("limit") int limit);

    List<Blog> getAllBlogsPage(@Param("blogType") int blogType,
                               @Param("offset") int offset,
                               @Param("limit") int limit);

    List<Blog> getBlogsPageByBlogId(@Param("isActive") boolean isActive,
                                    @Param("blogType") int blogType,
                                    @Param("lastBlogId") long lastBlogId,
                                    @Param("limit") int limit);

    List<Blog> getAllBlogsByBlogId(@Param("blogType") int blogType,
                                   @Param("lastBlogId") long lastBlogId,
                                   @Param("limit") int limit);
}
