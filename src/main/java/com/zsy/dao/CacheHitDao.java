package com.zsy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CacheHitDao {

    @Autowired
    RedisTemplate redisTemplate;

    private static final String CACHE_HIT_KEY = "cache_hit";
    private static final String CACHE_MISS_KEY = "cache_miss";

    public void hit(){
        try {
            redisTemplate.opsForValue().increment(CACHE_HIT_KEY);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void miss() {
        try {
            redisTemplate.opsForValue().increment(CACHE_MISS_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
