package org.example.elecciones_backend.controllers;

import org.example.elecciones_backend.dtos.AsistenciaDTO;
import org.example.elecciones_backend.entities.Asistencia;
import org.example.elecciones_backend.services.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/asistencias")
@CrossOrigin("*")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    // Crear Asistencia
    @PostMapping
    public ResponseEntity<AsistenciaDTO> createAsistencia(@RequestBody AsistenciaDTO asistenciaDTO) {
        AsistenciaDTO savedAsistencia = asistenciaService.createAsistencia(asistenciaDTO);
        return new ResponseEntity<>(savedAsistencia, HttpStatus.CREATED);
    }

    // Listar todas las Asistencias
    @GetMapping
    public ResponseEntity<List<AsistenciaDTO>> getAllAsistencias() {
        return ResponseEntity.ok(asistenciaService.getAsistencias());
    }

    // Buscar por ID
    @GetMapping("{id}")
    public ResponseEntity<AsistenciaDTO> getAsistencia(@PathVariable Long id) {
        return ResponseEntity.ok(asistenciaService.getAsistencia(id));
    }

    // Buscar por jurado
    @GetMapping("jurado/{juradoId}")
    public ResponseEntity<List<AsistenciaDTO>> getAsistenciasByJurado(@PathVariable Long juradoId) {
        return ResponseEntity.ok(asistenciaService.getAsistenciasByJurado(juradoId));
    }

    // Buscar por mesa
    @GetMapping("mesa/{mesaId}")
    public ResponseEntity<List<AsistenciaDTO>> getAsistenciasByMesa(@PathVariable Long mesaId) {
        return ResponseEntity.ok(asistenciaService.getAsistenciasByMesa(mesaId));
    }

    // Buscar por estado
    @GetMapping("estado/{estado}")
    public ResponseEntity<List<AsistenciaDTO>> getAsistenciasByEstado(@PathVariable Asistencia.Estado estado) {
        return ResponseEntity.ok(asistenciaService.getAsistenciasByEstado(estado));
    }

    // Buscar por jurado y mesa
    @GetMapping("jurado/{juradoId}/mesa/{mesaId}")
    public ResponseEntity<AsistenciaDTO> getAsistenciaByJuradoAndMesa(@PathVariable Long juradoId, @PathVariable Long mesaId) {
        return ResponseEntity.ok(asistenciaService.getAsistenciaByJuradoAndMesa(juradoId, mesaId));
    }

    // Actualizar Asistencia
    @PutMapping("{id}")
    public ResponseEntity<AsistenciaDTO> updateAsistencia(@PathVariable Long id,
                                                    @RequestBody AsistenciaDTO asistenciaDTO) {
        return ResponseEntity.ok(asistenciaService.updateAsistencia(id, asistenciaDTO));
    }

    // Eliminar Asistencia
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAsistencia(@PathVariable Long id) {
        return ResponseEntity.ok(asistenciaService.deleteAsistencia(id));
    }

    // Marcar como presente
    @PutMapping("{id}/presente")
    public ResponseEntity<String> marcarPresente(@PathVariable Long id) {
        return ResponseEntity.ok(asistenciaService.marcarPresente(id));
    }

    // Marcar como ausente
    @PutMapping("{id}/ausente")
    public ResponseEntity<String> marcarAusente(@PathVariable Long id) {
        return ResponseEntity.ok(asistenciaService.marcarAusente(id));
    }

    // Crear registros de asistencia para una mesa
    @PostMapping("mesa/{mesaId}/crear")
    public ResponseEntity<String> crearAsistenciasParaMesa(@PathVariable Long mesaId) {
        return ResponseEntity.ok(asistenciaService.crearAsistenciasParaMesa(mesaId));
    }

}
