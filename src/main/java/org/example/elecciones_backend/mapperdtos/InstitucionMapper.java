package org.example.elecciones_backend.mapperdtos;

import org.example.elecciones_backend.dtos.InstitucionDTO;
import org.example.elecciones_backend.entities.Institucion;

public class InstitucionMapper {

    public static InstitucionDTO mapInstitucionToInstitucionDTO(Institucion institucion) {
        return new InstitucionDTO(
                institucion.getId(),
                institucion.getNombre(),
                institucion.getSigla()
        );
    }

    public static Institucion mapInstitucionDTOToInstitucion(InstitucionDTO institucionDTO) {
        Institucion institucion = new Institucion();
        institucion.setId(institucionDTO.getId());
        institucion.setNombre(institucionDTO.getNombre());
        institucion.setSigla(institucionDTO.getSigla());
        return institucion;
    }
}
