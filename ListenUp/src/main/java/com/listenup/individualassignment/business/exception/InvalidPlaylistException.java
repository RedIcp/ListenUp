package com.listenup.individualassignment.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidPlaylistException extends ResponseStatusException {
    public InvalidPlaylistException(String error){super(HttpStatus.BAD_REQUEST, error);}
}
