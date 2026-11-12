package com.workshop.studentmanagement1;

public class DuplicateEmailException extends RuntimeException{
    public  DuplicateEmailException(String message) {
        super(message);
    }
}
