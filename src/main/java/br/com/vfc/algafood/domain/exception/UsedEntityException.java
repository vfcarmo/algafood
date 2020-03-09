package br.com.vfc.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsedEntityException extends RuntimeException {

    public UsedEntityException(String message) {
        super(message);
    }
}
