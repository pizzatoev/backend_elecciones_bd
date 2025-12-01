package org.example.elecciones_backend.controllers;

import org.example.elecciones_backend.entities.HistorialPersona;
import org.example.elecciones_backend.services.HistorialPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador HistorialPersonaController - Módulo Infraestructura
 * Responsabilidad: Waldir Trancoso
 * 
 * Controlador REST para gestionar los endpoints relacionados
 * con el historial de personas.
 */
@RestController
@RequestMapping("/historial")
@CrossOrigin(origins = "*")
public class HistorialPersonaController {

    @Autowired
    private HistorialPersonaService historialPersonaService;

    /**
     * Endpoint para obtener todos los registros de historial
     * GET /historial
     * @return Lista de todos los registros de historial
     */
    @GetMapping
    public ResponseEntity<List<HistorialPersona>> listarTodos() {
        try {
            List<HistorialPersona> historiales = historialPersonaService.listarTodos();
            return ResponseEntity.ok(historiales);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint para obtener el historial de una persona específica
     * GET /historial/persona/{id}
     * @param id ID de la persona
     * @return Lista de registros de historial de la persona
     */
    @GetMapping("/persona/{id}")
    public ResponseEntity<List<HistorialPersona>> listarPorPersona(@PathVariable Long id) {
        try {
            List<HistorialPersona> historiales = historialPersonaService.listarPorPersona(id);
            return ResponseEntity.ok(historiales);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint para crear un nuevo registro de historial
     * POST /historial
     * @param historialPersona El objeto HistorialPersona a crear
     * @return El historial creado
     */
    @PostMapping
    public ResponseEntity<HistorialPersona> crearHistorial(@RequestBody HistorialPersona historialPersona) {
        try {
            HistorialPersona historialGuardado = historialPersonaService.guardarHistorial(historialPersona);
            return ResponseEntity.status(HttpStatus.CREATED).body(historialGuardado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
