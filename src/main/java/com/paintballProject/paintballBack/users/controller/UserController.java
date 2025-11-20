package com.paintballProject.paintballBack.users.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.paintballProject.paintballBack.common.constants.globalConstants;
import com.paintballProject.paintballBack.users.dto.UserDto;
import com.paintballProject.paintballBack.users.model.User;
import com.paintballProject.paintballBack.users.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
@RequestMapping(globalConstants.ENDPOINT_USER)
@RequiredArgsConstructor
@CrossOrigin(origins = globalConstants.RUTA_FRONT, allowCredentials = "true")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/me")
    public UserDto me(Authentication auth) {
        if (auth == null)
            return null;
        UserDetails ud = (UserDetails) auth.getPrincipal();
        User user = userRepository.findByEmail(ud.getUsername()).orElse(null);
        if (user == null)
            return null;
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setRole_id(user.getRole_id());
        return dto;
    }
}