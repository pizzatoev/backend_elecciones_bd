package org.example.elecciones_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsientoDTO {
    private Long id;
    private String nombre;
    private Long idMunicipio;
    
    // Campo de la entidad relacionada
    private String municipioNombre;
}
