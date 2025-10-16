package org.example.elecciones_backend.controllers;

import org.example.elecciones_backend.dtos.MesaDTO;
import org.example.elecciones_backend.services.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/mesas")
@CrossOrigin("*")
public class MesaController {

    @Autowired
    private MesaService mesaService;

    // Crear Mesa
    @PostMapping
    public ResponseEntity<MesaDTO> createMesa(@RequestBody MesaDTO mesaDTO) {
        MesaDTO savedMesa = mesaService.createMesa(mesaDTO);
        return new ResponseEntity<>(savedMesa, HttpStatus.CREATED);
    }

    // Listar todas las Mesas
    @GetMapping
    public ResponseEntity<List<MesaDTO>> getAllMesas() {
        return ResponseEntity.ok(mesaService.getMesas());
    }

    // Buscar por ID
    @GetMapping("{id}")
    public ResponseEntity<MesaDTO> getMesa(@PathVariable Long id) {
        return ResponseEntity.ok(mesaService.getMesa(id));
    }

    // Buscar por recinto
    @GetMapping("recinto/{recintoId}")
    public ResponseEntity<List<MesaDTO>> getMesasByRecinto(@PathVariable Long recintoId) {
        return ResponseEntity.ok(mesaService.getMesasByRecinto(recintoId));
    }

    // Actualizar Mesa
    @PutMapping("{id}")
    public ResponseEntity<MesaDTO> updateMesa(@PathVariable Long id,
                                                    @RequestBody MesaDTO mesaDTO) {
        return ResponseEntity.ok(mesaService.updateMesa(id, mesaDTO));
    }

    // Eliminar Mesa
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMesa(@PathVariable Long id) {
        return ResponseEntity.ok(mesaService.deleteMesa(id));
    }
}
