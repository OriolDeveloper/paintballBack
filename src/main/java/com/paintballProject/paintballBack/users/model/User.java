package com.paintballProject.paintballBack.users.model;

import jakarta.persistence.*; // Para JPA en Spring Boot 3+
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users") // Define el nombre de la tabla en la BD
public class User {
    // Estas anotaciones se usan para crear entidades JPA y poder acceder a ellas
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    private String username;

    private int role_id;

    // Constructor vacío requerido por JPA
    public User() {
    }

    public User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }
}
