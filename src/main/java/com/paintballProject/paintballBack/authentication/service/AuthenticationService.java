package com.paintballProject.paintballBack.authentication.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.paintballProject.paintballBack.authentication.dto.AuthResponse;
import com.paintballProject.paintballBack.authentication.dto.LoginRequest;
import com.paintballProject.paintballBack.authentication.dto.RegisterRequest;
import com.paintballProject.paintballBack.authentication.mapper.AuthenticationMapper;
import com.paintballProject.paintballBack.users.dto.UserDto;
import com.paintballProject.paintballBack.users.model.User;
import com.paintballProject.paintballBack.users.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import com.paintballProject.paintballBack.authentication.service.AuthenticationService;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository; // JPA
    private final AuthenticationMapper authenticationMapper; // MyBatis
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El email ya está registrado");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El username ya está registrado");
        }
        User userRegister = new User();
        userRegister.setEmail(request.getEmail());
        userRegister.setUsername(request.getUsername());
        userRegister.setPassword(passwordEncoder.encode(request.getPassword()));
        userRegister.setRole_id(3);

        User saved = userRepository.save(userRegister);

        AuthResponse res = new AuthResponse();
        res.setMessage("Usuario registrado correctamente");
        res.setUser(userDto(saved));
        return res;
    }

    public UserDto login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Correo incorrecto"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Contraseña incorrecta");
        }

        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getRole_id());
    }

    private UserDto userDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setRole_id(user.getRole_id());
        return dto;
    }
}
