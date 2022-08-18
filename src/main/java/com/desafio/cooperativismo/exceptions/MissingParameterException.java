package com.desafio.cooperativismo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.desafio.cooperativismo.enums.ErrorMessageEnum;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class MissingParameterException extends RuntimeException {

    private String message;

    public MissingParameterException(ErrorMessageEnum message) {
        this.message = message.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(ErrorMessageEnum message) {
        this.message = message.getMessage();
    }
}
