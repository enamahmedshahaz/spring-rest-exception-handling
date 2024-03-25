package com.shahaz.springrestexception.rest;


//step 1: create a custom error response class

public class StudentErrorResponse {

    private int Status;
    private String message;
    private long timestamp;


    public StudentErrorResponse(){

    }


    public StudentErrorResponse(int status, String message, long timestamp) {
        Status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
