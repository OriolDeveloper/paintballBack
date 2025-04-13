package com.paintballProject.paintballBack.users.model.Interface;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paintballProject.paintballBack.users.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
