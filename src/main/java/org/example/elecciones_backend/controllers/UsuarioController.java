package org.example.elecciones_backend.controllers;

import org.example.elecciones_backend.dtos.LoginRequest;
import org.example.elecciones_backend.dtos.UsuarioDTO;
import org.example.elecciones_backend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuarios")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Crear Usuario
    @PostMapping
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO savedUsuario = usuarioService.createUsuario(usuarioDTO);
        return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
    }

    // Listar todos los Usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioService.getUsuarios());
    }

    // Buscar por ID
    @GetMapping("{id}")
    public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.getUsuario(id));
    }

    // Buscar por username
    @GetMapping("username/{username}")
    public ResponseEntity<UsuarioDTO> getUsuarioByUsername(@PathVariable String username) {
        return ResponseEntity.ok(usuarioService.getUsuarioByUsername(username));
    }

    // Actualizar Usuario
    @PutMapping("{id}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Long id,
                                                    @RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.updateUsuario(id, usuarioDTO));
    }

    // Eliminar Usuario
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.deleteUsuario(id));
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody LoginRequest loginRequest) {
        try {
            UsuarioDTO usuario = usuarioService.login(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

        // Endpoint de prueba para CORS
        @GetMapping("/test")
        public ResponseEntity<String> testCors() {
            return ResponseEntity.ok("CORS funcionando correctamente");
        }

        // Endpoint temporal para verificar contraseña
        @PostMapping("/check-password")
        public ResponseEntity<String> checkPassword(@RequestBody LoginRequest loginRequest) {
            try {
                UsuarioDTO usuario = usuarioService.login(loginRequest.getUsername(), loginRequest.getPassword());
                return ResponseEntity.ok("Login exitoso para: " + usuario.getUsername());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: " + e.getMessage());
            }
        }
}
