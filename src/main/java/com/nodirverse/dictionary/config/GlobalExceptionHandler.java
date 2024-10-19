package com.nodirverse.dictionary.config;

import com.nodirverse.dictionary.dto.error.ErrorDTO;
import com.nodirverse.dictionary.exception.DataAlreadyExistsException;
import com.nodirverse.dictionary.exception.DataNotFoundException;
import com.nodirverse.dictionary.exception.NotEnoughFundsException;
import com.nodirverse.dictionary.exception.WrongPasswordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorDTO> dataNotFoundExceptionHandler(DataNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e.getMessage(), 404));
    }
    @ExceptionHandler(DataAlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> handleDataAlreadyExistsException(DataAlreadyExistsException  e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDTO(e.getMessage(), 409));
    }
    @ExceptionHandler(NotEnoughFundsException.class)
    public ResponseEntity<ErrorDTO> notEnoughFundsExceptionHandler(NotEnoughFundsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDTO(e.getMessage(), 401));
    }
    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ErrorDTO> wrongPasswordException(WrongPasswordException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage(), 400));
    }
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorDTO> bindExceptionHandler(BindException e) {
        e.getAllErrors().get(0).getDefaultMessage();

        StringBuilder errors = new StringBuilder();
        e.getAllErrors().forEach(error -> {
            errors.append(error.getDefaultMessage()).append("\n");
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(errors.toString(), 400));
    }
}
