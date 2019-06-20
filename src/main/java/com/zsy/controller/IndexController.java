package com.zsy.controller;

import com.zsy.domain.Blog;
import com.zsy.domain.User;
import com.zsy.service.impl.BlogServiceImpl;
import com.zsy.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogServiceImpl blogService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = {"/", "/index.html"})
    public ModelAndView showIndexPage() {
        User user = SessionUtil.getUser(request);
        if(user == null) {
            return new ModelAndView("redirect:/login.html");
        }
        ModelAndView modelAndView = new ModelAndView("index");
        List<Blog> blogs = blogService.getAllBlogByUser(user.getUserId());
        modelAndView.addObject("blogs", blogs);
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
