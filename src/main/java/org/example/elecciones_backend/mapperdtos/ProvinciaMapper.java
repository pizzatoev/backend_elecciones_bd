package org.example.elecciones_backend.mapperdtos;

import org.example.elecciones_backend.dtos.ProvinciaDTO;
import org.example.elecciones_backend.entities.Provincia;

public class ProvinciaMapper {

    public static ProvinciaDTO mapProvinciaToProvinciaDTO(Provincia provincia) {
        ProvinciaDTO dto = new ProvinciaDTO();
        dto.setId(provincia.getId());
        dto.setNombre(provincia.getNombre());
        dto.setIdDepartamento(provincia.getDepartamento() != null ? provincia.getDepartamento().getId() : null);
        
        // Mapear nombre del departamento
        if (provincia.getDepartamento() != null) {
            dto.setDepartamentoNombre(provincia.getDepartamento().getNombre());
        }
        
        return dto;
    }

    public static Provincia mapProvinciaDTOToProvincia(ProvinciaDTO provinciaDTO) {
        Provincia provincia = new Provincia();
        provincia.setId(provinciaDTO.getId());
        provincia.setNombre(provinciaDTO.getNombre());
        // El departamento se manejará en el servicio
        return provincia;
    }
}
