package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;
@Getter
public class ScheduleResponseDto {
    private Long id;
    private String todoTitle;
    private String todoContent;
    private String manager;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    public ScheduleResponseDto(Schedule schedule) {
        this.id=schedule.getId();
        this.todoTitle=schedule.getTodoTitle();
        this.todoContent=schedule.getTodoContent();
        this.manager=schedule.getManager();
        this.createdAt=schedule.getCreatedAt();
        this.modifiedAt=schedule.getModifiedAt();
    }
}
