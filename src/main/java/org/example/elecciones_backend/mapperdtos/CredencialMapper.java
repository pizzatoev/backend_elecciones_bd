package org.example.elecciones_backend.mapperdtos;

import org.example.elecciones_backend.dtos.CredencialDTO;
import org.example.elecciones_backend.entities.Credencial;

public class CredencialMapper {

    public static CredencialDTO mapCredencialToCredencialDTO(Credencial credencial) {
        CredencialDTO dto = new CredencialDTO();
        dto.setId(credencial.getId());
        dto.setIdPersona(credencial.getPersona() != null ? credencial.getPersona().getId() : null);
        dto.setRol(credencial.getRol());
        dto.setQrCode(credencial.getQrCode());
        dto.setPdfPath(credencial.getPdfPath());
        dto.setEmitidoEn(credencial.getEmitidoEn());
        
        // Campos de la entidad relacionada Persona
        if (credencial.getPersona() != null) {
            dto.setPersonaCi(credencial.getPersona().getCi());
            dto.setPersonaNombre(credencial.getPersona().getNombre());
            dto.setPersonaApellido(credencial.getPersona().getApellido());
        }
        
        return dto;
    }

    public static Credencial mapCredencialDTOToCredencial(CredencialDTO credencialDTO) {
        Credencial credencial = new Credencial();
        credencial.setId(credencialDTO.getId());
        credencial.setRol(credencialDTO.getRol());
        credencial.setQrCode(credencialDTO.getQrCode());
        credencial.setPdfPath(credencialDTO.getPdfPath());
        credencial.setEmitidoEn(credencialDTO.getEmitidoEn());
        return credencial;
    }
}
