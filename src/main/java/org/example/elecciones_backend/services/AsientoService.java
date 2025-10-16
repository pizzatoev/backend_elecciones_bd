package org.example.elecciones_backend.services;

import org.example.elecciones_backend.dtos.AsientoDTO;
import java.util.List;

public interface AsientoService {
    AsientoDTO createAsiento(AsientoDTO asientoDTO);
    AsientoDTO updateAsiento(Long id, AsientoDTO asientoDTO);
    String deleteAsiento(Long id);
    AsientoDTO getAsiento(Long id);
    List<AsientoDTO> getAsientos();
    List<AsientoDTO> getAsientosByMunicipio(Long municipioId);
}
