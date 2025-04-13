package com.paintballProject.paintballBack.users.mapper;


import com.paintballProject.paintballBack.users.model.Usuario;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface UsuarioMapper {
    @Select("SELECT * FROM users")
    List<Usuario> findAll();
}
