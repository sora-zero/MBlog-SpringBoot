package com.zsy.service;

import com.zsy.domain.User;

import java.util.List;

public interface UserRelationService {

    void follow(int userId, int followId);

    List<User> getFollowers(int userId);

    List<User> getFollowings(int userId);
}
