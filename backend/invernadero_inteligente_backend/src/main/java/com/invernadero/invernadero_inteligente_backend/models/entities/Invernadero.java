package com.invernadero.invernadero_inteligente_backend.models.entities;

import com.invernadero.invernadero_inteligente_backend.models.enums.EstadoInvernadero;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * Entidad Invernadero
 * Ejemplo de template para crear otras entidades
 */
@Entity
@Table(name = "invernaderos", indexes = {
    @Index(name = "idx_nombre", columnList = "nombre"),
    @Index(name = "idx_estado", columnList = "estado"),
    @Index(name = "idx_activo", columnList = "activo")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invernadero extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @Column(nullable = false, length = 200)
    private String ubicacion;

    @Column(nullable = false)
    private Double area; // en m²

    @Column(length = 100)
    private String responsable;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EstadoInvernadero estado = EstadoInvernadero.ACTIVO;

    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;

    @PrePersist
    public void prePersist() {
        super.prePersist();
        if (this.estado == null) {
            this.estado = EstadoInvernadero.ACTIVO;
        }
    }

    @PreUpdate
    public void preUpdate() {
        super.preUpdate();
    }
}

