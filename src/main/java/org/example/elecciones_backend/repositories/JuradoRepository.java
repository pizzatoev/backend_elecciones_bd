package org.example.elecciones_backend.repositories;

import org.example.elecciones_backend.entities.Jurado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JuradoRepository extends JpaRepository<Jurado, Long> {
    List<Jurado> findByPersonaId(Long personaId);
    List<Jurado> findByMesaId(Long mesaId);
    List<Jurado> findByCargo(Jurado.Cargo cargo);
    List<Jurado> findByVerificado(Boolean verificado);
    Optional<Jurado> findByPersonaIdAndMesaId(Long personaId, Long mesaId);
    
    @Query("SELECT j FROM Jurado j LEFT JOIN FETCH j.persona LEFT JOIN FETCH j.mesa LEFT JOIN FETCH j.mesa.recinto")
    List<Jurado> findAllWithRelations();
    
    @Query("SELECT j FROM Jurado j LEFT JOIN FETCH j.persona LEFT JOIN FETCH j.mesa LEFT JOIN FETCH j.mesa.recinto WHERE j.persona.ci = :ci")
    Optional<Jurado> findByPersonaCiWithRelations(String ci);
}
