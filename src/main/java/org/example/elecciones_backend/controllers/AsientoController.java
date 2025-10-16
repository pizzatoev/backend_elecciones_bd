package org.example.elecciones_backend.controllers;

import org.example.elecciones_backend.dtos.AsientoDTO;
import org.example.elecciones_backend.services.AsientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/asientos")
@CrossOrigin("*")
public class AsientoController {

    @Autowired
    private AsientoService asientoService;

    // Crear Asiento
    @PostMapping
    public ResponseEntity<AsientoDTO> createAsiento(@RequestBody AsientoDTO asientoDTO) {
        AsientoDTO savedAsiento = asientoService.createAsiento(asientoDTO);
        return new ResponseEntity<>(savedAsiento, HttpStatus.CREATED);
    }

    // Listar todos los Asientos
    @GetMapping
    public ResponseEntity<List<AsientoDTO>> getAllAsientos() {
        return ResponseEntity.ok(asientoService.getAsientos());
    }

    // Buscar por ID
    @GetMapping("{id}")
    public ResponseEntity<AsientoDTO> getAsiento(@PathVariable Long id) {
        return ResponseEntity.ok(asientoService.getAsiento(id));
    }

    // Buscar por municipio
    @GetMapping("municipio/{municipioId}")
    public ResponseEntity<List<AsientoDTO>> getAsientosByMunicipio(@PathVariable Long municipioId) {
        return ResponseEntity.ok(asientoService.getAsientosByMunicipio(municipioId));
    }

    // Actualizar Asiento
    @PutMapping("{id}")
    public ResponseEntity<AsientoDTO> updateAsiento(@PathVariable Long id,
                                                    @RequestBody AsientoDTO asientoDTO) {
        return ResponseEntity.ok(asientoService.updateAsiento(id, asientoDTO));
    }

    // Eliminar Asiento
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAsiento(@PathVariable Long id) {
        return ResponseEntity.ok(asientoService.deleteAsiento(id));
    }
}
