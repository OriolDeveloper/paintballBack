package com.paintballProject.paintballBack.news.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.paintballProject.paintballBack.common.dto.CommentDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsResponse {
    NewsDto newsDto;
    private LocalDateTime publishedAt;
    private LocalDateTime updatedAt;
    private List<CommentDto> comments;
}
