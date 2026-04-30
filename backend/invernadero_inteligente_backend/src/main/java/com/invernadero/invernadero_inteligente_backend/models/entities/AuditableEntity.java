package com.invernadero.invernadero_inteligente_backend.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * Entidad base con propiedades comunes para todos los modelos
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AuditableEntity {

    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    protected String createdBy;
    protected String updatedBy;
    protected boolean activo;

    /**
     * Método para inicializar propiedades antes de crear
     */
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.activo = true;
    }

    /**
     * Método para actualizar propiedades antes de actualizar
     */
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

