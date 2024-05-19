package com.sparta.schedule.error;

import com.sparta.schedule.entity.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e){
        return new ResponseEntity<>(new ErrorResponse("찾는 아이디 없음", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(PasswordDoesNotMatch.class)
    public ResponseEntity<ErrorResponse> handlePassWordDoesNotMatch(PasswordDoesNotMatch e){
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

}
