package com.zsy.dao;

import org.apache.ibatis.javassist.bytecode.annotation.IntegerMemberValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * use redis zset to manage most recently created MAX_NUM blog
 * use zset to compose MAX_NUM recent blogs from all followings
 */
@Repository
public class UserTimeLineDao {

    private final static String BLOG_TIMELINE_NAMESPACE = "blog_timeline:";
    private final static String FOLLOWING_BLOG_TIMELINE_NAMESPACE = "following_blog_timeline:";

    private final static int BLOG_MAX_NUM = 50;
    private final static int FOLOWING_BLOG_MAX_NUM = 20;

    @Autowired
    RedisTemplate redisTemplate;

    public void add(int userId, int blogId){
       String key = getBlogKey(userId);
       redisTemplate.opsForZSet().add(key, blogId, (double) System.currentTimeMillis());
       long size = redisTemplate.opsForZSet().size(key);
       if(size > BLOG_MAX_NUM){
           redisTemplate.opsForZSet().removeRange(key, -1, -1);
       }
    }

    public void delete(int userId, int blogId) {
        String key = getBlogKey(userId);
        redisTemplate.opsForZSet().remove(key, blogId);
    }

    public List<Integer> get(int userId, List<Integer> followingIds){
        String followBlogKey = getFollowingBlogKey(userId);
        double farthestTime = 0;
        if(redisTemplate.opsForZSet().size(followBlogKey) != 0) {
            Set<ZSetOperations.TypedTuple<Integer>> set = redisTemplate.opsForZSet().rangeWithScores(followBlogKey, -1, -1);
            for(ZSetOperations.TypedTuple<Integer> e: set){
                farthestTime = e.getScore();
            }
        }

        for (int id : followingIds){
            String key = getBlogKey(id);
            Set<ZSetOperations.TypedTuple<Integer>> set = redisTemplate.opsForZSet().rangeByScoreWithScores(key, farthestTime, Double.MAX_VALUE);
            for(ZSetOperations.TypedTuple<Integer> e: set){
                redisTemplate.opsForZSet().add(followBlogKey, e.getValue(), e.getScore());
            }
        }

        long size = redisTemplate.opsForZSet().size(followBlogKey);
        redisTemplate.opsForZSet().removeRange(followBlogKey, 0, size - FOLOWING_BLOG_MAX_NUM);

        List<ZSetOperations.TypedTuple<Integer>> list = new ArrayList<>(redisTemplate.opsForZSet().rangeWithScores(followBlogKey, 0, -1));
        list.sort((o1, o2) -> {
            if (o1.getScore() > o2.getScore()) return -1;
            else if (o1.getScore() < o2.getScore()) return 1;
            return 0;
        });
        List<Integer> res = new ArrayList<>();
        for (ZSetOperations.TypedTuple<Integer> e : list){
            res.add(e.getValue());
        }
        return res;
    }

    private String getBlogKey(int userId){
        return BLOG_TIMELINE_NAMESPACE + userId;
    }

    private String getFollowingBlogKey(int userId){
        return FOLLOWING_BLOG_TIMELINE_NAMESPACE + userId;
    }
}
