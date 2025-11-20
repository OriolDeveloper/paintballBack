package com.paintballProject.paintballBack.authentication.mapper;

import com.paintballProject.paintballBack.users.model.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface AuthenticationMapper {
    @Select("SELECT * FROM users")
    List<User> findAll();

    @Select("SELECT * FROM users where username = #{username} AND password = #{password}")
    Boolean getLogin();

    @Select("SELECT id, email, password, username FROM usuarios WHERE email = #{email}")
    User findByEmail(String email);
}
