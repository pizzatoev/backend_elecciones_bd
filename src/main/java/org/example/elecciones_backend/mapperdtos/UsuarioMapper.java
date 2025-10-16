package org.example.elecciones_backend.mapperdtos;

import org.example.elecciones_backend.dtos.UsuarioDTO;
import org.example.elecciones_backend.entities.Usuario;

public class UsuarioMapper {

    public static UsuarioDTO mapUsuarioToUsuarioDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getRol(),
                usuario.getCreadoEn()
        );
    }

    public static Usuario mapUsuarioDTOToUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(usuarioDTO.getPassword()); // Mapear password directamente
        usuario.setRol(usuarioDTO.getRol());
        usuario.setCreadoEn(usuarioDTO.getCreadoEn());
        return usuario;
    }
}
