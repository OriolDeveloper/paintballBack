package com.paintballProject.paintballBack.category.model;


import jakarta.persistence.*; // Para JPA en Spring Boot 3+
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "category") // Define el nombre de la tabla en la BD
public class Category {
 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    // Constructor vacío requerido por JPA
    public Category() {
    }

    public Category(String name) {
        this.name = name;
        }
}
