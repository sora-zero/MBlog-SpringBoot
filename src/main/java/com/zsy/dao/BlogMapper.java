package com.zsy.dao;

import com.zsy.domain.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogMapper {

    @Select("SELECT blog_id from blog where user_id = #{id}")
    @Results({
            @Result(property = "blogId", column = "blog_id")
    })
    List<Blog> selectAllByUserId(int id);

    @Select("SELECT blog_id from blog where blog_id = #{id}")
    @Results({
            @Result(property = "blogId", column = "blog_id")
    })
    Blog selectById(int id);

    @Delete("DELETE FROM blog where blog_id = #{id}")
    int deleteById(int id);

    @Insert("INSERT INTO blog(user_id, publish_time, update_time, content) VALUES (#{userId}, now(), now(), #{content})")
    @Options(useGeneratedKeys = true)
    int insert(Blog record);

    @Update("UPDATE blog set content=#{content}, update_time=now() where blog_id=#{blogId}")
    int updateContent(Blog record);
}
