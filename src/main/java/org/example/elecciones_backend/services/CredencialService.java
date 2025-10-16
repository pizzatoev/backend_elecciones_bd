package org.example.elecciones_backend.services;

import org.example.elecciones_backend.dtos.CredencialDTO;
import org.example.elecciones_backend.entities.Credencial;
import java.util.List;

public interface CredencialService {
    CredencialDTO createCredencial(CredencialDTO credencialDTO);
    CredencialDTO updateCredencial(Long id, CredencialDTO credencialDTO);
    String deleteCredencial(Long id);
    CredencialDTO getCredencial(Long id);
    List<CredencialDTO> getCredenciales();
    List<CredencialDTO> getCredencialesByPersona(Long personaId);
    List<CredencialDTO> getCredencialesByRol(Credencial.Rol rol);
    CredencialDTO getCredencialByPersonaAndRol(Long personaId, Credencial.Rol rol);
    CredencialDTO generarCredencial(Long idPersona, Credencial.Rol rol);
    org.springframework.http.ResponseEntity<byte[]> descargarPdf(Long id);
    org.springframework.http.ResponseEntity<byte[]> verQr(Long id);
}
