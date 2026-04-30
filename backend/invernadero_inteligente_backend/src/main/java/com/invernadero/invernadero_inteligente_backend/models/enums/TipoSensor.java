package com.invernadero.invernadero_inteligente_backend.models.enums;

/**
 * Enum que representa los tipos de sensores disponibles
 */
public enum TipoSensor {
    TEMPERATURA("Temperatura", "°C"),
    HUMEDAD("Humedad", "%"),
    HUMEDAD_SUELO("Humedad del suelo", "%"),
    PH("pH del suelo", "pH"),
    PRECIPITACION("Precipitación", "mm"),
    RADIACION_SOLAR("Radiación solar", "W/m²"),
    CO2("Dióxido de carbono", "ppm"),
    PRESION("Presión", "hPa"),
    LUZ("Luz", "lux"),
    NITROGENO("Nitrógeno", "ppm");

    private final String nombre;
    private final String unidad;

    TipoSensor(String nombre, String unidad) {
        this.nombre = nombre;
        this.unidad = unidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUnidad() {
        return unidad;
    }
}

