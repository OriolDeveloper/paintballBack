package com.paintballProject.paintballBack.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paintballProject.paintballBack.users.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    boolean existsByUsername(String email);

    Optional<User> findByEmail(String email);
}
