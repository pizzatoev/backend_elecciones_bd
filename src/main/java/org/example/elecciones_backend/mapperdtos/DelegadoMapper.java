package org.example.elecciones_backend.mapperdtos;

import org.example.elecciones_backend.dtos.DelegadoDTO;
import org.example.elecciones_backend.entities.Delegado;

public class DelegadoMapper {

    public static DelegadoDTO mapDelegadoToDelegadoDTO(Delegado delegado) {
        DelegadoDTO dto = new DelegadoDTO();
        dto.setId(delegado.getId());
        dto.setIdPersona(delegado.getPersona() != null ? delegado.getPersona().getId() : null);
        dto.setIdPartido(delegado.getPartido() != null ? delegado.getPartido().getId() : null);
        dto.setIdMesa(delegado.getMesa() != null ? delegado.getMesa().getId() : null);
        dto.setHabilitado(delegado.getHabilitado());
        
        // Mapear datos de las entidades relacionadas
        if (delegado.getPersona() != null) {
            dto.setPersonaCi(delegado.getPersona().getCi());
            dto.setPersonaNombre(delegado.getPersona().getNombre());
            dto.setPersonaApellido(delegado.getPersona().getApellido());
        }
        if (delegado.getPartido() != null) {
            dto.setPartidoNombre(delegado.getPartido().getNombre());
            dto.setPartidoSigla(delegado.getPartido().getSigla());
        }
        if (delegado.getMesa() != null) {
            dto.setMesaNumero(delegado.getMesa().getNumero());
            if (delegado.getMesa().getRecinto() != null) {
                dto.setRecintoNombre(delegado.getMesa().getRecinto().getNombre());
            }
        }
        
        return dto;
    }

    public static Delegado mapDelegadoDTOToDelegado(DelegadoDTO delegadoDTO) {
        Delegado delegado = new Delegado();
        delegado.setId(delegadoDTO.getId());
        delegado.setHabilitado(delegadoDTO.getHabilitado());
        return delegado;
    }
}
