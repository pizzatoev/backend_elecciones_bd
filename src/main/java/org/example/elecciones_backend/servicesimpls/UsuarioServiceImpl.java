package org.example.elecciones_backend.servicesimpls;

import lombok.AllArgsConstructor;
import org.example.elecciones_backend.dtos.UsuarioDTO;
import org.example.elecciones_backend.entities.Usuario;
import org.example.elecciones_backend.exceptions.ResourceNotFoundException;
import org.example.elecciones_backend.mapperdtos.UsuarioMapper;
import org.example.elecciones_backend.repositories.UsuarioRepository;
import org.example.elecciones_backend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioMapper.mapUsuarioDTOToUsuario(usuarioDTO);
        // Guardar contraseña en texto plano
        return UsuarioMapper.mapUsuarioToUsuarioDTO(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioDTO updateUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuario not found with id " + id)
        );

        usuario.setUsername(usuarioDTO.getUsername());
        // Actualizar contraseña en texto plano
        if (usuarioDTO.getPassword() != null && !usuarioDTO.getPassword().isEmpty()) {
            usuario.setPassword(usuarioDTO.getPassword());
        }
        usuario.setRol(usuarioDTO.getRol());

        return UsuarioMapper.mapUsuarioToUsuarioDTO(usuarioRepository.save(usuario));
    }

    @Override
    public String deleteUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuario not found with id " + id)
        );
        usuarioRepository.delete(usuario);
        return "Usuario has been deleted";
    }

    @Override
    public UsuarioDTO getUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuario not found with id " + id)
        );
        return UsuarioMapper.mapUsuarioToUsuarioDTO(usuario);
    }

    @Override
    public UsuarioDTO getUsuarioByUsername(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("Usuario not found with username " + username)
        );
        return UsuarioMapper.mapUsuarioToUsuarioDTO(usuario);
    }

    @Override
    public List<UsuarioDTO> getUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioMapper::mapUsuarioToUsuarioDTO).collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO login(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario no encontrado"));

        // Validar contraseña en texto plano
        if (!usuario.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }

        return new UsuarioDTO(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getRol(),
                usuario.getCreadoEn()
        );
    }

}
