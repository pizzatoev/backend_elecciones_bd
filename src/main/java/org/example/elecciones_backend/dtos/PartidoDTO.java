package org.example.elecciones_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.elecciones_backend.entities.Partido;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartidoDTO {
    private Long id;
    private String sigla;
    private String nombre;
    private Partido.Estado estado;
    private String logoUrl; // Campo logoUrl - Módulo Infraestructura
}
