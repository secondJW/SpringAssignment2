package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
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


    @PutMapping("/schedule/update")
    public ScheduleResponseDto updateTodo(@RequestBody ScheduleRequestDto requestDto, @RequestParam Long id, HttpServletRequest request){
        return scheduleService.updateTodo(requestDto, id, request.getAttribute("manager").toString());
    }

    @DeleteMapping("/schedule")
    public void deleteTodo(@RequestParam Long id, @RequestBody String secrete, HttpServletRequest request){
         scheduleService.deleteTodo(id, secrete, request.getAttribute("manager").toString());
    }
}
