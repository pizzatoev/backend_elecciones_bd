package org.example.elecciones_backend.repositories;

import org.example.elecciones_backend.entities.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}
