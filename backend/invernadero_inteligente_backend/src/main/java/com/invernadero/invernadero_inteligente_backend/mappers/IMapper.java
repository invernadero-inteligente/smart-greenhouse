package com.invernadero.invernadero_inteligente_backend.mappers;

/**
 * Interfaz base para mappers de entidades a DTOs y viceversa
 * @param <E> Tipo de entidad
 * @param <D> Tipo de DTO
 */
public interface IMapper<E, D> {

    /**
     * Convierte una entidad a DTO
     */
    D toDTO(E entity);

    /**
     * Convierte un DTO a entidad
     */
    E toEntity(D dto);
}

