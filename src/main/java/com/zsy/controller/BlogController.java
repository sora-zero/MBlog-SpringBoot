package com.zsy.controller;

import com.zsy.domain.User;
import com.zsy.service.impl.BlogServiceImpl;
import com.zsy.util.SessionUtil;
import com.zsy.util.XSSFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BlogController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    BlogServiceImpl blogService;

    final int MAX_BLOG_LEN = 1000;

    @RequestMapping("/publishBlog.html")
    public ModelAndView publishBlog(String blogContent){
        User user = SessionUtil.getUser(request);
        int len = blogContent.length();
        if(len > 0 && len < MAX_BLOG_LEN && user != null) {
            blogContent = XSSFilter.filterBrackets(blogContent);
            blogService.addOneBlog(user.getUserId(), blogContent);
        }

        return new ModelAndView("redirect:/index.html");
    }

    @RequestMapping("/deleteBlog.html")
    @ResponseBody
    public String deleteBlog(Integer blogId){
        User user = SessionUtil.getUser(request);
        if (user == null) return "error";
        blogService.deleteBlogById(user.getUserId(), blogId);
        return "success";
    }
}
