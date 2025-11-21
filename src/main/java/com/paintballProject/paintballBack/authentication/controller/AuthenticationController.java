package com.paintballProject.paintballBack.authentication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import com.paintballProject.paintballBack.authentication.dto.AuthResponse;
import com.paintballProject.paintballBack.authentication.dto.RegisterRequest;
import com.paintballProject.paintballBack.authentication.service.AuthenticationService;
import com.paintballProject.paintballBack.common.constants.globalConstants;
import com.paintballProject.paintballBack.security.CustomUserDetails;
import com.paintballProject.paintballBack.users.dto.UserDto;

@RestController
@RequestMapping(globalConstants.ENDPOINT_AUTHENTICATION)
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        AuthResponse resp = authService.register(request);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/myUser")
    public ResponseEntity<UserDto> me(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        UserDto dto = new UserDto(user.getId(), user.getEmail(), user.getUsername(), user.getRoleId());
        return ResponseEntity.ok(dto);
    }
}
