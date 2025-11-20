package com.paintballProject.paintballBack.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import com.paintballProject.paintballBack.authentication.dto.AuthResponse;
import com.paintballProject.paintballBack.authentication.dto.LoginRequest;
import com.paintballProject.paintballBack.authentication.dto.RegisterRequest;
import com.paintballProject.paintballBack.authentication.service.AuthenticationService;
import com.paintballProject.paintballBack.common.constants.globalConstants;
import com.paintballProject.paintballBack.users.dto.UserDto;

@RestController
@RequestMapping(globalConstants.ENDPOINT_AUTHENTICATION)
@RequiredArgsConstructor
@CrossOrigin(origins = globalConstants.RUTA_FRONT, allowCredentials = "true")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        AuthResponse resp = authService.register(request);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequest request) {
        UserDto resp = authService.login(request);
        // IMPORTANTE: No devolvemos token. Con sesiones, Spring genera JSESSIONID
        // cuando la autenticación se realiza.
        // Aquí asumimos que el front gestiona la cookie si usamos AuthenticationManager
        // (opcional).
        return ResponseEntity.ok(resp);
    }
}