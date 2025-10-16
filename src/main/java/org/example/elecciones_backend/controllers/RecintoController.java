package org.example.elecciones_backend.controllers;

import org.example.elecciones_backend.dtos.RecintoDTO;
import org.example.elecciones_backend.services.RecintoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/recintos")
@CrossOrigin("*")
public class RecintoController {

    @Autowired
    private RecintoService recintoService;

    // Crear Recinto
    @PostMapping
    public ResponseEntity<RecintoDTO> createRecinto(@RequestBody RecintoDTO recintoDTO) {
        RecintoDTO savedRecinto = recintoService.createRecinto(recintoDTO);
        return new ResponseEntity<>(savedRecinto, HttpStatus.CREATED);
    }

    // Listar todos los Recintos
    @GetMapping
    public ResponseEntity<List<RecintoDTO>> getAllRecintos() {
        return ResponseEntity.ok(recintoService.getRecintos());
    }

    // Buscar por ID
    @GetMapping("{id}")
    public ResponseEntity<RecintoDTO> getRecinto(@PathVariable Long id) {
        return ResponseEntity.ok(recintoService.getRecinto(id));
    }

    // Buscar por asiento
    @GetMapping("asiento/{asientoId}")
    public ResponseEntity<List<RecintoDTO>> getRecintosByAsiento(@PathVariable Long asientoId) {
        return ResponseEntity.ok(recintoService.getRecintosByAsiento(asientoId));
    }

    // Actualizar Recinto
    @PutMapping("{id}")
    public ResponseEntity<RecintoDTO> updateRecinto(@PathVariable Long id,
                                                    @RequestBody RecintoDTO recintoDTO) {
        return ResponseEntity.ok(recintoService.updateRecinto(id, recintoDTO));
    }

    // Eliminar Recinto
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRecinto(@PathVariable Long id) {
        return ResponseEntity.ok(recintoService.deleteRecinto(id));
    }
}
