package com.zsy.dao;

import com.zsy.BaseApplicationTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRelationDaoTest extends BaseApplicationTest {

    @Autowired
    UserRelationDao userRelationDao;

    private static final int userId = 1;
    private static final int followerId = 42;
    private static final int followingId = 42;

    @Test
    public void addOneFollower(){
        userRelationDao.addFollower(userId, followerId);
    }

    @Test
    public void addOneFollowing(){
        userRelationDao.addFollowing(userId, followingId);
    }

    @Test
    public void getFollowerList(){
        List<Integer> list = userRelationDao.getFollowersId(userId);
        Assert.assertTrue(list.contains(followerId));
    }

    @Test
    public void getFollowingList(){
        List<Integer> list = userRelationDao.getFollowingsId(userId);
        Assert.assertTrue(list.contains(followingId));
    }
}
