package com.zsy.dao;

import com.zsy.BaseApplicationTest;
import com.zsy.domain.Blog;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BlogCacheDaoTest extends BaseApplicationTest {

    @Autowired
    BlogCacheDao blogCacheDao;

    @Test
    public void addBlogToCache(){
        int blogId = 100;
        Blog blog = new Blog();
        blog.setBlogId(blogId);
        Assert.assertTrue(blogCacheDao.addBlog(blog));
    }

    @Test
    public  void getBlogFromCache(){
        int blogId = 100;
        Assert.assertNotNull(blogCacheDao.getBlog(blogId));
    }
}
