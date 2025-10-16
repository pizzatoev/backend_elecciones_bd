package org.example.elecciones_backend.repositories;

import org.example.elecciones_backend.entities.Delegado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DelegadoRepository extends JpaRepository<Delegado, Long> {
    List<Delegado> findByPersonaId(Long personaId);
    List<Delegado> findByPartidoId(Long partidoId);
    List<Delegado> findByMesaId(Long mesaId);
    List<Delegado> findByHabilitado(Boolean habilitado);
    Optional<Delegado> findByPersonaIdAndPartidoIdAndMesaId(Long personaId, Long partidoId, Long mesaId);
    
    @Query("SELECT d FROM Delegado d LEFT JOIN FETCH d.persona LEFT JOIN FETCH d.partido LEFT JOIN FETCH d.mesa LEFT JOIN FETCH d.mesa.recinto")
    List<Delegado> findAllWithRelations();
    
    @Query("SELECT d FROM Delegado d LEFT JOIN FETCH d.persona LEFT JOIN FETCH d.partido LEFT JOIN FETCH d.mesa LEFT JOIN FETCH d.mesa.recinto WHERE d.persona.ci = :ci")
    Optional<Delegado> findByPersonaCiWithRelations(String ci);
}
