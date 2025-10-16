package org.example.elecciones_backend.mapperdtos;

import org.example.elecciones_backend.dtos.AsistenciaDTO;
import org.example.elecciones_backend.entities.Asistencia;

public class AsistenciaMapper {

    public static AsistenciaDTO mapAsistenciaToAsistenciaDTO(Asistencia asistencia) {
        AsistenciaDTO dto = new AsistenciaDTO();
        dto.setId(asistencia.getId());
        dto.setIdJurado(asistencia.getJurado() != null ? asistencia.getJurado().getId() : null);
        dto.setIdMesa(asistencia.getMesa() != null ? asistencia.getMesa().getId() : null);
        dto.setEstado(asistencia.getEstado());
        dto.setRegistradoEn(asistencia.getRegistradoEn());
        
        // Campos de la entidad relacionada Jurado
        if (asistencia.getJurado() != null) {
            dto.setJuradoCargo(asistencia.getJurado().getCargo() != null ? 
                asistencia.getJurado().getCargo().toString() : null);
            
            // Campos de la entidad relacionada Persona
            if (asistencia.getJurado().getPersona() != null) {
                dto.setPersonaCi(asistencia.getJurado().getPersona().getCi());
                dto.setPersonaNombre(asistencia.getJurado().getPersona().getNombre());
                dto.setPersonaApellido(asistencia.getJurado().getPersona().getApellido());
            }
        }
        
        return dto;
    }

    public static Asistencia mapAsistenciaDTOToAsistencia(AsistenciaDTO asistenciaDTO) {
        Asistencia asistencia = new Asistencia();
        asistencia.setId(asistenciaDTO.getId());
        asistencia.setEstado(asistenciaDTO.getEstado());
        asistencia.setRegistradoEn(asistenciaDTO.getRegistradoEn());
        return asistencia;
    }
}
