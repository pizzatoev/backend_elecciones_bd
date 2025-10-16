package org.example.elecciones_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.elecciones_backend.entities.Jurado;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JuradoDTO {
    private Long id;
    private Long idPersona;
    private Long idMesa;
    private Jurado.Cargo cargo;
    private Boolean verificado;
    
    // Campos de las entidades relacionadas
    private String personaCi;
    private String personaNombre;
    private String personaApellido;
    private Integer mesaNumero;
    private String recintoNombre;
}
