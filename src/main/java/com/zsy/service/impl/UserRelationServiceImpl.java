package com.zsy.service.impl;

import com.zsy.dao.UserMapper;
import com.zsy.dao.UserRelationDao;
import com.zsy.domain.User;
import com.zsy.service.UserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRelationServiceImpl implements UserRelationService {

    @Autowired
    private UserRelationDao userRelationDao;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void follow(int userId, int followId) {
        userRelationDao.addFollower(followId, userId);
        userRelationDao.addFollowing(userId, followId);
    }

    @Override
    public List<User> getFollowers(int userId){
        return getUserByIdList(userRelationDao.getFollowersId(userId));
    }

    @Override
    public List<User> getFollowings(int userId) {
        return getUserByIdList(userRelationDao.getFollowingsId(userId));
    }

    private List<User> getUserByIdList(List<Integer> ids){
        List<User> userList = new ArrayList<>(ids.size());
        for(int id : ids){
            User user = userMapper.selectById(id);
            userList.add(user);
        }
        return userList;
    }

}
