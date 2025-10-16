package org.example.elecciones_backend.mapperdtos;

import org.example.elecciones_backend.dtos.DepartamentoDTO;
import org.example.elecciones_backend.entities.Departamento;

public class DepartamentoMapper {

    public static DepartamentoDTO mapDepartamentoToDepartamentoDTO(Departamento departamento) {
        return new DepartamentoDTO(
                departamento.getId(),
                departamento.getNombre()
        );
    }

    public static Departamento mapDepartamentoDTOToDepartamento(DepartamentoDTO departamentoDTO) {
        Departamento departamento = new Departamento();
        departamento.setId(departamentoDTO.getId());
        departamento.setNombre(departamentoDTO.getNombre());
        return departamento;
    }
}
