package com.jeromerichard.pdfstream.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EmptyListException extends Exception{
    public EmptyListException(String log) {
        super(log);
        new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
