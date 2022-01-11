package com.example.fourthhomework.exception;

public class UserIsNotExistException extends RuntimeException{
    public UserIsNotExistException(String message) {
        super(message);
    }
}
