package com.example.demo.endpoint;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    protected @ResponseBody Error handleEntityNotFoundException(EntityNotFoundException ex) {
        Error error = new Error();
        error.setTimestamp(LocalDateTime.now());
        error.setMessage("Die Resource wurde nicht gefunden.");
        return error;
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected @ResponseBody Error handleException(Exception ex) {
        Error error = new Error();
        error.setTimestamp(LocalDateTime.now());
        error.setMessage("Internal Server Error");
        log.error(ex.getMessage(), ex);
        return error;
    }

    class Error {
        LocalDateTime timestamp;
        String message;

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
