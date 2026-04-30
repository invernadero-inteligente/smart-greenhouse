package com.invernadero.invernadero_inteligente_backend.services;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz genérica para servicios CRUD
 * @param <T> Tipo de entidad
 * @param <ID> Tipo de ID
 */
public interface BaseService<T, ID> {

    /**
     * Crear una nueva entidad
     */
    T create(T entity);

    /**
     * Obtener una entidad por ID
     */
    Optional<T> getById(ID id);

    /**
     * Obtener todas las entidades
     */
    List<T> getAll();

    /**
     * Actualizar una entidad
     */
    T update(ID id, T entity);

    /**
     * Eliminar una entidad por ID
     */
    void delete(ID id);

    /**
     * Verificar si existe una entidad por ID
     */
    boolean exists(ID id);

    /**
     * Contar el total de entidades
     */
    long count();
}

