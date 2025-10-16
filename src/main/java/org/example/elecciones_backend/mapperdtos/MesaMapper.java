package org.example.elecciones_backend.mapperdtos;

import org.example.elecciones_backend.dtos.MesaDTO;
import org.example.elecciones_backend.entities.Mesa;

public class MesaMapper {

    public static MesaDTO mapMesaToMesaDTO(Mesa mesa) {
        MesaDTO dto = new MesaDTO();
        dto.setId(mesa.getId());
        dto.setNumero(mesa.getNumero());
        dto.setIdRecinto(mesa.getRecinto() != null ? mesa.getRecinto().getId() : null);
        
        // Mapear nombre del recinto
        if (mesa.getRecinto() != null) {
            dto.setRecintoNombre(mesa.getRecinto().getNombre());
        }
        
        return dto;
    }

    public static Mesa mapMesaDTOToMesa(MesaDTO mesaDTO) {
        Mesa mesa = new Mesa();
        mesa.setId(mesaDTO.getId());
        mesa.setNumero(mesaDTO.getNumero());
        return mesa;
    }
}
