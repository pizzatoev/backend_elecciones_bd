package org.example.elecciones_backend.services;

import org.example.elecciones_backend.entities.HistorialPersona;
import org.example.elecciones_backend.entities.Persona;
import org.example.elecciones_backend.repositories.HistorialPersonaRepository;
import org.example.elecciones_backend.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Servicio HistorialPersonaService - Módulo Infraestructura
 * Responsabilidad: Waldir Trancoso
 * 
 * Servicio para gestionar las operaciones de negocio
 * relacionadas con el historial de personas.
 */
@Service
public class HistorialPersonaService {

    @Autowired
    private HistorialPersonaRepository historialPersonaRepository;
    
    @Autowired
    private PersonaRepository personaRepository;

    /**
     * Guarda un nuevo registro en el historial de una persona
     * @param historialPersona El objeto HistorialPersona a guardar
     * @return El historial guardado
     */
    public HistorialPersona guardarHistorial(HistorialPersona historialPersona) {
        // Validar que la persona existe
        if (historialPersona.getPersona() == null || historialPersona.getPersona().getId() == null) {
            throw new IllegalArgumentException("La persona es requerida para crear un historial");
        }
        
        Optional<Persona> persona = personaRepository.findById(historialPersona.getPersona().getId());
        if (!persona.isPresent()) {
            throw new IllegalArgumentException("La persona con ID " + historialPersona.getPersona().getId() + " no existe");
        }
        
        // Establecer fecha de evento si no está definida
        if (historialPersona.getFechaEvento() == null) {
            historialPersona.setFechaEvento(LocalDateTime.now());
        }
        
        return historialPersonaRepository.save(historialPersona);
    }

    /**
     * Lista todos los registros de historial para una persona específica
     * @param personaId ID de la persona
     * @return Lista de registros de historial ordenados por fecha descendente
     */
    public List<HistorialPersona> listarPorPersona(Long personaId) {
        // Validar que la persona existe
        Optional<Persona> persona = personaRepository.findById(personaId);
        if (!persona.isPresent()) {
            throw new IllegalArgumentException("La persona con ID " + personaId + " no existe");
        }
        
        return historialPersonaRepository.findByPersonaIdOrderByFechaEventoDesc(personaId);
    }

    /**
     * Lista todos los registros de historial del sistema
     * @return Lista de todos los registros de historial
     */
    public List<HistorialPersona> listarTodos() {
        return historialPersonaRepository.findAll();
    }
}
