package com.paintballProject.paintballBack.authentication.dto;

import com.paintballProject.paintballBack.users.dto.UserDto;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class AuthResponse {
    private String message;
    private UserDto user;
    private String token;
}