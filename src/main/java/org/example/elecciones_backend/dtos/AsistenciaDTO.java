package org.example.elecciones_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.elecciones_backend.entities.Asistencia;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsistenciaDTO {
    private Long id;
    private Long idJurado;
    private Long idMesa;
    private Asistencia.Estado estado;
    private java.sql.Timestamp registradoEn;
    
    // Campos de la entidad relacionada Jurado
    private String juradoCargo;
    
    // Campos de la entidad relacionada Persona
    private String personaCi;
    private String personaNombre;
    private String personaApellido;
}
