package com.zsy.service.impl;

import com.zsy.BaseApplicationTest;
import com.zsy.domain.User;
import com.zsy.service.UserService;
import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImplTest extends BaseApplicationTest {
    @Autowired
    UserServiceImpl userService;

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
    public void addOneUserWithValidInputShouldNotThrowException(){
        String username = "test_user_name";
        String password = "password";
        int avatarId = 1;
        try{
            userService.addUser(username, password, avatarId);
        } catch (Exception e){
            Assert.fail("should not throw exception!");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void addOneUserWithInvalidInputShouldThrowException(){
        String username = "root";
        String password = "password";
        int avatarId = 1;
        userService.addUser(username, password, avatarId);
    }

    @Test
    public void checkPassowrdIsValid(){
        String username = "root";
        User user = userService.getUserByName(username);
        String password = "test";
        Assert.assertTrue(userService.checkPassword(user, password));
    }

    @Test
    public void checkPassowrdIsInvalid(){
        String username = "root";
        User user = userService.getUserByName(username);
        String password = "incorrect";
        Assert.assertFalse(userService.checkPassword(user, password));
    }

    @Test
    public void userNameIsValidIfNotExist(){
        String username = "not_exist";
        Assert.assertTrue(userService.isUserNameValid(username));
    }

    @Test
    public void userNameIsNotValidIfExist(){
        String username = "root";
        Assert.assertFalse(userService.isUserNameValid(username));
    }

    @Test
    public void getOneUserByName(){
        String username = "root";
        User user = userService.getUserByName(username);
        Assert.assertEquals(user.getUserName(), username);
    }

    @Test
    public void getOneUserById(){
        int id = 1;
        User user = userService.getUserById(id);
        Assert.assertEquals(user.getUserId(), id);
    }

}
