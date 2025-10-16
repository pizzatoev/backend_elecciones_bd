package org.example.elecciones_backend.mapperdtos;

import org.example.elecciones_backend.dtos.JuradoDTO;
import org.example.elecciones_backend.entities.Jurado;

public class JuradoMapper {

    public static JuradoDTO mapJuradoToJuradoDTO(Jurado jurado) {
        JuradoDTO dto = new JuradoDTO();
        dto.setId(jurado.getId());
        dto.setIdPersona(jurado.getPersona() != null ? jurado.getPersona().getId() : null);
        dto.setIdMesa(jurado.getMesa() != null ? jurado.getMesa().getId() : null);
        dto.setCargo(jurado.getCargo());
        dto.setVerificado(jurado.getVerificado());
        
        // Mapear datos de las entidades relacionadas
        if (jurado.getPersona() != null) {
            dto.setPersonaCi(jurado.getPersona().getCi());
            dto.setPersonaNombre(jurado.getPersona().getNombre());
            dto.setPersonaApellido(jurado.getPersona().getApellido());
        }
        if (jurado.getMesa() != null) {
            dto.setMesaNumero(jurado.getMesa().getNumero());
            if (jurado.getMesa().getRecinto() != null) {
                dto.setRecintoNombre(jurado.getMesa().getRecinto().getNombre());
            }
        }
        
        return dto;
    }

    public static Jurado mapJuradoDTOToJurado(JuradoDTO juradoDTO) {
        Jurado jurado = new Jurado();
        jurado.setId(juradoDTO.getId());
        jurado.setCargo(juradoDTO.getCargo());
        jurado.setVerificado(juradoDTO.getVerificado());
        return jurado;
    }
}
