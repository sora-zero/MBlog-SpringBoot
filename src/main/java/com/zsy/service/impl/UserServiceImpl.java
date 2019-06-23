package com.zsy.service.impl;

import com.zsy.dao.UserMapper;
import com.zsy.domain.User;
import com.zsy.service.UserService;
import com.zsy.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void addUser(String username, String password, int avatarId) throws IllegalArgumentException{
        if(isUserNameValid(username) == false){
           throw new IllegalArgumentException("User name is not valid");
        }

        User user = new User();
        user.setUserName(username);
        user.setMd5Password(MD5.getMD5(password));
        user.setAvatarId(avatarId);
        userMapper.add(user);
    }

    @Override
    public boolean checkPassword(String userName, String password){
        User user = userMapper.selectByName(userName);
        String userPassword = user.getMd5Password();
        String pwdMd5 = MD5.getMD5(password);
        return userPassword.equals(pwdMd5);
    }

    @Override
    public boolean isUserNameValid(String username) {
        if(userMapper.selectByName(username) != null) return false;
        return true;
    }

    @Override
    public User getUserByName(String name){
        return userMapper.selectByName(name);
    }

    @Override
    public User getUserById(int id){
        return userMapper.selectById(id);
    }

    @Override
    public void login(User user){

    }

}

