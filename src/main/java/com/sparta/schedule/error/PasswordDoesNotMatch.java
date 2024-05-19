package com.sparta.schedule.error;



public class PasswordDoesNotMatch extends RuntimeException {

    public PasswordDoesNotMatch(String message){
        super(message);
    }
}
