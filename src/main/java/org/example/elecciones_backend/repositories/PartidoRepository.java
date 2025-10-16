package org.example.elecciones_backend.repositories;

import org.example.elecciones_backend.entities.Partido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PartidoRepository extends JpaRepository<Partido, Long> {
    Optional<Partido> findBySigla(String sigla);
    List<Partido> findByEstado(Partido.Estado estado);
}
