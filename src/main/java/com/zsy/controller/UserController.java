package com.zsy.controller;

import com.zsy.domain.User;
import com.zsy.service.UserRelationService;
import com.zsy.service.impl.BlogServiceImpl;
import com.zsy.service.impl.UserServiceImpl;
import com.zsy.util.AvatarUtil;
import com.zsy.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BlogServiceImpl blogService;

    @Autowired
    private UserRelationService userRelationService;

    @GetMapping("/login.html")
    public ModelAndView showLoginPage(Integer page){
        if (page == null){
            page = 0;
        }
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("page", page);
        return modelAndView;
    }

    @PostMapping("/login.html")
    public ModelAndView checkLogin(String userName, String password){
        boolean isValidUser = userService.checkPassword(userName, password);
        if (!isValidUser) {
            return new ModelAndView("redirect:/login.html", "error", "username or password error");
        }
        User user = userService.getUserByName(userName);
        SessionUtil.setUser(request, user);
        return new ModelAndView("redirect:/index.html");
    }

    @RequestMapping(value = "/logout.html")
    public String logout(){
        SessionUtil.removeUser(request);
        return "redirect:/login.html";
    }

    @GetMapping("/getUserAvatar.html")
    @ResponseBody
    public String getUserAvatarId(String userName){
        User user = userService.getUserByName(userName);
        if(user != null){
            return user.getAvatarId() + "";
        } else {
            return AvatarUtil.NOT_FOUND + "";
        }

    }

    @PostMapping("/register.html")
    public ModelAndView register(String userName, String password, int avatarId){
        boolean isValidUserName = userService.isUserNameValid(userName);
        ModelAndView modelAndView = new ModelAndView();
        if(!isValidUserName){
            modelAndView.setViewName("/register.html");
            modelAndView.addObject("error", "User name is occupied");
        } else {
            userService.addUser(userName, password, avatarId);
            User user = userService.getUserByName(userName);
            SessionUtil.setUser(request, user);
            modelAndView.setViewName("redirect:/index.html");
        }
        return modelAndView;
    }

    @GetMapping("/user.html")
    public ModelAndView showUserPage(Integer userId, String contentType){
        ModelAndView modelAndView = new ModelAndView("user");
        User sessionUser = SessionUtil.getUser(request);
        modelAndView.addObject("sessionUser", sessionUser);
        User user;
        if(userId == null){
            user = sessionUser;
            userId = user.getUserId();
        } else {
            user = userService.getUserById(userId);
        }
        modelAndView.addObject("user", user);
        if(contentType == null){
            contentType = "blog";
        }
        switch(contentType){
            case "blog":
                modelAndView.addObject("blogDetails", blogService.getAllBlogByUser(userId));
                break;
            case "followers":
                modelAndView.addObject("follows", userRelationService.getFollowers(userId));
                break;
            case "followings":
                modelAndView.addObject("follows", userRelationService.getFollowings(userId));
                break;
            default:
                modelAndView.addObject("error", "Unknown content type: " + contentType);
                modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        }
        modelAndView.addObject("contentType", contentType);
        return modelAndView;
    }

    @GetMapping("/follow.html")
    public String follow(Integer userId){
        User user = SessionUtil.getUser(request);
        userRelationService.follow(user.getUserId(), userId);
        return "redirect:/user.html?userId=" + userId;
    }

}
