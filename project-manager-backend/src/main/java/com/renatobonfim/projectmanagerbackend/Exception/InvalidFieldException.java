package com.renatobonfim.projectmanagerbackend.exception;

public class InvalidFieldException extends RuntimeException{
    public InvalidFieldException(String message) {
        super(message);
    }
}
