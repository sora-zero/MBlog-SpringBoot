package com.zsy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zsy.dao")
public class MBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MBlogApplication.class, args);
    }

}
