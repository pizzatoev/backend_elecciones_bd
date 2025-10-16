package org.example.elecciones_backend.controllers;

import org.example.elecciones_backend.dtos.InstitucionDTO;
import org.example.elecciones_backend.services.InstitucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/instituciones")
@CrossOrigin("*")
public class InstitucionController {

    @Autowired
    private InstitucionService institucionService;

    // Crear Institucion
    @PostMapping
    public ResponseEntity<InstitucionDTO> createInstitucion(@RequestBody InstitucionDTO institucionDTO) {
        InstitucionDTO savedInstitucion = institucionService.createInstitucion(institucionDTO);
        return new ResponseEntity<>(savedInstitucion, HttpStatus.CREATED);
    }

    // Listar todas las Instituciones
    @GetMapping
    public ResponseEntity<List<InstitucionDTO>> getAllInstituciones() {
        return ResponseEntity.ok(institucionService.getInstituciones());
    }

    // Buscar por ID
    @GetMapping("{id}")
    public ResponseEntity<InstitucionDTO> getInstitucion(@PathVariable Long id) {
        return ResponseEntity.ok(institucionService.getInstitucion(id));
    }

    // Buscar por sigla
    @GetMapping("sigla/{sigla}")
    public ResponseEntity<InstitucionDTO> getInstitucionBySigla(@PathVariable String sigla) {
        return ResponseEntity.ok(institucionService.getInstitucionBySigla(sigla));
    }

    // Buscar por nombre
    @GetMapping("nombre/{nombre}")
    public ResponseEntity<List<InstitucionDTO>> getInstitucionesByNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(institucionService.getInstitucionesByNombre(nombre));
    }

    // Actualizar Institucion
    @PutMapping("{id}")
    public ResponseEntity<InstitucionDTO> updateInstitucion(@PathVariable Long id,
                                                    @RequestBody InstitucionDTO institucionDTO) {
        return ResponseEntity.ok(institucionService.updateInstitucion(id, institucionDTO));
    }

    // Eliminar Institucion
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteInstitucion(@PathVariable Long id) {
        return ResponseEntity.ok(institucionService.deleteInstitucion(id));
    }
}
