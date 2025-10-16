package org.example.elecciones_backend.mapperdtos;

import org.example.elecciones_backend.dtos.AsientoDTO;
import org.example.elecciones_backend.entities.Asiento;

public class AsientoMapper {

    public static AsientoDTO mapAsientoToAsientoDTO(Asiento asiento) {
        AsientoDTO dto = new AsientoDTO();
        dto.setId(asiento.getId());
        dto.setNombre(asiento.getNombre());
        dto.setIdMunicipio(asiento.getMunicipio() != null ? asiento.getMunicipio().getId() : null);
        
        // Mapear nombre del municipio
        if (asiento.getMunicipio() != null) {
            dto.setMunicipioNombre(asiento.getMunicipio().getNombre());
        }
        
        return dto;
    }

    public static Asiento mapAsientoDTOToAsiento(AsientoDTO asientoDTO) {
        Asiento asiento = new Asiento();
        asiento.setId(asientoDTO.getId());
        asiento.setNombre(asientoDTO.getNombre());
        return asiento;
    }
}
