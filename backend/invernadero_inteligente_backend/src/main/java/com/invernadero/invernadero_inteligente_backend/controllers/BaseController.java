package com.invernadero.invernadero_inteligente_backend.controllers;

import org.springframework.web.bind.annotation.RestController;

/**
 * Clase base para todos los controladores
 * Contiene propiedades y métodos comunes
 */
@RestController
public abstract class BaseController {

    /**
     * Prefijo base para todas las rutas
     */
    public static final String API_PREFIX = "/api/v1";

    /**
     * Responde con éxito
     */
    protected <T> T respondSuccess(T data) {
        return data;
    }
}

