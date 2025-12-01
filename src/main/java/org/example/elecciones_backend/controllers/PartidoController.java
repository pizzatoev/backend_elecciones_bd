package org.example.elecciones_backend.controllers;

import org.example.elecciones_backend.dtos.PartidoDTO;
import org.example.elecciones_backend.entities.Partido;
import org.example.elecciones_backend.services.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/partidos")
@CrossOrigin("*")
public class PartidoController {

    @Autowired
    private PartidoService partidoService;

    // Crear Partido
    @PostMapping
    public ResponseEntity<PartidoDTO> createPartido(@RequestBody PartidoDTO partidoDTO) {
        PartidoDTO savedPartido = partidoService.createPartido(partidoDTO);
        return new ResponseEntity<>(savedPartido, HttpStatus.CREATED);
    }

    // Listar todos los Partidos
    @GetMapping
    public ResponseEntity<List<PartidoDTO>> getAllPartidos() {
        return ResponseEntity.ok(partidoService.getPartidos());
    }

    // Buscar por ID
    @GetMapping("{id}")
    public ResponseEntity<PartidoDTO> getPartido(@PathVariable Long id) {
        return ResponseEntity.ok(partidoService.getPartido(id));
    }

    // Buscar por sigla
    @GetMapping("sigla/{sigla}")
    public ResponseEntity<PartidoDTO> getPartidoBySigla(@PathVariable String sigla) {
        return ResponseEntity.ok(partidoService.getPartidoBySigla(sigla));
    }

    // Buscar por estado
    @GetMapping("estado/{estado}")
    public ResponseEntity<List<PartidoDTO>> getPartidosByEstado(@PathVariable Partido.Estado estado) {
        return ResponseEntity.ok(partidoService.getPartidosByEstado(estado));
    }

    // Actualizar Partido
    @PutMapping("{id}")
    public ResponseEntity<PartidoDTO> updatePartido(@PathVariable Long id,
                                                    @RequestBody PartidoDTO partidoDTO) {
        return ResponseEntity.ok(partidoService.updatePartido(id, partidoDTO));
    }

    // Eliminar Partido
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePartido(@PathVariable Long id) {
        return ResponseEntity.ok(partidoService.deletePartido(id));
    }

    // Subir logo de partido - Módulo Infraestructura
    // Responsabilidad: Waldir Trancoso
    @PostMapping("/upload-logo/{id}")
    public ResponseEntity<?> uploadLogo(@PathVariable Long id, @RequestParam("logoUrl") String logoUrl) {
        try {
            PartidoDTO updatedPartido = partidoService.uploadLogo(id, logoUrl);
            return ResponseEntity.ok(updatedPartido);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Cambiar estado de partido - Módulo Infraestructura
    // Responsabilidad: Waldir Trancoso
    @PutMapping("/{id}/estado")
    public ResponseEntity<?> changeEstado(@PathVariable Long id, @RequestParam("nuevoEstado") Partido.Estado nuevoEstado) {
        try {
            PartidoDTO updatedPartido = partidoService.changeEstado(id, nuevoEstado);
            return ResponseEntity.ok(updatedPartido);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
