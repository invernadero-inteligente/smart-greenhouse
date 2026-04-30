package com.invernadero.invernadero_inteligente_backend.services;

import com.invernadero.invernadero_inteligente_backend.dtos.request.CrearInvernaderoRequestDTO;
import com.invernadero.invernadero_inteligente_backend.dtos.response.InvernaderoResponseDTO;
import com.invernadero.invernadero_inteligente_backend.models.entities.Invernadero;
import com.invernadero.invernadero_inteligente_backend.models.enums.EstadoInvernadero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de servicio para Invernadero
 * Ejemplo de template para otros servicios
 */
public interface InvernaderoService extends BaseService<Invernadero, Long> {

    /**
     * Obtener invernadero por nombre
     */
    Optional<InvernaderoResponseDTO> getByNombre(String nombre);

    /**
     * Obtener invernaderos por estado
     */
    List<InvernaderoResponseDTO> getByEstado(EstadoInvernadero estado);

    /**
     * Obtener invernaderos con paginación y filtros
     */
    Page<InvernaderoResponseDTO> getAllPaginated(
        String nombre,
        EstadoInvernadero estado,
        Boolean activo,
        Pageable pageable
    );

    /**
     * Crear invernadero desde DTO
     */
    InvernaderoResponseDTO crearInvernadero(CrearInvernaderoRequestDTO dto);

    /**
     * Actualizar estado del invernadero
     */
    InvernaderoResponseDTO actualizarEstado(Long id, EstadoInvernadero nuevoEstado);

    /**
     * Cambiar disponibilidad del invernadero
     */
    void cambiarDisponibilidad(Long id, Boolean activo);

    /**
     * Obtener información de temperatura promedio (desde MongoDB)
     */
    Double obtenerTemperaturaPromedio(Long invernaderoId);
}

