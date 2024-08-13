package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import java.net.http.HttpResponse;
import java.util.Date;

public class ErrorResponse {
    public Date timestamp;
    public HttpStatus status;
    public   String message;
    public ErrorResponse(EmployeeNotFoundException ex){
        this.message = ex.getMessage();
        this.status = HttpStatus.NOT_FOUND;
        this.timestamp = new Date();

    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "timestamp=" + timestamp +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}