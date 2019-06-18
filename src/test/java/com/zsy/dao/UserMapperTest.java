package com.zsy.dao;

import com.zsy.BaseApplicationTest;
import com.zsy.domain.User;
import com.zsy.util.MD5;
import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapperTest extends BaseApplicationTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Flyway flyway;

    @Before
    public void setUpDatabase() {
        flyway.migrate();
    }

    @After
    public void cleanDatabase() {
        flyway.clean();
    }

    @Test
    public void deleteOneUserById(){
        int userId = 42;
        userMapper.deleteByid(userId);
    }

    @Test
    public void addOneUser(){
        User user = new User();
        user.setAvatarId(1);
        user.setUserName("test_user");
        user.setMd5Password(MD5.getMD5("test"));
        userMapper.add(user);
    }

    @Test
    public void selectUserById(){
        int userId = 1;
        User user = userMapper.selectById(userId);
        Assert.assertEquals(user.getUserId(), userId);
    }

    @Test
    public void selectUserByName() {
        String name = "root";
        User user = userMapper.selectByName(name);
        Assert.assertEquals(user.getUserName(), name);
    }

    @Test
    public void updateUser() {
       User user = userMapper.selectById(41);
       user.setUserName("update test user name");
       userMapper.updateUser(user);
    }
}
