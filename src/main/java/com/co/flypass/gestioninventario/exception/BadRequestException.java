package com.co.flypass.gestioninventario.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class BadRequestException extends Exception {

    private HttpStatus status;
    private List<String> errors;

    public BadRequestException(final List<String> errors) {
        this.errors = errors;
        this.status = HttpStatus.BAD_REQUEST;
    }

    public HttpStatus getStatus() {
        return status;
    }


    public List<String> getErrors() {
        return errors;
    }

}