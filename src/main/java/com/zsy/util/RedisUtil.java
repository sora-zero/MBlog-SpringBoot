package com.zsy.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public final class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * put key and value
     * @param key
     * @param value
     * @return true for success, false for failure
     */
    public boolean set(String key, Object value){
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * get value by key
     * @param key
     * @return deserialized object
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }


    /**
     * put values to set based on key
     * @param key
     * @param values
     * @return number of values successfully added to set
     */
    public long sadd(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * get values from set based on key
     * @param key
     * @return all values from set based on input key
     */
    public Set<Object> smembers(String key){
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
