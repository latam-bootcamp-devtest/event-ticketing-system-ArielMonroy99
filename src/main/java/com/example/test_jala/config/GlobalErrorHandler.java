package com.example.test_jala.config;

import com.example.test_jala.dto.ResponseDto;
import com.example.test_jala.exceptions.BadRequestException;
import com.example.test_jala.exceptions.ConflictException;
import com.example.test_jala.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> handleRuntimeException (RuntimeException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(System.currentTimeMillis(),exception.getMessage(),400));
    }

    @ExceptionHandler({ConflictException.class})
    public ResponseEntity<?> handleConflictException (ConflictException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseDto(System.currentTimeMillis(),exception.getMessage(),409));
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<?> handleBadRequestException (BadRequestException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(System.currentTimeMillis(),exception.getMessage(),400));
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<?> handleNotFoundException (NotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto(System.currentTimeMillis(),exception.getMessage(),404));
    }
}
