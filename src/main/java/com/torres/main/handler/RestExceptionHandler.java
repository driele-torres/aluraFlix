package com.torres.main.handler;

import com.torres.main.exception.ApiError;
import com.torres.main.exception.ApiSubError;
import com.torres.main.exception.VideoNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.Path;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(VideoNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(VideoNotFoundException ex) {
        return buildResponseEntity(new ApiError(NOT_FOUND, "Video not found", ex));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        return buildResponseEntity(from(ex));
    }

    private ApiError from(ConstraintViolationException ex){
        List<ApiSubError> lst = new ArrayList<>();
        ex.getConstraintViolations().forEach((error) -> {
            String fieldName ="";
            Iterator<Path.Node> iterator = error.getPropertyPath().iterator();
            while (iterator.hasNext()){
                fieldName = iterator.next().toString();
            }
            lst.add(new ApiSubError("param", fieldName, error.getInvalidValue(), error.getMessage()));
        });
        return new ApiError(BAD_REQUEST, "One or more param invalid", lst, ex);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}