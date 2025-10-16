package org.example.elecciones_backend.services;

import org.example.elecciones_backend.dtos.DelegadoDTO;
import java.util.List;

public interface DelegadoService {
    DelegadoDTO createDelegado(DelegadoDTO delegadoDTO);
    DelegadoDTO updateDelegado(Long id, DelegadoDTO delegadoDTO);
    String deleteDelegado(Long id);
    DelegadoDTO getDelegado(Long id);
    List<DelegadoDTO> getDelegados();
    List<DelegadoDTO> getDelegadosByPersona(Long personaId);
    List<DelegadoDTO> getDelegadosByPartido(Long partidoId);
    List<DelegadoDTO> getDelegadosByMesa(Long mesaId);
    List<DelegadoDTO> getDelegadosByHabilitado(Boolean habilitado);
    DelegadoDTO getDelegadoByPersonaPartidoAndMesa(Long personaId, Long partidoId, Long mesaId);
    DelegadoDTO getDelegadoByCi(String ci);
    DelegadoDTO toggleHabilitado(Long id);
}
