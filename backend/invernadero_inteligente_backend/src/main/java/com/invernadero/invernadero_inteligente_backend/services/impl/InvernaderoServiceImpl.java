package com.invernadero.invernadero_inteligente_backend.services.impl;

import com.invernadero.invernadero_inteligente_backend.dtos.request.CrearInvernaderoRequestDTO;
import com.invernadero.invernadero_inteligente_backend.dtos.response.InvernaderoResponseDTO;
import com.invernadero.invernadero_inteligente_backend.exceptions.ResourceNotFoundException;
import com.invernadero.invernadero_inteligente_backend.exceptions.ValidationException;
import com.invernadero.invernadero_inteligente_backend.mappers.InvernaderoMapper;
import com.invernadero.invernadero_inteligente_backend.models.entities.Invernadero;
import com.invernadero.invernadero_inteligente_backend.models.enums.EstadoInvernadero;
import com.invernadero.invernadero_inteligente_backend.repositories.InvernaderoRepository;
import com.invernadero.invernadero_inteligente_backend.services.InvernaderoService;
import com.invernadero.invernadero_inteligente_backend.utils.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de Invernadero
 * Ejemplo de template para otros service implementations
 */
@Slf4j
@Service
@Transactional
public class InvernaderoServiceImpl implements InvernaderoService {

    @Autowired
    private InvernaderoRepository invernaderoRepository;

    @Autowired
    private InvernaderoMapper invernaderoMapper;

    @Override
    public Invernadero create(Invernadero entity) {
        log.info("Creando nuevo invernadero: {}", entity.getNombre());
        ValidationUtil.validateNotNull(entity, "invernadero");
        ValidationUtil.validateNotEmpty(entity.getNombre(), "nombre");
        
        entity.prePersist();
        Invernadero saved = invernaderoRepository.save(entity);
        log.info("Invernadero creado exitosamente con ID: {}", saved.getId());
        return saved;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Invernadero> getById(Long id) {
        log.debug("Buscando invernadero por ID: {}", id);
        return invernaderoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Invernadero> getAll() {
        log.debug("Obteniendo todos los invernaderos");
        return invernaderoRepository.findAll();
    }

    @Override
    public Invernadero update(Long id, Invernadero entity) {
        log.info("Actualizando invernadero con ID: {}", id);
        Invernadero existente = invernaderoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Invernadero", "id", id));
        
        if (entity.getNombre() != null) {
            existente.setNombre(entity.getNombre());
        }
        if (entity.getDescripcion() != null) {
            existente.setDescripcion(entity.getDescripcion());
        }
        if (entity.getUbicacion() != null) {
            existente.setUbicacion(entity.getUbicacion());
        }
        if (entity.getArea() != null) {
            existente.setArea(entity.getArea());
        }
        
        existente.preUpdate();
        return invernaderoRepository.save(existente);
    }

    @Override
    public void delete(Long id) {
        log.info("Eliminando invernadero con ID: {}", id);
        if (!invernaderoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Invernadero", "id", id);
        }
        invernaderoRepository.deleteById(id);
        log.info("Invernadero eliminado exitosamente");
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Long id) {
        return invernaderoRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return invernaderoRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InvernaderoResponseDTO> getByNombre(String nombre) {
        return invernaderoRepository.findByNombre(nombre)
            .map(invernaderoMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InvernaderoResponseDTO> getByEstado(EstadoInvernadero estado) {
        return invernaderoRepository.findByEstado(estado).stream()
            .map(invernaderoMapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InvernaderoResponseDTO> getAllPaginated(
            String nombre, EstadoInvernadero estado, Boolean activo, Pageable pageable) {
        return invernaderoRepository.findAll(nombre, estado, activo, pageable)
            .map(invernaderoMapper::toDTO);
    }

    @Override
    public InvernaderoResponseDTO crearInvernadero(CrearInvernaderoRequestDTO dto) {
        log.info("Creando invernadero desde DTO: {}", dto.getNombre());
        
        // Validar que no exista otro con el mismo nombre
        invernaderoRepository.findByNombre(dto.getNombre())
            .ifPresent(i -> {
                throw new ValidationException("nombre", "Ya existe un invernadero con este nombre");
            });

        Invernadero invernadero = Invernadero.builder()
            .nombre(dto.getNombre())
            .descripcion(dto.getDescripcion())
            .ubicacion(dto.getUbicacion())
            .area(dto.getArea())
            .responsable(dto.getResponsable())
            .activo(dto.getActivo() != null ? dto.getActivo() : true)
            .estado(EstadoInvernadero.ACTIVO)
            .build();

        create(invernadero);
        return invernaderoMapper.toDTO(invernadero);
    }

    @Override
    public InvernaderoResponseDTO actualizarEstado(Long id, EstadoInvernadero nuevoEstado) {
        log.info("Actualizando estado del invernadero {} al estado {}", id, nuevoEstado);
        Invernadero invernadero = invernaderoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Invernadero", "id", id));

        invernadero.setEstado(nuevoEstado);
        invernadero.preUpdate();
        invernaderoRepository.save(invernadero);

        return invernaderoMapper.toDTO(invernadero);
    }

    @Override
    public void cambiarDisponibilidad(Long id, Boolean activo) {
        log.info("Cambiando disponibilidad del invernadero {} a {}", id, activo);
        Invernadero invernadero = invernaderoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Invernadero", "id", id));

        invernadero.setActivo(activo);
        invernadero.preUpdate();
        invernaderoRepository.save(invernadero);
    }

    @Override
    @Transactional(readOnly = true)
    public Double obtenerTemperaturaPromedio(Long invernaderoId) {
        log.debug("Obteniendo temperatura promedio del invernadero {}", invernaderoId);
        // TODO: Implementar lógica para obtener datos desde MongoDB
        return null;
    }
}

