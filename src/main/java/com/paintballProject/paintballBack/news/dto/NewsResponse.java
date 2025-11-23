package com.paintballProject.paintballBack.news.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsResponse {
    NewsDto newsDto;
    private LocalDateTime publishedAt;
    private LocalDateTime updatedAt;
}
