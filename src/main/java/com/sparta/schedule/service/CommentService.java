package com.sparta.schedule.service;

import com.sparta.schedule.dto.CommentRequestDto;
import com.sparta.schedule.dto.CommentResponseDto;
import com.sparta.schedule.entity.Comment;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.CommentRepository;
import com.sparta.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service

public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final List<Schedule> existScheduleList;

    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository, List<Schedule> existScheduleList) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
        this.existScheduleList = scheduleRepository.findAll();
    }

    public CommentResponseDto addComment(CommentRequestDto commentRequestDto) {
       Schedule schedule= scheduleRepository.findById(commentRequestDto.getScheduleId()).orElseThrow(()->
                new NullPointerException("해당 일정이 존재하지 않음")
                );
       if(commentRequestDto.getScheduleId()==null){
           throw new IllegalArgumentException("아이디를 입력 하지 않았음");
       }
       if(commentRequestDto.getContent()==null){
           throw new IllegalArgumentException("댓글 내용을 입력하지 않았음");
       }
       Comment comment=commentRepository.save(new Comment(commentRequestDto, schedule));
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(CommentRequestDto commentRequestDto, Long commentId, String manager) {

        if(!isExistSchedule(commentRequestDto, existScheduleList)){
            throw new NullPointerException("해당 일정이 없음");
        }
        Comment comment= commentRepository.findById(commentId).orElseThrow(()->
                new NullPointerException("해당 댓글이 없음")
        );
        if(commentRequestDto.getScheduleId()==null || commentId==null){
            throw new IllegalArgumentException("일정 아이디를 입력 하지 않았거나 댓글 아이디를 입력하지 않았음");
        }
        if(!comment.getManager().equals(commentRequestDto.getManager())){
            throw new IllegalArgumentException("현재 사용자와 수정하려는 댓글의 사용자가 다름");
        }
        if(!commentRequestDto.getManager().equals(manager)){
            throw new IllegalArgumentException("작성자만 댓글 수정/삭제 할 수 있음");
        }
        comment.update(commentRequestDto);
        return new CommentResponseDto(comment);
    }

    public ResponseEntity<String> deleteComment(CommentRequestDto commentRequestDto, Long commentId, String manager) {

        if(!isExistSchedule(commentRequestDto, existScheduleList)){
            throw new NullPointerException("해당 일정이 없음");
        }
        Comment comment= commentRepository.findById(commentId).orElseThrow(()->
                new NullPointerException("해당 댓글이 없음")
        );
        if(commentRequestDto.getScheduleId()==null || commentId==null){
            throw new IllegalArgumentException("일정 아이디를 입력 하지 않았거나 댓글 아이디를 입력하지 않았음");
        }

        if(!comment.getManager().equals(commentRequestDto.getManager())){
            throw new IllegalArgumentException("현재 사용자와 삭제하려는 댓글의 사용자가 다름");
        }
        if(!commentRequestDto.getManager().equals(manager)){
            throw new IllegalArgumentException("작성자만 댓글 수정/삭제할 수 있음");
        }
        commentRepository.delete(comment);
        String successMessage="delete success";
        return new ResponseEntity<>(successMessage, HttpStatus.OK);

    }
    private boolean isExistSchedule(CommentRequestDto commentRequestDto, List<Schedule> existScheduleList){
        for (Schedule scheduleList : existScheduleList) {
            if(commentRequestDto.getScheduleId().equals(scheduleList.getId())){
                return true;
            }
        }
        return false;
    }
}
