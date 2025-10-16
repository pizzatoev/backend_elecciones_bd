package org.example.elecciones_backend.services;

import org.example.elecciones_backend.dtos.VeedorDTO;
import org.example.elecciones_backend.entities.Veedor;
import java.util.List;

public interface VeedorService {
    VeedorDTO createVeedor(VeedorDTO veedorDTO);
    VeedorDTO updateVeedor(Long id, VeedorDTO veedorDTO);
    String deleteVeedor(Long id);
    VeedorDTO getVeedor(Long id);
    List<VeedorDTO> getVeedores();
    List<VeedorDTO> getVeedoresByPersona(Long personaId);
    List<VeedorDTO> getVeedoresByInstitucion(Long institucionId);
    List<VeedorDTO> getVeedoresByEstado(Veedor.Estado estado);
    VeedorDTO getVeedorByPersonaAndInstitucion(Long personaId, Long institucionId);
    VeedorDTO getVeedorByCi(String ci);
    VeedorDTO aprobarVeedor(Long id);
    VeedorDTO rechazarVeedor(Long id, String motivo);
}
