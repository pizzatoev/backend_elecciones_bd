package org.example.elecciones_backend.controllers;

import org.example.elecciones_backend.dtos.JuradoDTO;
import org.example.elecciones_backend.entities.Jurado;
import org.example.elecciones_backend.services.JuradoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jurados")
@CrossOrigin("*")
public class JuradoController {

    @Autowired
    private JuradoService juradoService;

    // Crear Jurado
    @PostMapping
    public ResponseEntity<JuradoDTO> createJurado(@RequestBody JuradoDTO juradoDTO) {
        JuradoDTO savedJurado = juradoService.createJurado(juradoDTO);
        return new ResponseEntity<>(savedJurado, HttpStatus.CREATED);
    }

    // Listar todos los Jurados
    @GetMapping
    public ResponseEntity<List<JuradoDTO>> getAllJurados() {
        return ResponseEntity.ok(juradoService.getJurados());
    }

    // Buscar por ID
    @GetMapping("{id}")
    public ResponseEntity<JuradoDTO> getJurado(@PathVariable Long id) {
        return ResponseEntity.ok(juradoService.getJurado(id));
    }

    // Buscar por persona
    @GetMapping("persona/{personaId}")
    public ResponseEntity<List<JuradoDTO>> getJuradosByPersona(@PathVariable Long personaId) {
        return ResponseEntity.ok(juradoService.getJuradosByPersona(personaId));
    }

    // Buscar por mesa
    @GetMapping("mesa/{mesaId}")
    public ResponseEntity<List<JuradoDTO>> getJuradosByMesa(@PathVariable Long mesaId) {
        return ResponseEntity.ok(juradoService.getJuradosByMesa(mesaId));
    }

    // Buscar por cargo
    @GetMapping("cargo/{cargo}")
    public ResponseEntity<List<JuradoDTO>> getJuradosByCargo(@PathVariable Jurado.Cargo cargo) {
        return ResponseEntity.ok(juradoService.getJuradosByCargo(cargo));
    }

    // Buscar por verificado
    @GetMapping("verificado/{verificado}")
    public ResponseEntity<List<JuradoDTO>> getJuradosByVerificado(@PathVariable Boolean verificado) {
        return ResponseEntity.ok(juradoService.getJuradosByVerificado(verificado));
    }

    // Buscar por persona y mesa
    @GetMapping("persona/{personaId}/mesa/{mesaId}")
    public ResponseEntity<JuradoDTO> getJuradoByPersonaAndMesa(@PathVariable Long personaId, @PathVariable Long mesaId) {
        return ResponseEntity.ok(juradoService.getJuradoByPersonaAndMesa(personaId, mesaId));
    }

    // Buscar por CI
    @GetMapping("ci/{ci}")
    public ResponseEntity<JuradoDTO> getJuradoByCi(@PathVariable String ci) {
        return ResponseEntity.ok(juradoService.getJuradoByCi(ci));
    }

    // Actualizar Jurado
    @PutMapping("{id}")
    public ResponseEntity<JuradoDTO> updateJurado(@PathVariable Long id,
                                                    @RequestBody JuradoDTO juradoDTO) {
        return ResponseEntity.ok(juradoService.updateJurado(id, juradoDTO));
    }

    // Eliminar Jurado
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteJurado(@PathVariable Long id) {
        return ResponseEntity.ok(juradoService.deleteJurado(id));
    }

    // Sorteo de Jurados
    @PostMapping("sortear")
    public ResponseEntity<List<JuradoDTO>> sortearJurados() {
        List<JuradoDTO> juradosSorteados = juradoService.sortearJurados();
        return ResponseEntity.ok(juradosSorteados);
    }

    // Eliminar Sorteo
    @DeleteMapping("eliminar-sorteo")
    public ResponseEntity<String> eliminarSorteo() {
        juradoService.eliminarSorteo();
        return ResponseEntity.ok("Sorteo eliminado exitosamente");
    }
}
