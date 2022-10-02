package com.example.testcase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "CITIZEN NOT FOUND!")
public class NotFoundException {
    public NotFoundException() {
    }
}
