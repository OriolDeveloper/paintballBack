package com.paintballProject.paintballBack.news.dto;

import lombok.Setter;

import com.paintballProject.paintballBack.category.model.Category;
import com.paintballProject.paintballBack.users.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {
    private Long id;
    private String title;
    private String description;
    private String content;
    private String categoryId;
    private String authorId;
    private String imageName;
    private String image64;
    private Boolean isFeatured;
}
