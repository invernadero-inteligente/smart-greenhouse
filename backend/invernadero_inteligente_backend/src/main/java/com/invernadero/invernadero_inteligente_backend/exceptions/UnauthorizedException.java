package com.invernadero.invernadero_inteligente_backend.exceptions;

/**
 * Excepción lanzada cuando el usuario no está autorizado
 */
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}

