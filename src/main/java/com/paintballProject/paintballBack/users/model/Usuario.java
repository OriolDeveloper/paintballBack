package com.paintballProject.paintballBack.users.model;

import jakarta.persistence.Entity;

import jakarta.persistence.*; // Para JPA en Spring Boot 3+
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuarios") // Define el nombre de la tabla en la BD
public class Usuario {
    //Estas anotaciones se usan para crear entidades JPA y poder acceder a ellas
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    // Constructor vacío requerido por JPA
    public Usuario() {}

    public Usuario(String nombre) {
        this.nombre = nombre;
    }
}
