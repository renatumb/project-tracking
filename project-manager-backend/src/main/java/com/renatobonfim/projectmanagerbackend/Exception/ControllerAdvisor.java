package com.renatobonfim.projectmanagerbackend.exception;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.PropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity notFoundException(Exception ex) {
        log.warn(ex.getMessage());

        return new ResponseEntity(Map.of("message", ex.getMessage(), "timestamp", LocalDateTime.now().toString()), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({ InvalidFieldException.class,
                        IllegalArgumentException.class,
                        PropertyValueException.class,
                        DataIntegrityViolationException.class})
    public ResponseEntity InvalidFieldException(Exception ex) {
        log.warn(ex.getMessage());

        return new ResponseEntity(Map.of("message", ex.getMessage(), "timestamp", LocalDateTime.now().toString()), HttpStatus.BAD_REQUEST);
    }
}
