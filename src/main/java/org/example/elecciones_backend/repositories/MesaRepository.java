package org.example.elecciones_backend.repositories;

import org.example.elecciones_backend.entities.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MesaRepository extends JpaRepository<Mesa, Long> {
    List<Mesa> findByRecintoId(Long recintoId);
    
    @Query("SELECT m FROM Mesa m LEFT JOIN FETCH m.recinto")
    List<Mesa> findAllWithRelations();
}
