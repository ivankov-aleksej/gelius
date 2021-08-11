package ua.com.gelius.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ua.com.gelius.test.exception.ErrorInfo;
import ua.com.gelius.test.exception.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static ua.com.gelius.test.exception.ErrorType.*;

@RestControllerAdvice
public class ExceptionInfoHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorInfo> handleGenericNotFoundException(HttpServletRequest req, NotFoundException e) {
        ErrorInfo error = new ErrorInfo(LocalDateTime.now(), DATA_NOT_FOUND.getStatus().value(), DATA_NOT_FOUND.getErrorMessage(), e.getMessage(), req.getRequestURI());
        return new ResponseEntity<>(error, DATA_NOT_FOUND.getStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorInfo> handleGenericException(HttpServletRequest req, MethodArgumentTypeMismatchException e) {
        ErrorInfo error = new ErrorInfo(LocalDateTime.now(), BAD_REQUEST.getStatus().value(), BAD_REQUEST.getErrorMessage(), e.getMessage(), req.getRequestURI());
        return new ResponseEntity<>(error, BAD_REQUEST.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> handleGenericException(HttpServletRequest req, Exception e) {
        ErrorInfo error = new ErrorInfo(LocalDateTime.now(), APP_ERROR.getStatus().value(), APP_ERROR.getErrorMessage(), e.getMessage(), req.getRequestURI());
        return new ResponseEntity<>(error, APP_ERROR.getStatus());
    }
}
