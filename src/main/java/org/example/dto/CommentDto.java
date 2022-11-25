package org.example.dto;

import lombok.Value;

@Value
public class CommentDto {
    private String text;
    private Integer userId;
    private Integer postId;
}
