package com.zsy.dao;

import com.zsy.domain.User;
import com.zsy.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Mange user relations using redis
 * Each user has a unique set for storing follower id
 * and a set for storing following id
 */
@Repository
public class UserRelationDao {

    private final static String FOLLOWER_NAMESPACE = "followers:";
    private final static String FOLLOWING_NAMESPACE = "following:";

    @Autowired
    RedisUtil redisUtil;

    public void addFollower(int userId, int followerId){
        String key = getFollowerNamespace(userId);

        redisUtil.sadd(key, followerId);
    }

    public void addFollowing(int userId, int followingId){
        String key = getFollowingNamespace(userId);
        redisUtil.sadd(key, followingId);
    }

    public List<Integer> getFollowersId(int userId){
        Set<Object> ret;
        String key = getFollowerNamespace(userId);
        ret = redisUtil.smembers(key);
        return convertObjectSet2IntegerList(ret);
    }

    public List<Integer> getFollowingsId(int userId){
        Set<Object> ret;
        String key = getFollowingNamespace(userId);
        ret = redisUtil.smembers(key);
        return convertObjectSet2IntegerList(ret);
    }

    private String getFollowerNamespace(int userId){
        return FOLLOWER_NAMESPACE + userId;
    }

    private String getFollowingNamespace(int userId){
        return FOLLOWING_NAMESPACE + userId;
    }

    private List<Integer> convertObjectSet2IntegerList(Set<Object> set){
        List<Integer> list = new LinkedList<>();
        for (Object o : set){
            list.add((Integer) o);
        }
        return list;
    }
}
