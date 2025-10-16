package org.example.elecciones_backend.services;

import org.example.elecciones_backend.dtos.JuradoDTO;
import org.example.elecciones_backend.entities.Jurado;
import java.util.List;

public interface JuradoService {
    JuradoDTO createJurado(JuradoDTO juradoDTO);
    JuradoDTO updateJurado(Long id, JuradoDTO juradoDTO);
    String deleteJurado(Long id);
    JuradoDTO getJurado(Long id);
    List<JuradoDTO> getJurados();
    List<JuradoDTO> getJuradosByPersona(Long personaId);
    List<JuradoDTO> getJuradosByMesa(Long mesaId);
    List<JuradoDTO> getJuradosByCargo(Jurado.Cargo cargo);
    List<JuradoDTO> getJuradosByVerificado(Boolean verificado);
    JuradoDTO getJuradoByPersonaAndMesa(Long personaId, Long mesaId);
    JuradoDTO getJuradoByCi(String ci);
    
    // Métodos para sorteo
    List<JuradoDTO> sortearJurados();
    void eliminarSorteo();
}
