package com.jeromerichard.pdfstream.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class NotFoundException extends Exception{
    public NotFoundException(String log) {
        super(log);
        new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
