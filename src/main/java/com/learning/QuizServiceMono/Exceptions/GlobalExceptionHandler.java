package com.learning.QuizServiceMono.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleNotFoundException(
            NotFoundException notFoundException
    ){
        return new ResponseEntity<>(new ExceptionDTO(notFoundException.getMessage(),
                HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }
}
