package org.example.elecciones_backend.controllers;

import org.example.elecciones_backend.dtos.DepartamentoDTO;
import org.example.elecciones_backend.services.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/departamentos")
@CrossOrigin("*")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    // Crear Departamento
    @PostMapping
    public ResponseEntity<DepartamentoDTO> createDepartamento(@RequestBody DepartamentoDTO departamentoDTO) {
        DepartamentoDTO savedDepartamento = departamentoService.createDepartamento(departamentoDTO);
        return new ResponseEntity<>(savedDepartamento, HttpStatus.CREATED);
    }

    // Listar todos los Departamentos
    @GetMapping
    public ResponseEntity<List<DepartamentoDTO>> getAllDepartamentos() {
        return ResponseEntity.ok(departamentoService.getDepartamentos());
    }

    // Buscar por ID
    @GetMapping("{id}")
    public ResponseEntity<DepartamentoDTO> getDepartamento(@PathVariable Long id) {
        return ResponseEntity.ok(departamentoService.getDepartamento(id));
    }

    // Actualizar Departamento
    @PutMapping("{id}")
    public ResponseEntity<DepartamentoDTO> updateDepartamento(@PathVariable Long id,
                                                    @RequestBody DepartamentoDTO departamentoDTO) {
        return ResponseEntity.ok(departamentoService.updateDepartamento(id, departamentoDTO));
    }

    // Eliminar Departamento
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartamento(@PathVariable Long id) {
        return ResponseEntity.ok(departamentoService.deleteDepartamento(id));
    }
}
