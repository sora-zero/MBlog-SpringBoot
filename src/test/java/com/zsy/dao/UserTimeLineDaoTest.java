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
        int blogId = 1;
        userTimeLineDao.add(userId, blogId);
    }

    @Test
    public void deleteBlogFromUesrTimeLine(){
        int userId = 1;
        int blogId = 1;
        userTimeLineDao.delete(userId, blogId);
    }

    @Test
    public void getUserTimeLine(){
        int[][] userIdBlogIdArray = new int[][]{{}, {1}, {2}, {3}};
        for(int userId = 1; userId < userIdBlogIdArray.length; userId++){
            userTimeLineDao.add(userId, userIdBlogIdArray[userId][0]);
            try{
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        List<Integer> blogIds = userTimeLineDao.get(1,  Arrays.asList(2,3));
        System.out.println(blogIds);
    }


}
