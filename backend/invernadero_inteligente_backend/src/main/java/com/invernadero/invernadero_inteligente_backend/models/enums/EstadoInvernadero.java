package com.invernadero.invernadero_inteligente_backend.models.enums;

/**
 * Enum que representa los estados posibles del invernadero
 */
public enum EstadoInvernadero {
    ACTIVO("Activo"),
    INACTIVO("Inactivo"),
    MANTENIMIENTO("Mantenimiento"),
    ALERTA("Alerta"),
    CRITICO("Crítico");

    private final String descripcion;

    EstadoInvernadero(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

