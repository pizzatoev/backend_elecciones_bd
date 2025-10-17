package org.example.elecciones_backend.repositories;

import org.example.elecciones_backend.entities.HistorialPersona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio HistorialPersonaRepository - Módulo Infraestructura
 * Responsabilidad: Waldir Trancoso
 * 
 * Repositorio para gestionar las operaciones de base de datos
 * relacionadas con el historial de personas.
 */
@Repository
public interface HistorialPersonaRepository extends JpaRepository<HistorialPersona, Long> {
    
    /**
     * Busca todos los registros de historial para una persona específica
     * @param personaId ID de la persona
     * @return Lista de registros de historial ordenados por fecha descendente
     */
    List<HistorialPersona> findByPersonaIdOrderByFechaEventoDesc(Long personaId);
    
    /**
     * Busca todos los registros de historial para una persona específica
     * @param personaId ID de la persona
     * @return Lista de registros de historial ordenados por fecha descendente
     */
    List<HistorialPersona> findByPersonaId(Long personaId);
}
