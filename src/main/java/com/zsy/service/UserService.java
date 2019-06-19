package com.zsy.service;

import com.zsy.domain.User;

public interface UserService {

    void addUser(String username, String password, int avatarId) throws IllegalArgumentException;

    boolean checkPassword(User user, String password);

    boolean isUserNameValid(String username);

    User getUserByName(String name);

    User getUserById(int id);

    void login(User user);
}
