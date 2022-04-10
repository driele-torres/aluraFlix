package com.torres.main.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiError {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();;
    private HttpStatus status;
    private String message;
    private String debugMessage;
    private List<ApiSubError> subErrors;

    public ApiError(HttpStatus status, String message, Throwable ex){
        this.status=status;
        this.message=message;
        this.debugMessage = ex.getMessage();
    }

    public ApiError(HttpStatus status, String message, List<ApiSubError> subErrors, Throwable ex){
        this.status=status;
        this.message=message;
        this.subErrors=subErrors;
        this.debugMessage=ex.getMessage();
    }
}
