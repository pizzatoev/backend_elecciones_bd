package org.example.elecciones_backend.mapperdtos;

import org.example.elecciones_backend.dtos.MunicipioDTO;
import org.example.elecciones_backend.entities.Municipio;

public class MunicipioMapper {

    public static MunicipioDTO mapMunicipioToMunicipioDTO(Municipio municipio) {
        MunicipioDTO dto = new MunicipioDTO();
        dto.setId(municipio.getId());
        dto.setNombre(municipio.getNombre());
        dto.setIdProvincia(municipio.getProvincia() != null ? municipio.getProvincia().getId() : null);
        
        // Mapear nombre de la provincia
        if (municipio.getProvincia() != null) {
            dto.setProvinciaNombre(municipio.getProvincia().getNombre());
        }
        
        return dto;
    }

    public static Municipio mapMunicipioDTOToMunicipio(MunicipioDTO municipioDTO) {
        Municipio municipio = new Municipio();
        municipio.setId(municipioDTO.getId());
        municipio.setNombre(municipioDTO.getNombre());
        return municipio;
    }
}
