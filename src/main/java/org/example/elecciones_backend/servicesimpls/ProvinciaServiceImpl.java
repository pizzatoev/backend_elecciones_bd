package org.example.elecciones_backend.servicesimpls;

import lombok.AllArgsConstructor;
import org.example.elecciones_backend.dtos.ProvinciaDTO;
import org.example.elecciones_backend.entities.Departamento;
import org.example.elecciones_backend.entities.Provincia;
import org.example.elecciones_backend.exceptions.ResourceNotFoundException;
import org.example.elecciones_backend.mapperdtos.ProvinciaMapper;
import org.example.elecciones_backend.repositories.DepartamentoRepository;
import org.example.elecciones_backend.repositories.ProvinciaRepository;
import org.example.elecciones_backend.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProvinciaServiceImpl implements ProvinciaService {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    public ProvinciaDTO createProvincia(ProvinciaDTO provinciaDTO) {
        Provincia provincia = ProvinciaMapper.mapProvinciaDTOToProvincia(provinciaDTO);
        
        if (provinciaDTO.getIdDepartamento() != null) {
            Departamento departamento = departamentoRepository.findById(provinciaDTO.getIdDepartamento())
                    .orElseThrow(() -> new ResourceNotFoundException("Departamento not found with id " + provinciaDTO.getIdDepartamento()));
            provincia.setDepartamento(departamento);
        }
        
        return ProvinciaMapper.mapProvinciaToProvinciaDTO(provinciaRepository.save(provincia));
    }

    @Override
    public ProvinciaDTO updateProvincia(Long id, ProvinciaDTO provinciaDTO) {
        Provincia provincia = provinciaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Provincia not found with id " + id)
        );

        provincia.setNombre(provinciaDTO.getNombre());
        
        if (provinciaDTO.getIdDepartamento() != null) {
            Departamento departamento = departamentoRepository.findById(provinciaDTO.getIdDepartamento())
                    .orElseThrow(() -> new ResourceNotFoundException("Departamento not found with id " + provinciaDTO.getIdDepartamento()));
            provincia.setDepartamento(departamento);
        } else {
            provincia.setDepartamento(null);
        }

        return ProvinciaMapper.mapProvinciaToProvinciaDTO(provinciaRepository.save(provincia));
    }

    @Override
    public String deleteProvincia(Long id) {
        Provincia provincia = provinciaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Provincia not found with id " + id)
        );
        provinciaRepository.delete(provincia);
        return "Provincia has been deleted";
    }

    @Override
    public ProvinciaDTO getProvincia(Long id) {
        Provincia provincia = provinciaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Provincia not found with id " + id)
        );
        return ProvinciaMapper.mapProvinciaToProvinciaDTO(provincia);
    }

    @Override
    public List<ProvinciaDTO> getProvincias() {
        List<Provincia> provincias = provinciaRepository.findAllWithRelations();
        return provincias.stream().map(ProvinciaMapper::mapProvinciaToProvinciaDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProvinciaDTO> getProvinciasByDepartamento(Long departamentoId) {
        List<Provincia> provincias = provinciaRepository.findByDepartamentoId(departamentoId);
        return provincias.stream().map(ProvinciaMapper::mapProvinciaToProvinciaDTO).collect(Collectors.toList());
    }
}
