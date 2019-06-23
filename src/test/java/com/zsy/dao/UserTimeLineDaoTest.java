package com.zsy.dao;

import com.zsy.BaseApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserTimeLineDaoTest extends BaseApplicationTest {

    @Autowired
    UserTimeLineDao userTimeLineDao;

    @Test
    public void addBlogToUserTimeLine(){
        int userId = 1;
        int blogId = 13;
        userTimeLineDao.add(userId, blogId);
    }

    @Test
    public void deleteBlogFromUserTimeLine(){
        int userId = 1;
        int blogId = 13;
        userTimeLineDao.delete(userId, blogId);
    }

    @Test
    public void getUserTimeLine(){
        try{
            userTimeLineDao.add(1, 13);
            TimeUnit.SECONDS.sleep(1);
            userTimeLineDao.add(41, 12);
            TimeUnit.SECONDS.sleep(1);
            userTimeLineDao.add(43, 21);
        } catch (Exception e){

        }
        List<Integer> blogIds = userTimeLineDao.get(1,  Arrays.asList(41,43));
        System.out.println(blogIds);
    }


}
