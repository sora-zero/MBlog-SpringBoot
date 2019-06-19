package com.zsy.dao;

import com.zsy.BaseApplicationTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRelationDaoTest extends BaseApplicationTest {

    @Autowired
    UserRelationDao userRelationDao;

    @Test
    public void addOneFollower(){
        int userId = 1;
        int followerId = 2;
        userRelationDao.addFollower(userId, followerId);
    }

    @Test
    public void addOneFollowing(){
        int userId = 1;
        int followingId = 2;
        userRelationDao.addFollowing(userId, followingId);
    }

    @Test
    public void getFollowerList(){
        int userId = 1;
        List<Integer> list = userRelationDao.getFollowersId(userId);
        Assert.assertTrue(list.contains(2));
    }

    @Test
    public void getFollowingList(){
        int userId = 1;
        List<Integer> list = userRelationDao.getFollowingId(userId);
        Assert.assertTrue(list.contains(2));
    }
}
