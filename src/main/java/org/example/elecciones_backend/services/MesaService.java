package org.example.elecciones_backend.services;

import org.example.elecciones_backend.dtos.MesaDTO;
import java.util.List;

public interface MesaService {
    MesaDTO createMesa(MesaDTO mesaDTO);
    MesaDTO updateMesa(Long id, MesaDTO mesaDTO);
    String deleteMesa(Long id);
    MesaDTO getMesa(Long id);
    List<MesaDTO> getMesas();
    List<MesaDTO> getMesasByRecinto(Long recintoId);
}
