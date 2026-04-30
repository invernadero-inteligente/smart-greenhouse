package com.invernadero.invernadero_inteligente_backend.shared.utils;

import com.invernadero.invernadero_inteligente_backend.shared.exceptions.ValidationException;

/**
 * Utilidad para validaciones comunes
 */
public class ValidationUtil {

    private ValidationUtil() {
        // Constructor privado para evitar instanciaciÃ³n
    }

    public static void validateNotNull(Object obj, String fieldName) {
        if (obj == null) {
            throw new ValidationException(fieldName, "No puede ser nulo");
        }
    }

    public static void validateNotEmpty(String str, String fieldName) {
        if (str == null || str.trim().isEmpty()) {
            throw new ValidationException(fieldName, "No puede estar vacÃ­o");
        }
    }

    public static void validateEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!email.matches(emailRegex)) {
            throw new ValidationException("email", "Email invÃ¡lido");
        }
    }

    public static void validateRange(double value, double min, double max, String fieldName) {
        if (value < min || value > max) {
            throw new ValidationException(fieldName,
                String.format("El valor debe estar entre %f y %f", min, max));
        }
    }

    public static void validateGreaterThanZero(double value, String fieldName) {
        if (value <= 0) {
            throw new ValidationException(fieldName, "Debe ser mayor a cero");
        }
    }
}

