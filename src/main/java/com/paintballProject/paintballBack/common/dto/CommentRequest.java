package com.paintballProject.paintballBack.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {
    private Long userId;
    private Long parentId;
    private Long newsId;
    private Long forumId;
    private String content;
}
