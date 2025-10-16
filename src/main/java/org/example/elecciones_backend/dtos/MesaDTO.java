package org.example.elecciones_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MesaDTO {
    private Long id;
    private Integer numero;
    private Long idRecinto;
    
    // Campo de la entidad relacionada
    private String recintoNombre;
}
