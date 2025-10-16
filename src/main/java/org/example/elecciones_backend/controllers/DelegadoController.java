package org.example.elecciones_backend.controllers;

import org.example.elecciones_backend.dtos.DelegadoDTO;
import org.example.elecciones_backend.services.DelegadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/delegados")
@CrossOrigin("*")
public class DelegadoController {

    @Autowired
    private DelegadoService delegadoService;

    // Crear Delegado
    @PostMapping
    public ResponseEntity<DelegadoDTO> createDelegado(@RequestBody DelegadoDTO delegadoDTO) {
        DelegadoDTO savedDelegado = delegadoService.createDelegado(delegadoDTO);
        return new ResponseEntity<>(savedDelegado, HttpStatus.CREATED);
    }

    // Listar todos los Delegados
    @GetMapping
    public ResponseEntity<List<DelegadoDTO>> getAllDelegados() {
        return ResponseEntity.ok(delegadoService.getDelegados());
    }

    // Buscar por ID
    @GetMapping("{id}")
    public ResponseEntity<DelegadoDTO> getDelegado(@PathVariable Long id) {
        return ResponseEntity.ok(delegadoService.getDelegado(id));
    }

    // Buscar por persona
    @GetMapping("persona/{personaId}")
    public ResponseEntity<List<DelegadoDTO>> getDelegadosByPersona(@PathVariable Long personaId) {
        return ResponseEntity.ok(delegadoService.getDelegadosByPersona(personaId));
    }

    // Buscar por partido
    @GetMapping("partido/{partidoId}")
    public ResponseEntity<List<DelegadoDTO>> getDelegadosByPartido(@PathVariable Long partidoId) {
        return ResponseEntity.ok(delegadoService.getDelegadosByPartido(partidoId));
    }

    // Buscar por mesa
    @GetMapping("mesa/{mesaId}")
    public ResponseEntity<List<DelegadoDTO>> getDelegadosByMesa(@PathVariable Long mesaId) {
        return ResponseEntity.ok(delegadoService.getDelegadosByMesa(mesaId));
    }

    // Buscar por habilitado
    @GetMapping("habilitado/{habilitado}")
    public ResponseEntity<List<DelegadoDTO>> getDelegadosByHabilitado(@PathVariable Boolean habilitado) {
        return ResponseEntity.ok(delegadoService.getDelegadosByHabilitado(habilitado));
    }

    // Buscar por persona, partido y mesa
    @GetMapping("persona/{personaId}/partido/{partidoId}/mesa/{mesaId}")
    public ResponseEntity<DelegadoDTO> getDelegadoByPersonaPartidoAndMesa(@PathVariable Long personaId, @PathVariable Long partidoId, @PathVariable Long mesaId) {
        return ResponseEntity.ok(delegadoService.getDelegadoByPersonaPartidoAndMesa(personaId, partidoId, mesaId));
    }

    // Buscar por CI
    @GetMapping("ci/{ci}")
    public ResponseEntity<DelegadoDTO> getDelegadoByCi(@PathVariable String ci) {
        return ResponseEntity.ok(delegadoService.getDelegadoByCi(ci));
    }

    // Actualizar Delegado
    @PutMapping("{id}")
    public ResponseEntity<DelegadoDTO> updateDelegado(@PathVariable Long id,
                                                    @RequestBody DelegadoDTO delegadoDTO) {
        return ResponseEntity.ok(delegadoService.updateDelegado(id, delegadoDTO));
    }

    // Eliminar Delegado
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDelegado(@PathVariable Long id) {
        return ResponseEntity.ok(delegadoService.deleteDelegado(id));
    }

    // Toggle Habilitado
    @PutMapping("{id}/toggle-habilitado")
    public ResponseEntity<DelegadoDTO> toggleHabilitado(@PathVariable Long id) {
        return ResponseEntity.ok(delegadoService.toggleHabilitado(id));
    }
}
