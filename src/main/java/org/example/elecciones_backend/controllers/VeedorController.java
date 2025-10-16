package org.example.elecciones_backend.controllers;

import org.example.elecciones_backend.dtos.VeedorDTO;
import org.example.elecciones_backend.entities.Veedor;
import org.example.elecciones_backend.services.VeedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/veedores")
@CrossOrigin("*")
public class VeedorController {

    @Autowired
    private VeedorService veedorService;

    // Crear Veedor
    @PostMapping
    public ResponseEntity<VeedorDTO> createVeedor(@RequestBody VeedorDTO veedorDTO) {
        VeedorDTO savedVeedor = veedorService.createVeedor(veedorDTO);
        return new ResponseEntity<>(savedVeedor, HttpStatus.CREATED);
    }

    // Listar todos los Veedores
    @GetMapping
    public ResponseEntity<List<VeedorDTO>> getAllVeedores() {
        return ResponseEntity.ok(veedorService.getVeedores());
    }

    // Buscar por ID
    @GetMapping("{id}")
    public ResponseEntity<VeedorDTO> getVeedor(@PathVariable Long id) {
        return ResponseEntity.ok(veedorService.getVeedor(id));
    }

    // Buscar por persona
    @GetMapping("persona/{personaId}")
    public ResponseEntity<List<VeedorDTO>> getVeedoresByPersona(@PathVariable Long personaId) {
        return ResponseEntity.ok(veedorService.getVeedoresByPersona(personaId));
    }

    // Buscar por institución
    @GetMapping("institucion/{institucionId}")
    public ResponseEntity<List<VeedorDTO>> getVeedoresByInstitucion(@PathVariable Long institucionId) {
        return ResponseEntity.ok(veedorService.getVeedoresByInstitucion(institucionId));
    }

    // Buscar por estado
    @GetMapping("estado/{estado}")
    public ResponseEntity<List<VeedorDTO>> getVeedoresByEstado(@PathVariable Veedor.Estado estado) {
        return ResponseEntity.ok(veedorService.getVeedoresByEstado(estado));
    }

    // Buscar por persona e institución
    @GetMapping("persona/{personaId}/institucion/{institucionId}")
    public ResponseEntity<VeedorDTO> getVeedorByPersonaAndInstitucion(@PathVariable Long personaId, @PathVariable Long institucionId) {
        return ResponseEntity.ok(veedorService.getVeedorByPersonaAndInstitucion(personaId, institucionId));
    }

    // Buscar por CI
    @GetMapping("ci/{ci}")
    public ResponseEntity<VeedorDTO> getVeedorByCi(@PathVariable String ci) {
        return ResponseEntity.ok(veedorService.getVeedorByCi(ci));
    }

    // Actualizar Veedor
    @PutMapping("{id}")
    public ResponseEntity<VeedorDTO> updateVeedor(@PathVariable Long id,
                                                    @RequestBody VeedorDTO veedorDTO) {
        return ResponseEntity.ok(veedorService.updateVeedor(id, veedorDTO));
    }

    // Eliminar Veedor
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVeedor(@PathVariable Long id) {
        return ResponseEntity.ok(veedorService.deleteVeedor(id));
    }

    // Aprobar Veedor
    @PutMapping("{id}/aprobar")
    public ResponseEntity<VeedorDTO> aprobarVeedor(@PathVariable Long id) {
        return ResponseEntity.ok(veedorService.aprobarVeedor(id));
    }

    // Rechazar Veedor
    @PutMapping("{id}/rechazar")
    public ResponseEntity<VeedorDTO> rechazarVeedor(@PathVariable Long id, @RequestBody String motivo) {
        return ResponseEntity.ok(veedorService.rechazarVeedor(id, motivo));
    }
}
