package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto postTodo(ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        // DB 저장
        Schedule postSchedule=scheduleRepository.save(schedule);


        // Entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto=new ScheduleResponseDto(postSchedule);

        return scheduleResponseDto;
    }

    public ScheduleResponseDto getTodo(Long id) {
        return new ScheduleResponseDto(scheduleRepository.getReferenceById(id));
    }
}
