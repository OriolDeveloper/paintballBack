package com.paintballProject.paintballBack.news.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsRequest {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Long categoryId;
    private String imageName;
    private MultipartFile imageBytes;
    private Long authorId;
    private Boolean isFeatured;
}
