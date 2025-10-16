package org.example.elecciones_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.elecciones_backend.entities.Veedor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeedorDTO {
    private Long id;
    private Long idPersona;
    private Long idInstitucion;
    private String cartaRespaldo;
    private Veedor.Estado estado;
    private String motivoRechazo;
    
    // Campos de las entidades relacionadas
    private String personaCi;
    private String personaNombre;
    private String personaApellido;
    private String institucionNombre;
    private String institucionSigla;
}
