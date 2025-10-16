package org.example.elecciones_backend.services;

import org.example.elecciones_backend.dtos.DepartamentoDTO;
import java.util.List;

public interface DepartamentoService {
    DepartamentoDTO createDepartamento(DepartamentoDTO departamentoDTO);
    DepartamentoDTO updateDepartamento(Long id, DepartamentoDTO departamentoDTO);
    String deleteDepartamento(Long id);
    DepartamentoDTO getDepartamento(Long id);
    List<DepartamentoDTO> getDepartamentos();
}
