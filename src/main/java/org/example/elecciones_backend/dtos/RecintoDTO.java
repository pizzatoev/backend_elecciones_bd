package org.example.elecciones_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecintoDTO {
    private Long id;
    private String nombre;
    private String direccion;
    private Long idAsiento;
    
    // Campo de la entidad relacionada
    private String asientoNombre;
}
