package com.paintballProject.paintballBack.news.model;

import java.sql.Date;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.paintballProject.paintballBack.category.model.Category;
import com.paintballProject.paintballBack.users.model.User;

import jakarta.persistence.*; // Para JPA en Spring Boot 3+
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "news") // Define el nombre de la tabla en la BD
public class News {
    // Estas anotaciones se usan para crear entidades JPA y poder acceder a ellas
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String content;

     @Column(nullable = false, length = 255)
    private String imageName;

     @Column(nullable = false, columnDefinition = "TEXT")
    private String image64;

    @NotNull(message = "El autor es obligatorio")
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User authorId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryId;

     @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime publishedAt;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @Column()
    private Boolean isFeatured;

    @CreationTimestamp
    protected void onCreate() {
        publishedAt = LocalDateTime.now();
    }

    @CreationTimestamp
    protected void onUpdate() {
        updatedAt =  LocalDateTime.now();
    }


    // Constructor vacío requerido por JPA
    public News() {
    }

    public News(String title, String description, String content, String imageName, String image64, Date publishedAt, Date updatedAt, Boolean isFeatured) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.imageName = imageName;
        this.image64 = image64;
        this.isFeatured = isFeatured;
        }
}
