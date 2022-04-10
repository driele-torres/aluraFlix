package com.torres.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Video not found")
public class VideoNotFoundException extends RuntimeException {
    public VideoNotFoundException(String id) {
        super("Could not find video " + id);
    }
}
