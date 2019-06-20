package com.zsy.service.impl;

import com.zsy.BaseApplicationTest;
import com.zsy.domain.Blog;
import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

public class BlogServiceImplTest extends BaseApplicationTest {
    @Autowired
    BlogServiceImpl blogService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private Flyway flyway;

    @Before
    public void setUpDatabase() {
        flyway.migrate();
    }

    @After
    public void cleanDatabase() {
        flyway.clean();
    }


    @Test
    public void getAllBlogForUserHomePage(){
       int userId = 1;
       List<Blog> blogs = blogService.getAllBlogForUserHomePage(userId);
    }


    @Test
    public void getAllBlogByUser(){
        int userId = 1;
        List<Blog> blogs = blogService.getAllBlogByUser(userId);
    }

    @Test
    public void getBlogByBlogId(){
        int blogId = 1;
        Blog blog = blogService.getBlogByBlogId(blogId);

    }

    @Test
    public void addOneBlog(){
        int userId = 1;
        String content = "addOneBlog test";
        blogService.addOneBlog(userId, content);

    }

    @Test
    public void editNoExistBlogShouldReturnFalse(){
        int blogId = 1;
        String content = "editBlog test";
        Assert.assertFalse(blogService.editBlog(blogId, content));
    }


    @Test
    public void deleteNotExistBlogSouldReturnFalse(){
        int userId = 1;
        int blogId = 1;
        blogService.deleteBlogById(userId, blogId);
    }
}
