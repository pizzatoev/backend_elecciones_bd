package org.example.elecciones_backend.controllers;

import org.example.elecciones_backend.dtos.PersonaDTO;
import org.example.elecciones_backend.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/personas")
@CrossOrigin("*")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    // Crear Persona
    @PostMapping
    public ResponseEntity<?> createPersona(@RequestBody PersonaDTO personaDTO) {
        try {
            PersonaDTO savedPersona = personaService.createPersona(personaDTO);
            return new ResponseEntity<>(savedPersona, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Listar todas las Personas
    @GetMapping
    public ResponseEntity<List<PersonaDTO>> getAllPersonas() {
        return ResponseEntity.ok(personaService.getPersonas());
    }

    // Buscar por ID
    @GetMapping("{id}")
    public ResponseEntity<PersonaDTO> getPersona(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.getPersona(id));
    }

    // Buscar por CI
    @GetMapping("/ci/{ci}")
    public ResponseEntity<PersonaDTO> getPersonaByCi(@PathVariable String ci) {
        return ResponseEntity.ok(personaService.getPersonaByCi(ci));
    }

    // Actualizar Persona
    @PutMapping("{id}")
    public ResponseEntity<?> updatePersona(@PathVariable Long id,
                                                    @RequestBody PersonaDTO personaDTO) {
        try {
            return ResponseEntity.ok(personaService.updatePersona(id, personaDTO));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Eliminar Persona
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePersona(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.deletePersona(id));
    }
}
