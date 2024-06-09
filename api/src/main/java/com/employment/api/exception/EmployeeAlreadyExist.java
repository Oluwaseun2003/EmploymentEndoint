package com.employment.api.exception;

public class EmployeeAlreadyExist extends RuntimeException {
    public EmployeeAlreadyExist(String message){
        super(message);
    }
}
