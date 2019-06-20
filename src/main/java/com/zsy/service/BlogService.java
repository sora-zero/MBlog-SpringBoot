package com.zsy.service;

import com.zsy.domain.Blog;
import com.zsy.domain.User;

import java.util.List;

public interface BlogService {

    List<Blog> getAllBlogForUserHomePage(int userId);

    List<Blog> getAllBlogByUser(int userId);

    Blog getBlogByBlogId(int blogId);

    void addOneBlog(int userId, String content);

    boolean editBlog(int blogId, String content);

    void deleteBlogById(int userId, int blogId);
}
