package org.example.elecciones_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.elecciones_backend.entities.Usuario;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String username;
    private String password; // Campo para contraseña en texto plano
    private Usuario.Rol rol;
    private java.sql.Timestamp creadoEn;
    
    // Constructor para login (sin password)
    public UsuarioDTO(Long id, String username, Usuario.Rol rol, java.sql.Timestamp creadoEn) {
        this.id = id;
        this.username = username;
        this.rol = rol;
        this.creadoEn = creadoEn;
    }
}
