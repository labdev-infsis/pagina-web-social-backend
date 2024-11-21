package com.infsis.socialpagebackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    // Constructor para nombre de recurso y UUID
    public NotFoundException(String resourceName, String uuid) {
        super(String.format("NotFound: %s not found with UUID: %s", resourceName, uuid));
    }

    // Constructor adicional para mensajes personalizados
    public NotFoundException(String message) {
        super(message);
    }
}
