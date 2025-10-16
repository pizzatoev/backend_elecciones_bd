package org.example.elecciones_backend.services;

import org.example.elecciones_backend.dtos.ProvinciaDTO;
import java.util.List;

public interface ProvinciaService {
    ProvinciaDTO createProvincia(ProvinciaDTO provinciaDTO);
    ProvinciaDTO updateProvincia(Long id, ProvinciaDTO provinciaDTO);
    String deleteProvincia(Long id);
    ProvinciaDTO getProvincia(Long id);
    List<ProvinciaDTO> getProvincias();
    List<ProvinciaDTO> getProvinciasByDepartamento(Long departamentoId);
}
