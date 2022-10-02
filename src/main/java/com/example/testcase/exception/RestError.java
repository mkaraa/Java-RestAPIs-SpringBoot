package com.example.testcase.exception;

import lombok.Data;

@Data
public class RestError {

    private int errorCode;
    private String errorMessage;

}