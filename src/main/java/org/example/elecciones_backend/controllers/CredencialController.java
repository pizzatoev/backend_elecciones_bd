package org.example.elecciones_backend.controllers;

import org.example.elecciones_backend.dtos.CredencialDTO;
import org.example.elecciones_backend.entities.Credencial;
import org.example.elecciones_backend.services.CredencialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/credenciales")
@CrossOrigin("*")
public class CredencialController {

    @Autowired
    private CredencialService credencialService;

    // Crear Credencial
    @PostMapping
    public ResponseEntity<CredencialDTO> createCredencial(@RequestBody CredencialDTO credencialDTO) {
        CredencialDTO savedCredencial = credencialService.createCredencial(credencialDTO);
        return new ResponseEntity<>(savedCredencial, HttpStatus.CREATED);
    }

    // Listar todas las Credenciales
    @GetMapping
    public ResponseEntity<List<CredencialDTO>> getAllCredenciales() {
        return ResponseEntity.ok(credencialService.getCredenciales());
    }

    // Buscar por ID
    @GetMapping("{id}")
    public ResponseEntity<CredencialDTO> getCredencial(@PathVariable Long id) {
        return ResponseEntity.ok(credencialService.getCredencial(id));
    }

    // Buscar por persona
    @GetMapping("persona/{personaId}")
    public ResponseEntity<List<CredencialDTO>> getCredencialesByPersona(@PathVariable Long personaId) {
        return ResponseEntity.ok(credencialService.getCredencialesByPersona(personaId));
    }

    // Buscar por rol
    @GetMapping("rol/{rol}")
    public ResponseEntity<List<CredencialDTO>> getCredencialesByRol(@PathVariable Credencial.Rol rol) {
        return ResponseEntity.ok(credencialService.getCredencialesByRol(rol));
    }

    // Buscar por persona y rol
    @GetMapping("persona/{personaId}/rol/{rol}")
    public ResponseEntity<CredencialDTO> getCredencialByPersonaAndRol(@PathVariable Long personaId, @PathVariable Credencial.Rol rol) {
        return ResponseEntity.ok(credencialService.getCredencialByPersonaAndRol(personaId, rol));
    }

    // Actualizar Credencial
    @PutMapping("{id}")
    public ResponseEntity<CredencialDTO> updateCredencial(@PathVariable Long id,
                                                    @RequestBody CredencialDTO credencialDTO) {
        return ResponseEntity.ok(credencialService.updateCredencial(id, credencialDTO));
    }

    // Eliminar Credencial
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCredencial(@PathVariable Long id) {
        return ResponseEntity.ok(credencialService.deleteCredencial(id));
    }

    // Generar Credencial
    @PostMapping("generar")
    public ResponseEntity<CredencialDTO> generarCredencial(@RequestBody GenerarCredencialRequest request) {
        try {
            Credencial.Rol rolEnum = Credencial.Rol.valueOf(request.getRol().toUpperCase());
            return ResponseEntity.ok(credencialService.generarCredencial(request.getIdPersona(), rolEnum));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Descargar PDF
    @GetMapping("{id}/pdf")
    public ResponseEntity<byte[]> descargarPdf(@PathVariable Long id) {
        return credencialService.descargarPdf(id);
    }

    // Ver QR
    @GetMapping("{id}/qr")
    public ResponseEntity<byte[]> verQr(@PathVariable Long id) {
        return credencialService.verQr(id);
    }

    // Clase interna para el request
    public static class GenerarCredencialRequest {
        private Long idPersona;
        private String rol;

        public Long getIdPersona() { return idPersona; }
        public void setIdPersona(Long idPersona) { this.idPersona = idPersona; }
        public String getRol() { return rol; }
        public void setRol(String rol) { this.rol = rol; }
    }
}
