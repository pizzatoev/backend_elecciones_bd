package org.example.elecciones_backend.services;

import org.example.elecciones_backend.dtos.MunicipioDTO;
import java.util.List;

public interface MunicipioService {
    MunicipioDTO createMunicipio(MunicipioDTO municipioDTO);
    MunicipioDTO updateMunicipio(Long id, MunicipioDTO municipioDTO);
    String deleteMunicipio(Long id);
    MunicipioDTO getMunicipio(Long id);
    List<MunicipioDTO> getMunicipios();
    List<MunicipioDTO> getMunicipiosByProvincia(Long provinciaId);
}
