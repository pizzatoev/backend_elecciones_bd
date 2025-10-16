package org.example.elecciones_backend.repositories;

import org.example.elecciones_backend.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
    List<Provincia> findByDepartamentoId(Long departamentoId);
    
    @Query("SELECT p FROM Provincia p LEFT JOIN FETCH p.departamento")
    List<Provincia> findAllWithRelations();
}
