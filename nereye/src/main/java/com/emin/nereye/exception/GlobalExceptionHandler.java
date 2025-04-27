package com.emin.nereye.exception;

import com.emin.nereye.Error.BaseError;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<BaseError> handleException(EntityNotFoundException ex) {

        BaseError error = BaseError.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timeStamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleGenericException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Bir hata oluştu: " + ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<BaseError> handleBadRequset(MethodArgumentTypeMismatchException ex) {
        BaseError error = BaseError.builder()
                .message("Lütfen Sayısal Bir Değer Girin")
                .status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}


