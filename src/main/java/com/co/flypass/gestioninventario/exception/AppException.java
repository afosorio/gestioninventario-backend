package com.co.flypass.gestioninventario.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppException extends RuntimeException {

    private String msg;

    public AppException(String message, Throwable ex){
        super(message,ex);
    }

    public AppException(String msg){
        super(msg);
        this.msg = msg;
    }

    public String getExceptionMessage() {
        return this.msg != null ? this.msg : "Error procesando su solicitud";
    }

    public AppException(){}

}