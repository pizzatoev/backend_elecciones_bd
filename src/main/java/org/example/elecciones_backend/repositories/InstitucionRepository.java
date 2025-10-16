package org.example.elecciones_backend.repositories;

import org.example.elecciones_backend.entities.Institucion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InstitucionRepository extends JpaRepository<Institucion, Long> {
    Optional<Institucion> findBySigla(String sigla);
    List<Institucion> findByNombreContainingIgnoreCase(String nombre);
}
