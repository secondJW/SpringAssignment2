package com.sparta.schedule.controller;

import com.sparta.schedule.dto.CommentRequestDto;
import com.sparta.schedule.dto.CommentResponseDto;
import com.sparta.schedule.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
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
    public CommentResponseDto updateComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable Long commentId, HttpServletRequest request){
        return commentService.updateComment(commentRequestDto,commentId, request.getAttribute("manager").toString());
    }

    @DeleteMapping("/comment")
    public ResponseEntity<String> deleteComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable Long commentId,  HttpServletRequest request){
        String successMessage="Success delete";
        return commentService.deleteComment(commentRequestDto, commentId, request.getAttribute("manager").toString());

    }
}
