package com.zsy.util;

import com.zsy.BaseApplicationTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisUtilTest extends BaseApplicationTest {

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void putKeyValue(){
        String key = "test_key";
        int value = 1;
        Assert.assertTrue(redisUtil.set(key, value));
    }
}
