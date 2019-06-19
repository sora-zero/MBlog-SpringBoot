package com.zsy.dao;

import com.zsy.domain.Blog;
import com.zsy.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BlogCacheDao {

    private final static String BLOG_NAMESPACE = "blog:";

    @Autowired
    RedisUtil redisUtil;

    public boolean addBlog(Blog blog){
        String key = BLOG_NAMESPACE + blog.getBlogId();
        return redisUtil.set(key, blog);
    }

    public Blog getBlog(int blogId) {
        String key = BLOG_NAMESPACE + blogId;
        Object blog = redisUtil.get(key);

        return blog != null ? (Blog) blog: null;

    }
}
