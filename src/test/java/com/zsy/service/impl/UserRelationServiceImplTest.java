package com.zsy.service.impl;

import com.zsy.BaseApplicationTest;
import com.zsy.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRelationServiceImplTest extends BaseApplicationTest {

    @Autowired
    UserRelationServiceImpl userRelationService;

    @Test
    public void addFollower(){
        int userId = 1;
        int followId = 41;
        userRelationService.follow(userId, followId);
    }

    @Test
    public void getFollowers(){
        int userId = 41;
        List<User> followers = userRelationService.getFollowers(userId);
        Assertions.assertThat(followers).isNotEmpty();
    }


    @Test
    public void getFollowings(){
        int userId = 1;
        List<User> followings = userRelationService.getFollowings(userId);
        Assertions.assertThat(followings).isNotEmpty();
    }

}
