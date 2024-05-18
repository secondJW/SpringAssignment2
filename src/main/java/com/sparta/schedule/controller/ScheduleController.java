package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.service.ScheduleService;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService=scheduleService;
    }

    @PutMapping("/schedule")
    public ScheduleResponseDto postTodo(@RequestBody ScheduleRequestDto requestDto){
        return scheduleService.postTodo(requestDto);
    }

    @GetMapping("/schedule")
    public ScheduleResponseDto getTodo(@RequestParam Long id){
        return scheduleService.getTodo(id);
    }

    @GetMapping("/schedule/get")
    public List<ScheduleResponseDto> getAllTodo(){
        return scheduleService.getAllTodo();
    }

    @Transactional
    @PutMapping("/schedule/update")
    public ScheduleResponseDto updateTodo(@RequestBody ScheduleRequestDto requestDto, @RequestParam Long id){
        return scheduleService.updateTodo(requestDto, id);
    }
}
