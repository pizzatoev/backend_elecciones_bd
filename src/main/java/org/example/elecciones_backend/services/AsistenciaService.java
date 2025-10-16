package org.example.elecciones_backend.services;

import org.example.elecciones_backend.dtos.AsistenciaDTO;
import org.example.elecciones_backend.entities.Asistencia;
import java.util.List;

public interface AsistenciaService {
    AsistenciaDTO createAsistencia(AsistenciaDTO asistenciaDTO);
    AsistenciaDTO updateAsistencia(Long id, AsistenciaDTO asistenciaDTO);
    String deleteAsistencia(Long id);
    AsistenciaDTO getAsistencia(Long id);
    List<AsistenciaDTO> getAsistencias();
    List<AsistenciaDTO> getAsistenciasByJurado(Long juradoId);
    List<AsistenciaDTO> getAsistenciasByMesa(Long mesaId);
    List<AsistenciaDTO> getAsistenciasByEstado(Asistencia.Estado estado);
    AsistenciaDTO getAsistenciaByJuradoAndMesa(Long juradoId, Long mesaId);
    String marcarPresente(Long id);
    String marcarAusente(Long id);
    String crearAsistenciasParaMesa(Long mesaId);
}
