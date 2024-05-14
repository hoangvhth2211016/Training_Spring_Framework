package com.tasc.training.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class AlreadyExistException extends ResponseStatusException {
    
    public AlreadyExistException(String reason) {
        super(HttpStatus.CONFLICT, reason);
    }
}
