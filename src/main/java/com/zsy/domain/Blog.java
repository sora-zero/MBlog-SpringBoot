package com.zsy.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Blog {
    private int blogId;
    private int userId;
    private Date publishTime;
    private Date updateTime;
    private String content;
}
