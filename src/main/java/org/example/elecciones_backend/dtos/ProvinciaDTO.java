package org.example.elecciones_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinciaDTO {
    private Long id;
    private String nombre;
    private Long idDepartamento;
    
    // Campo de la entidad relacionada
    private String departamentoNombre;
}
