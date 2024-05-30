package com.sparta.schedule.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String content;
    private String manager;
    private Long sheduleId;
}
