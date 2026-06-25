package com.student.student_management.exception;


public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException(String email){
        super("Student with email "+ email +  " already exists");
    }
}
