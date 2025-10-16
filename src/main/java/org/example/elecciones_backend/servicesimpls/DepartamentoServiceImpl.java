package org.example.elecciones_backend.servicesimpls;

import lombok.AllArgsConstructor;
import org.example.elecciones_backend.dtos.DepartamentoDTO;
import org.example.elecciones_backend.entities.Departamento;
import org.example.elecciones_backend.exceptions.ResourceNotFoundException;
import org.example.elecciones_backend.mapperdtos.DepartamentoMapper;
import org.example.elecciones_backend.repositories.DepartamentoRepository;
import org.example.elecciones_backend.services.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    public DepartamentoDTO createDepartamento(DepartamentoDTO departamentoDTO) {
        Departamento departamento = DepartamentoMapper.mapDepartamentoDTOToDepartamento(departamentoDTO);
        return DepartamentoMapper.mapDepartamentoToDepartamentoDTO(departamentoRepository.save(departamento));
    }

    @Override
    public DepartamentoDTO updateDepartamento(Long id, DepartamentoDTO departamentoDTO) {
        Departamento departamento = departamentoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Departamento not found with id " + id)
        );

        departamento.setNombre(departamentoDTO.getNombre());

        return DepartamentoMapper.mapDepartamentoToDepartamentoDTO(departamentoRepository.save(departamento));
    }

    @Override
    public String deleteDepartamento(Long id) {
        Departamento departamento = departamentoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Departamento not found with id " + id)
        );
        departamentoRepository.delete(departamento);
        return "Departamento has been deleted";
    }

    @Override
    public DepartamentoDTO getDepartamento(Long id) {
        Departamento departamento = departamentoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Departamento not found with id " + id)
        );
        return DepartamentoMapper.mapDepartamentoToDepartamentoDTO(departamento);
    }

    @Override
    public List<DepartamentoDTO> getDepartamentos() {
        List<Departamento> departamentos = departamentoRepository.findAll();
        return departamentos.stream().map(DepartamentoMapper::mapDepartamentoToDepartamentoDTO).collect(Collectors.toList());
    }
}
