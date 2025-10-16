package org.example.elecciones_backend.servicesimpls;

import lombok.AllArgsConstructor;
import org.example.elecciones_backend.dtos.InstitucionDTO;
import org.example.elecciones_backend.entities.Institucion;
import org.example.elecciones_backend.exceptions.ResourceNotFoundException;
import org.example.elecciones_backend.mapperdtos.InstitucionMapper;
import org.example.elecciones_backend.repositories.InstitucionRepository;
import org.example.elecciones_backend.services.InstitucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InstitucionServiceImpl implements InstitucionService {

    @Autowired
    private InstitucionRepository institucionRepository;

    @Override
    public InstitucionDTO createInstitucion(InstitucionDTO institucionDTO) {
        Institucion institucion = InstitucionMapper.mapInstitucionDTOToInstitucion(institucionDTO);
        return InstitucionMapper.mapInstitucionToInstitucionDTO(institucionRepository.save(institucion));
    }

    @Override
    public InstitucionDTO updateInstitucion(Long id, InstitucionDTO institucionDTO) {
        Institucion institucion = institucionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Institucion not found with id " + id)
        );

        institucion.setNombre(institucionDTO.getNombre());
        institucion.setSigla(institucionDTO.getSigla());

        return InstitucionMapper.mapInstitucionToInstitucionDTO(institucionRepository.save(institucion));
    }

    @Override
    public String deleteInstitucion(Long id) {
        Institucion institucion = institucionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Institucion not found with id " + id)
        );
        institucionRepository.delete(institucion);
        return "Institucion has been deleted";
    }

    @Override
    public InstitucionDTO getInstitucion(Long id) {
        Institucion institucion = institucionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Institucion not found with id " + id)
        );
        return InstitucionMapper.mapInstitucionToInstitucionDTO(institucion);
    }

    @Override
    public InstitucionDTO getInstitucionBySigla(String sigla) {
        Institucion institucion = institucionRepository.findBySigla(sigla).orElseThrow(
                () -> new ResourceNotFoundException("Institucion not found with sigla " + sigla)
        );
        return InstitucionMapper.mapInstitucionToInstitucionDTO(institucion);
    }

    @Override
    public List<InstitucionDTO> getInstituciones() {
        List<Institucion> instituciones = institucionRepository.findAll();
        return instituciones.stream().map(InstitucionMapper::mapInstitucionToInstitucionDTO).collect(Collectors.toList());
    }

    @Override
    public List<InstitucionDTO> getInstitucionesByNombre(String nombre) {
        List<Institucion> instituciones = institucionRepository.findByNombreContainingIgnoreCase(nombre);
        return instituciones.stream().map(InstitucionMapper::mapInstitucionToInstitucionDTO).collect(Collectors.toList());
    }
}
