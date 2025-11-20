package com.paintballProject.paintballBack.users.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paintballProject.paintballBack.users.dto.UserDto;
import com.paintballProject.paintballBack.users.model.User;
import com.paintballProject.paintballBack.users.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository; // JPA

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    /**
     * public List<UserDto> searchUsers(String keyword) {
     * return userMapper.searchByUsername(keyword).stream()
     * .map(this::toDto)
     * .toList();
     * }
     * 
     * public List<UserDto> searchAdvanced(String email, String role) {
     * Map<String, Object> filter = new HashMap<>();
     * filter.put("email", email);
     * filter.put("role", role);
     * 
     * return userMapper.searchAdvanced(filter).stream()
     * .map(this::toDto)
     * .toList();
     * }
     */

    private UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setRole_id(user.getRole_id());
        return dto;
    }
}
