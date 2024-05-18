package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
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

    public List<ScheduleResponseDto> getAllTodo() {
        return scheduleRepository.findAllByOrderByModifiedAtDesc().stream().map(ScheduleResponseDto::new).toList();
    }

    public ScheduleResponseDto updateTodo(ScheduleRequestDto requestDto, Long id) {
       // 해당 일정이 DB에 존재하는지 확인
       Schedule schedule = findSchedule(id);

       // 일정 내용 수정
       schedule.update(requestDto);

        return new ScheduleResponseDto(schedule);
    }

    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택한 일정이 없음")
        );
    }
}
