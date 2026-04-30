package com.invernadero.invernadero_inteligente_backend.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para crear un invernadero
 * Ejemplo de template para otros DTOs similar
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrearInvernaderoRequestDTO {

    @NotBlank(message = "El nombre del invernadero es requerido")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String descripcion;

    @NotNull(message = "La ubicación es requerida")
    private String ubicacion;

    @NotNull(message = "El área (en m²) es requerida")
    private Double area;

    private String responsable;

    private Boolean activo = true;
}

