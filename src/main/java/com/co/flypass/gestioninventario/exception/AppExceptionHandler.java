package com.co.flypass.gestioninventario.exception;

import com.co.flypass.gestioninventario.controller.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;


@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<Object> exceptionHandler(AppException exception) {

        if(exception instanceof NoDataFoundException){
            return buildResponseEntity(new Response<>(HttpStatus.NOT_FOUND.value(), exception.getExceptionMessage()), HttpStatus.NOT_FOUND);
        }
        return buildResponseEntity(new Response<>(HttpStatus.BAD_REQUEST.value(), exception.getExceptionMessage()), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> buildResponseEntity(Response<Void> responseObject, HttpStatus status) {
        return new ResponseEntity<>(responseObject, status);
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public ResponseEntity<String> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, WebRequest request) {
        return new ResponseEntity<>("Request timed out. Please try again.", HttpStatus.SERVICE_UNAVAILABLE);
    }

}