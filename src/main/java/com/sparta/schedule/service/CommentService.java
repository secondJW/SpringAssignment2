package com.sparta.schedule.service;

import com.sparta.schedule.dto.CommentRequestDto;
import com.sparta.schedule.dto.CommentResponseDto;
import com.sparta.schedule.entity.Comment;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.CommentRepository;
import com.sparta.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentResponseDto addComment(CommentRequestDto commentRequestDto) {
       Schedule schedule= scheduleRepository.findById(commentRequestDto.getSheduleId()).orElseThrow(()->
                new NullPointerException("해당 일정이 존재하지 않음")
                );
       if(commentRequestDto.getSheduleId()==null){
           throw new IllegalArgumentException("아이디를 입력 하지 않았음");
       }
       if(commentRequestDto.getContent()==null){
           throw new IllegalArgumentException("댓글 내용을 입력하지 않았음");
       }
       Comment comment=commentRepository.save(new Comment(commentRequestDto, schedule));
        return new CommentResponseDto(comment);
    }
}
