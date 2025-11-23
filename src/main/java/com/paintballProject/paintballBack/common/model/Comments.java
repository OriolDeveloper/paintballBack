package com.paintballProject.paintballBack.common.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.paintballProject.paintballBack.news.model.News;
import com.paintballProject.paintballBack.users.model.User;

import jakarta.persistence.*; // Para JPA en Spring Boot 3+
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "target_type", nullable = false)
    private String targetType; // "news" o "forum"

    @Column(name = "target_id", nullable = false)
    private Long targetId; // id de noticia o hilo de foro

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comments parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comments> replies = new ArrayList<>();
}
