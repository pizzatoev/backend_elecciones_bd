package org.example.elecciones_backend.services;

import org.example.elecciones_backend.dtos.PartidoDTO;
import org.example.elecciones_backend.entities.Partido;
import java.util.List;

public interface PartidoService {
    PartidoDTO createPartido(PartidoDTO partidoDTO);
    PartidoDTO updatePartido(Long id, PartidoDTO partidoDTO);
    String deletePartido(Long id);
    PartidoDTO getPartido(Long id);
    PartidoDTO getPartidoBySigla(String sigla);
    List<PartidoDTO> getPartidos();
    List<PartidoDTO> getPartidosByEstado(Partido.Estado estado);
}
