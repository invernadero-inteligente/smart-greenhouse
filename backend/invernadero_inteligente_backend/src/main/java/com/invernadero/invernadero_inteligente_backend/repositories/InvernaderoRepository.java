package com.invernadero.invernadero_inteligente_backend.repositories;

import com.invernadero.invernadero_inteligente_backend.models.entities.Invernadero;
import com.invernadero.invernadero_inteligente_backend.models.enums.EstadoInvernadero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository para la entidad Invernadero
 * Ejemplo de template para otros repositories
 */
@Repository
public interface InvernaderoRepository extends JpaRepository<Invernadero, Long> {

    /**
     * Buscar invernadero por nombre
     */
    Optional<Invernadero> findByNombre(String nombre);

    /**
     * Buscar invernaderos por estado
     */
    List<Invernadero> findByEstado(EstadoInvernadero estado);

    /**
     * Buscar invernaderos activos
     */
    List<Invernadero> findByActivo(Boolean activo);

    /**
     * Buscar invernaderos por responsable
     */
    List<Invernadero> findByResponsable(String responsable);

    /**
     * Buscar con paginación y filtros
     */
    @Query("SELECT i FROM Invernadero i WHERE " +
           "(:nombre IS NULL OR i.nombre LIKE %:nombre%) AND " +
           "(:estado IS NULL OR i.estado = :estado) AND " +
           "(:activo IS NULL OR i.activo = :activo)")
    Page<Invernadero> findAll(
        @Param("nombre") String nombre,
        @Param("estado") EstadoInvernadero estado,
        @Param("activo") Boolean activo,
        Pageable pageable
    );

    /**
     * Contar invernaderos activos
     */
    long countByActivo(Boolean activo);
}

