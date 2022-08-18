package com.desafio.cooperativismo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.desafio.cooperativismo.enums.ErrorMessageEnum;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private String message;

    public ResourceNotFoundException(ErrorMessageEnum message) {
        this.message = message.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(ErrorMessageEnum message) {
        this.message = message.getMessage();
    }
}
