package org.example.elecciones_backend.controllers;

import org.example.elecciones_backend.dtos.MunicipioDTO;
import org.example.elecciones_backend.services.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/municipios")
@CrossOrigin("*")
public class MunicipioController {

    @Autowired
    private MunicipioService municipioService;

    // Crear Municipio
    @PostMapping
    public ResponseEntity<MunicipioDTO> createMunicipio(@RequestBody MunicipioDTO municipioDTO) {
        MunicipioDTO savedMunicipio = municipioService.createMunicipio(municipioDTO);
        return new ResponseEntity<>(savedMunicipio, HttpStatus.CREATED);
    }

    // Listar todos los Municipios
    @GetMapping
    public ResponseEntity<List<MunicipioDTO>> getAllMunicipios() {
        return ResponseEntity.ok(municipioService.getMunicipios());
    }

    // Buscar por ID
    @GetMapping("{id}")
    public ResponseEntity<MunicipioDTO> getMunicipio(@PathVariable Long id) {
        return ResponseEntity.ok(municipioService.getMunicipio(id));
    }

    // Buscar por provincia
    @GetMapping("provincia/{provinciaId}")
    public ResponseEntity<List<MunicipioDTO>> getMunicipiosByProvincia(@PathVariable Long provinciaId) {
        return ResponseEntity.ok(municipioService.getMunicipiosByProvincia(provinciaId));
    }

    // Actualizar Municipio
    @PutMapping("{id}")
    public ResponseEntity<MunicipioDTO> updateMunicipio(@PathVariable Long id,
                                                    @RequestBody MunicipioDTO municipioDTO) {
        return ResponseEntity.ok(municipioService.updateMunicipio(id, municipioDTO));
    }

    // Eliminar Municipio
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMunicipio(@PathVariable Long id) {
        return ResponseEntity.ok(municipioService.deleteMunicipio(id));
    }
}
