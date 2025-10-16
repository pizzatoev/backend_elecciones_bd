package org.example.elecciones_backend.repositories;

import org.example.elecciones_backend.entities.Recinto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecintoRepository extends JpaRepository<Recinto, Long> {
    List<Recinto> findByAsientoId(Long asientoId);
    
    @Query("SELECT r FROM Recinto r LEFT JOIN FETCH r.asiento")
    List<Recinto> findAllWithRelations();
}
