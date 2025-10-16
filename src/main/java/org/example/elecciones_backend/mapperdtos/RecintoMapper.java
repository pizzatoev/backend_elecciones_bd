package org.example.elecciones_backend.mapperdtos;

import org.example.elecciones_backend.dtos.RecintoDTO;
import org.example.elecciones_backend.entities.Recinto;

public class RecintoMapper {

    public static RecintoDTO mapRecintoToRecintoDTO(Recinto recinto) {
        RecintoDTO dto = new RecintoDTO();
        dto.setId(recinto.getId());
        dto.setNombre(recinto.getNombre());
        dto.setDireccion(recinto.getDireccion());
        dto.setIdAsiento(recinto.getAsiento() != null ? recinto.getAsiento().getId() : null);
        
        // Mapear nombre del asiento
        if (recinto.getAsiento() != null) {
            dto.setAsientoNombre(recinto.getAsiento().getNombre());
        }
        
        return dto;
    }

    public static Recinto mapRecintoDTOToRecinto(RecintoDTO recintoDTO) {
        Recinto recinto = new Recinto();
        recinto.setId(recintoDTO.getId());
        recinto.setNombre(recintoDTO.getNombre());
        recinto.setDireccion(recintoDTO.getDireccion());
        return recinto;
    }
}
