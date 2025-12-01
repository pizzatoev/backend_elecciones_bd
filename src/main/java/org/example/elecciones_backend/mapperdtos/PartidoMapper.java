package org.example.elecciones_backend.mapperdtos;

import org.example.elecciones_backend.dtos.PartidoDTO;
import org.example.elecciones_backend.entities.Partido;

public class PartidoMapper {

    public static PartidoDTO mapPartidoToPartidoDTO(Partido partido) {
        return new PartidoDTO(
                partido.getId(),
                partido.getSigla(),
                partido.getNombre(),
                partido.getEstado(),
                partido.getLogoUrl() // Campo logoUrl - Módulo Infraestructura
        );
    }

    public static Partido mapPartidoDTOToPartido(PartidoDTO partidoDTO) {
        Partido partido = new Partido();
        partido.setId(partidoDTO.getId());
        partido.setSigla(partidoDTO.getSigla());
        partido.setNombre(partidoDTO.getNombre());
        partido.setEstado(partidoDTO.getEstado());
        partido.setLogoUrl(partidoDTO.getLogoUrl()); // Campo logoUrl - Módulo Infraestructura
        return partido;
    }
}
