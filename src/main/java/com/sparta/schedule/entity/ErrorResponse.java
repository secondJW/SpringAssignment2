package com.sparta.schedule.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class ErrorResponse {

    private String message;
    private HttpStatus httpStatus;
    private int statusCode;

   public ErrorResponse(String message, HttpStatus httpStatus){
       this.message=message;
       this.httpStatus=httpStatus;
       this.statusCode=httpStatus.value();
   }

}
