package org.example.elecciones_backend.mapperdtos;

import org.example.elecciones_backend.dtos.VeedorDTO;
import org.example.elecciones_backend.entities.Veedor;

public class VeedorMapper {

    public static VeedorDTO mapVeedorToVeedorDTO(Veedor veedor) {
        VeedorDTO dto = new VeedorDTO();
        dto.setId(veedor.getId());
        dto.setIdPersona(veedor.getPersona() != null ? veedor.getPersona().getId() : null);
        dto.setIdInstitucion(veedor.getInstitucion() != null ? veedor.getInstitucion().getId() : null);
        dto.setCartaRespaldo(veedor.getCartaRespaldo());
        dto.setEstado(veedor.getEstado());
        dto.setMotivoRechazo(veedor.getMotivoRechazo());
        
        // Mapear datos de las entidades relacionadas
        if (veedor.getPersona() != null) {
            dto.setPersonaCi(veedor.getPersona().getCi());
            dto.setPersonaNombre(veedor.getPersona().getNombre());
            dto.setPersonaApellido(veedor.getPersona().getApellido());
        }
        if (veedor.getInstitucion() != null) {
            dto.setInstitucionNombre(veedor.getInstitucion().getNombre());
            dto.setInstitucionSigla(veedor.getInstitucion().getSigla());
        }
        
        return dto;
    }

    public static Veedor mapVeedorDTOToVeedor(VeedorDTO veedorDTO) {
        Veedor veedor = new Veedor();
        veedor.setId(veedorDTO.getId());
        veedor.setCartaRespaldo(veedorDTO.getCartaRespaldo());
        veedor.setEstado(veedorDTO.getEstado());
        veedor.setMotivoRechazo(veedorDTO.getMotivoRechazo());
        return veedor;
    }
}
