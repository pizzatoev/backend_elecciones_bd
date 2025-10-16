package org.example.elecciones_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.elecciones_backend.entities.Credencial;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredencialDTO {
    private Long id;
    private Long idPersona;
    private Credencial.Rol rol;
    private String qrCode;
    private String pdfPath;
    private java.sql.Timestamp emitidoEn;
    
    // Campos de la entidad relacionada Persona
    private String personaCi;
    private String personaNombre;
    private String personaApellido;
}
