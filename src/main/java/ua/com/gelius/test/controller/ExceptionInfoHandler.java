package ua.com.gelius.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.com.gelius.test.exception.ErrorInfo;
import ua.com.gelius.test.exception.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static ua.com.gelius.test.exception.ErrorType.DATA_NOT_FOUND;

@RestControllerAdvice
public class ExceptionInfoHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorInfo> handleGenericNotFoundException(HttpServletRequest req, NotFoundException e) {
        ErrorInfo error = new ErrorInfo(LocalDateTime.now(), DATA_NOT_FOUND.getStatus().value(), DATA_NOT_FOUND.getErrorMessage(), e.getMessage(), req.getRequestURI());
        return new ResponseEntity<>(error, DATA_NOT_FOUND.getStatus());
    }
}
