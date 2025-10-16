package org.example.elecciones_backend.services;

import org.example.elecciones_backend.dtos.UsuarioDTO;
import java.util.List;

public interface UsuarioService {
    UsuarioDTO createUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO updateUsuario(Long id, UsuarioDTO usuarioDTO);
    String deleteUsuario(Long id);
    UsuarioDTO getUsuario(Long id);
    UsuarioDTO getUsuarioByUsername(String username);
    List<UsuarioDTO> getUsuarios();
    UsuarioDTO login(String username, String password);
}
