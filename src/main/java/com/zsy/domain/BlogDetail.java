package com.zsy.domain;

import lombok.Data;

@Data
public class BlogDetail {
    private Blog blog;
    private User user;

    public BlogDetail(Blog blog, User user){
        this.blog = blog;
        this.user = user;
    }
}
