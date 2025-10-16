package org.example.elecciones_backend.controllers;

import org.example.elecciones_backend.dtos.ProvinciaDTO;
import org.example.elecciones_backend.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/provincias")
@CrossOrigin("*")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    // Crear Provincia
    @PostMapping
    public ResponseEntity<ProvinciaDTO> createProvincia(@RequestBody ProvinciaDTO provinciaDTO) {
        ProvinciaDTO savedProvincia = provinciaService.createProvincia(provinciaDTO);
        return new ResponseEntity<>(savedProvincia, HttpStatus.CREATED);
    }

    // Listar todas las Provincias
    @GetMapping
    public ResponseEntity<List<ProvinciaDTO>> getAllProvincias() {
        return ResponseEntity.ok(provinciaService.getProvincias());
    }

    // Buscar por ID
    @GetMapping("{id}")
    public ResponseEntity<ProvinciaDTO> getProvincia(@PathVariable Long id) {
        return ResponseEntity.ok(provinciaService.getProvincia(id));
    }

    // Buscar por departamento
    @GetMapping("departamento/{departamentoId}")
    public ResponseEntity<List<ProvinciaDTO>> getProvinciasByDepartamento(@PathVariable Long departamentoId) {
        return ResponseEntity.ok(provinciaService.getProvinciasByDepartamento(departamentoId));
    }

    // Actualizar Provincia
    @PutMapping("{id}")
    public ResponseEntity<ProvinciaDTO> updateProvincia(@PathVariable Long id,
                                                    @RequestBody ProvinciaDTO provinciaDTO) {
        return ResponseEntity.ok(provinciaService.updateProvincia(id, provinciaDTO));
    }

    // Eliminar Provincia
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProvincia(@PathVariable Long id) {
        return ResponseEntity.ok(provinciaService.deleteProvincia(id));
    }
}
