package com.co.flypass.gestioninventario.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class DataBaseException extends AppException {

    private HttpStatus status;
    private List<String> errors;

    public DataBaseException(String message) {
        super(message);
    }


}