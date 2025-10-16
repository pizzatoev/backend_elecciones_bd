package org.example.elecciones_backend.services;

import org.example.elecciones_backend.dtos.RecintoDTO;
import java.util.List;

public interface RecintoService {
    RecintoDTO createRecinto(RecintoDTO recintoDTO);
    RecintoDTO updateRecinto(Long id, RecintoDTO recintoDTO);
    String deleteRecinto(Long id);
    RecintoDTO getRecinto(Long id);
    List<RecintoDTO> getRecintos();
    List<RecintoDTO> getRecintosByAsiento(Long asientoId);
}
