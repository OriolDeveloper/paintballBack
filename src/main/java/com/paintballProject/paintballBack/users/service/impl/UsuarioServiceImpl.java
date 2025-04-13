package com.paintballProject.paintballBack.users.service.impl;

import com.paintballProject.paintballBack.users.mapper.UsuarioMapper;
import com.paintballProject.paintballBack.users.model.Usuario;
import com.paintballProject.paintballBack.users.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private final UsuarioMapper mapper;

    // Inyección de dependencias mediante constructor
    public UsuarioServiceImpl(UsuarioMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return mapper.findAll();
    }
}