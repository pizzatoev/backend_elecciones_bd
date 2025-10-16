package org.example.elecciones_backend.repositories;

import org.example.elecciones_backend.entities.Asiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AsientoRepository extends JpaRepository<Asiento, Long> {
    List<Asiento> findByMunicipioId(Long municipioId);
    
    @Query("SELECT a FROM Asiento a LEFT JOIN FETCH a.municipio")
    List<Asiento> findAllWithRelations();
}
