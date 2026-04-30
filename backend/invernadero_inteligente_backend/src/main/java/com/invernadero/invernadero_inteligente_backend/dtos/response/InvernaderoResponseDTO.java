package com.invernadero.invernadero_inteligente_backend.dtos.response;

import com.invernadero.invernadero_inteligente_backend.models.enums.EstadoInvernadero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * DTO para respuestas de invernadero
 * Ejemplo de template para otros response DTOs
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvernaderoResponseDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private Double area;
    private String responsable;
    private EstadoInvernadero estado;
    private Boolean activo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
}

