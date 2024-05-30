package com.sparta.schedule.controller;

import com.sparta.schedule.dto.CommentRequestDto;
import com.sparta.schedule.dto.CommentResponseDto;
import com.sparta.schedule.entity.Comment;
import com.sparta.schedule.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/comment")
    public CommentResponseDto addComment(@RequestBody CommentRequestDto commentRequestDto){
        return commentService.addComment(commentRequestDto);
    }

    @PutMapping("/comment")
    public CommentResponseDto updateComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable Long commentId){
        return commentService.updateComment(commentRequestDto,commentId);
    }

    @DeleteMapping("/comment")
    public ResponseEntity<String> deleteComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable Long commentId){
        String successMessage="Success";
        return commentService.deleteComment(commentRequestDto, commentId);

    }
}
