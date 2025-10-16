package org.example.elecciones_backend.repositories;

import org.example.elecciones_backend.entities.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
    List<Municipio> findByProvinciaId(Long provinciaId);
    
    @Query("SELECT m FROM Municipio m LEFT JOIN FETCH m.provincia")
    List<Municipio> findAllWithRelations();
}
