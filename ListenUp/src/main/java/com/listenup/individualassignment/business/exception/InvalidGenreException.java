package com.listenup.individualassignment.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidGenreException extends ResponseStatusException {
    public InvalidGenreException(String error){super(HttpStatus.BAD_REQUEST, error);}
}
