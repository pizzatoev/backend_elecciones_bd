package org.example.elecciones_backend.services;

import org.example.elecciones_backend.dtos.InstitucionDTO;
import java.util.List;

public interface InstitucionService {
    InstitucionDTO createInstitucion(InstitucionDTO institucionDTO);
    InstitucionDTO updateInstitucion(Long id, InstitucionDTO institucionDTO);
    String deleteInstitucion(Long id);
    InstitucionDTO getInstitucion(Long id);
    InstitucionDTO getInstitucionBySigla(String sigla);
    List<InstitucionDTO> getInstituciones();
    List<InstitucionDTO> getInstitucionesByNombre(String nombre);
}
