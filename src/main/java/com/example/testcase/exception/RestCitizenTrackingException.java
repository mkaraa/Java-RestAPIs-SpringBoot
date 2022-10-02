package com.example.testcase.exception;


public class RestCitizenTrackingException extends RuntimeException{

    private Integer code;

    public RestCitizenTrackingException(String message){
        super(message);
    }
    public RestCitizenTrackingException(String message, int code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
