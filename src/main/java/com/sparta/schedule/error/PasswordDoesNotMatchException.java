package com.sparta.schedule.error;



public class PasswordDoesNotMatchException extends RuntimeException {

    public PasswordDoesNotMatchException(String message){
        super(message);
    }
}
