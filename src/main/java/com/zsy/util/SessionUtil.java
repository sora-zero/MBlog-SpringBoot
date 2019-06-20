package com.zsy.util;

import com.zsy.domain.User;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {

    private final static String USER_SESSION = "userSessionAttribute";

    public static User getUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(USER_SESSION);
    }

    public static void setUser(HttpServletRequest request, User user) {
        request.getSession().setAttribute(USER_SESSION, user);
    }

    public static void removeUser(HttpServletRequest request, User user) {
        request.getSession().removeAttribute(USER_SESSION);
    }


}
