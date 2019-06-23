package com.zsy.service.impl;

import com.zsy.dao.*;
import com.zsy.domain.Blog;
import com.zsy.domain.BlogDetail;
import com.zsy.domain.User;
import com.zsy.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BlogCacheDao blogCacheDao;

    @Autowired
    private CacheHitDao cacheHitDao;

    @Autowired
    private UserTimeLineDao userTimeLineDao;

    @Autowired
    private UserRelationDao userRelationDao;

    @Override
    public List<BlogDetail> getAllBlogForUserHomePage(int userId){

        List<Integer> followingIds = userRelationDao.getFollowingsId(userId);
        List<Integer> blogIds = userTimeLineDao.get(userId, followingIds);
        List<Blog> blogs = new ArrayList<>();
        for(int blogId : blogIds){
            blogs.add(getBlogByBlogId(blogId));
        }
        return blogToBlogDetail(blogs);
    }

    private List<BlogDetail> blogToBlogDetail(List<Blog> blogs){
        List<BlogDetail> blogDetails = new ArrayList<>(blogs.size());
        for(Blog blog : blogs){
            User user = userMapper.selectById(blog.getUserId());
            BlogDetail blogDetail = new BlogDetail(blog, user);
            blogDetails.add(blogDetail);
        }
        return blogDetails;

    }

    @Override
    public Blog getBlogByBlogId(int blogId){
        Blog blog = blogCacheDao.getBlog(blogId);
        if(blog == null){
            blog = blogMapper.selectById(blogId);
            cacheHitDao.miss();
            blogCacheDao.addBlog(blog);
        } else {
            cacheHitDao.hit();
        }
        return blog;
    }

    @Override
    public List<BlogDetail> getAllBlogByUser(int userId) {
        return blogToBlogDetail(blogMapper.selectAllByUserId(userId));
    }

    @Override
    public void addOneBlog(int userId, String content) {
        Blog blog = new Blog();
        blog.setUserId(userId);
        blog.setContent(content);
        blog.setPublishTime(new Date());
        blogMapper.insert(blog);
        blogCacheDao.addBlog(blog);
        userTimeLineDao.add(userId, blog.getBlogId());
    }

    @Override
    public boolean editBlog(int blogId, String content){
        Blog blog = getBlogByBlogId(blogId);
        if(blog == null){
           log.error("editBlog# blogId " + blogId + " not exits!");
           return false;
        }
        blog.setContent(content);
        blogMapper.updateContent(blog);
        return true;
    }

    @Override
    public void deleteBlogById(int userId, int blogId){
        blogMapper.deleteById(blogId);
        userTimeLineDao.delete(userId ,blogId);
    }
}
