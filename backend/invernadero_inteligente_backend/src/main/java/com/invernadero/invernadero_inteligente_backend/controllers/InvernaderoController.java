package com.invernadero.invernadero_inteligente_backend.controllers;

import com.invernadero.invernadero_inteligente_backend.dtos.request.CrearInvernaderoRequestDTO;
import com.invernadero.invernadero_inteligente_backend.dtos.request.PageRequestDTO;
import com.invernadero.invernadero_inteligente_backend.dtos.response.ApiResponseDTO;
import com.invernadero.invernadero_inteligente_backend.dtos.response.InvernaderoResponseDTO;
import com.invernadero.invernadero_inteligente_backend.models.enums.EstadoInvernadero;
import com.invernadero.invernadero_inteligente_backend.services.InvernaderoService;
import lombok.extern.slf4j.Slf4j;
import com.invernadero.invernadero_inteligente_backend.mappers.InvernaderoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Controlador REST para Invernadero
 * Ejemplo de template para otros controladores
 *
 * Rutas: /api/v1/invernaderos
 */
@Slf4j
@RestController
@RequestMapping(BaseController.API_PREFIX + "/invernaderos")
@CrossOrigin(origins = "*", maxAge = 3600)
public class InvernaderoController extends BaseController {

    @Autowired
    private InvernaderoService invernaderoService;

    @Autowired
    private InvernaderoMapper invernaderoMapper;

    /**
     * GET /api/v1/invernaderos
     * Obtener todos los invernaderos con paginación y filtros
     */
    @GetMapping
    public ResponseEntity<ApiResponseDTO<?>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) EstadoInvernadero estado,
            @RequestParam(required = false) Boolean activo,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction) {

        log.info("Obteniendo invernaderos - page: {}, size: {}", page, size);

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort));
        Page<InvernaderoResponseDTO> result = invernaderoService.getAllPaginated(
            nombre, estado, activo, pageable);

        return ResponseEntity.ok(ApiResponseDTO.success("Invernaderos obtenidos exitosamente", result));
    }

    /**
     * GET /api/v1/invernaderos/{id}
     * Obtener invernadero por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        log.info("Buscando invernadero con ID: {}", id);

        return invernaderoService.getById(id)
            .map(inv -> ResponseEntity.ok(
                ApiResponseDTO.success("Invernadero encontrado", invernaderoMapper.toDTO(inv))))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponseDTO.error(404, "Invernadero no encontrado")));
    }

    /**
     * POST /api/v1/invernaderos
     * Crear un nuevo invernadero
     */
    @PostMapping
    public ResponseEntity<ApiResponseDTO<?>> create(
            @Valid @RequestBody CrearInvernaderoRequestDTO dto) {
        log.info("Creando nuevo invernadero: {}", dto.getNombre());

        InvernaderoResponseDTO created = invernaderoService.crearInvernadero(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponseDTO.success("Invernadero creado exitosamente", created));
    }

    /**
     * PUT /api/v1/invernaderos/{id}
     * Actualizar invernadero
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<?>> update(
            @PathVariable Long id,
            @Valid @RequestBody CrearInvernaderoRequestDTO dto) {
        log.info("Actualizando invernadero con ID: {}", id);

        // Convertir DTO a entidad y actualizar
        // return ResponseEntity.ok(ApiResponseDTO.success(updated));
        return ResponseEntity.ok(ApiResponseDTO.success("Invernadero actualizado exitosamente", null));
    }

    /**
     * DELETE /api/v1/invernaderos/{id}
     * Eliminar invernadero
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<?>> delete(@PathVariable Long id) {
        log.info("Eliminando invernadero con ID: {}", id);

        invernaderoService.delete(id);
        return ResponseEntity.ok(ApiResponseDTO.success("Invernadero eliminado exitosamente", null));
    }

    /**
     * PATCH /api/v1/invernaderos/{id}/estado
     * Cambiar estado del invernadero
     */
    @PatchMapping("/{id}/estado")
    public ResponseEntity<ApiResponseDTO<?>> cambiarEstado(
            @PathVariable Long id,
            @RequestParam EstadoInvernadero nuevoEstado) {
        log.info("Cambiando estado del invernadero {} a {}", id, nuevoEstado);

        InvernaderoResponseDTO updated = invernaderoService.actualizarEstado(id, nuevoEstado);
        return ResponseEntity.ok(ApiResponseDTO.success("Estado actualizado exitosamente", updated));
    }

    /**
     * PATCH /api/v1/invernaderos/{id}/disponibilidad
     * Cambiar disponibilidad del invernadero
     */
    @PatchMapping("/{id}/disponibilidad")
    public ResponseEntity<ApiResponseDTO<?>> cambiarDisponibilidad(
            @PathVariable Long id,
            @RequestParam Boolean activo) {
        log.info("Cambiando disponibilidad del invernadero {} a {}", id, activo);

        invernaderoService.cambiarDisponibilidad(id, activo);
        return ResponseEntity.ok(ApiResponseDTO.success("Disponibilidad actualizada exitosamente", null));
    }
}

