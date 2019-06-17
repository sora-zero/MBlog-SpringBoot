package com.zsy.dao;

import com.zsy.BaseApplicationTest;
import com.zsy.domain.Blog;
import org.flywaydb.core.Flyway;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BlogMapperTest extends BaseApplicationTest {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private Flyway flyway;

    @Before
    public void setUpDatabase() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void selectAllBlogByUser1() {
        List<Blog> blogs = blogMapper.selectAllByUserId(41);
        Assert.assertTrue(blogs.size() > 0);
    }

    @Test
    public void selectOneBlogById(){
        int blogId = 13;
        Blog blog = blogMapper.selectById(blogId);
        Assert.assertEquals(blog.getBlogId(), blogId);
    }

    @Test
    public void deleteOneBlogByUser1(){
        int blogId = 13;

        Blog blog = blogMapper.selectById(blogId);
        blogMapper.deleteById(blogId);
    }

    @Test
    public void insertOneBlog(){
        Blog blog = new Blog();
        blog.setUserId(1);
        blog.setContent("blog insert test");
        blogMapper.insert(blog);
    }

    @Test
    public void updateBlogContent(){
        int blogId = 12;
        Blog blog = blogMapper.selectById(blogId);
        blog.setContent("update content test");
        blogMapper.updateContent(blog);
    }
}
