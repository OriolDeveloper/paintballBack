package com.paintballProject.paintballBack.common.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private Long id;
    private String username;
    private String content;
    private Long parentId;
    private LocalDateTime createdAt;
    private List<CommentDto> replies = new ArrayList<>();
}
