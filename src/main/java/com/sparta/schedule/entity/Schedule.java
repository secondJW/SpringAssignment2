package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Getter
@NoArgsConstructor
@Table(name="schedule")
public class Schedule extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "todoTitle", nullable = false)
    private String todoTitle;
    @Column(name = "todoContent", nullable = false)
    private String todoContent;
    @Column(name = "manager", nullable = false)
    private String manager;
    @Column(name = "secrete", nullable = false)
    private String secrete;

    @OneToMany(mappedBy = "schedule")
    private List<Comment> commentList;


    public Schedule(ScheduleRequestDto requestDto) {
        this.todoTitle=requestDto.getTodoTitle();
        this.todoContent=requestDto.getTodoContent();
        this.manager=requestDto.getManager();
        this.secrete=requestDto.getSecrete();
    }

    public void update(ScheduleRequestDto requestDto) {
        this.todoTitle = requestDto.getTodoTitle();
        this.todoContent = requestDto.getTodoContent();
        this.manager=requestDto.getManager();
    }
}
