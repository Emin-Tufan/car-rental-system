package com.emintufan.carrentalsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EntityExceptions extends RuntimeException {
    public EntityExceptions(String message) {
        super(message);
    }
}
