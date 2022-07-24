package com.example.demo.exception.handler;

import com.example.demo.dto.ErrorDto;
import com.example.demo.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto> handleNotFoundException
            (NotFoundException exception){

        ErrorDto errorDto = new ErrorDto();
        errorDto.setError(exception.getMessage());
        return  new ResponseEntity<>
                (errorDto, HttpStatus.NOT_FOUND);
    }
}
