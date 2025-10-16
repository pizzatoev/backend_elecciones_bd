package org.example.elecciones_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DelegadoDTO {
    private Long id;
    private Long idPersona;
    private Long idPartido;
    private Long idMesa;
    private Boolean habilitado;
    
    // Campos de las entidades relacionadas
    private String personaCi;
    private String personaNombre;
    private String personaApellido;
    private String partidoNombre;
    private String partidoSigla;
    private Integer mesaNumero;
    private String recintoNombre;
}
