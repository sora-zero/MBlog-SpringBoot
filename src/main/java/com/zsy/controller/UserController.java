package com.zsy.controller;

import com.zsy.service.impl.BlogServiceImpl;
import com.zsy.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BlogServiceImpl blogService;

}
