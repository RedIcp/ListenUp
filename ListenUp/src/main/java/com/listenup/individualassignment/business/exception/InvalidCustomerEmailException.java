package com.listenup.individualassignment.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidCustomerEmailException extends ResponseStatusException {
    public InvalidCustomerEmailException(String error){super(HttpStatus.BAD_REQUEST, error);}
}
