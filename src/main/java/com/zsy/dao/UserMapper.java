package com.zsy.dao;

import com.zsy.domain.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {

    @Delete("delete from user where user_id = #{id}")
    int deleteByid(int id);

    @Insert("INSERT INTO user(user_name, avatar_id, md5_password) VALUES(#{userName}, #{avatarId}, #{md5Password} )")
    int add(User user);

    @Select("SELECT user_id, user_name, avatar_id, md5_password from user where user_id = #{id}")
    @Results( id = "userResult", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "avatarId", column = "avatar_id"),
            @Result(property = "md5Password", column = "md5_password")
    })
    User selectById(int id);

    @Select("SELECT user_id, user_name, avatar_id, md5_password from user where user_name = #{name}")
    @Results( value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "avatarId", column = "avatar_id"),
            @Result(property = "md5Password", column = "md5_password")
    })
    User selectByName(String name);

    @Update("UPDATE user SET user_name=#{userName}, avatar_id=#{avatarId}, md5_password=#{md5Password} WHERE user_id = #{userId}")
    int updateUser(User user);
}
