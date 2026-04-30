package com.invernadero.invernadero_inteligente_backend.models.enums;

/**
 * Enum que representa los roles de usuarios en el sistema
 */
public enum RolUsuario {
    ADMINISTRADOR("Administrador"),
    ENCARGADO("Encargado del invernadero"),
    TECNICO("Técnico"),
    CONSULTOR("Consultor"),
    USUARIO("Usuario estándar");

    private final String descripcion;

    RolUsuario(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

