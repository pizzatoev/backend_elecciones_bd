package org.example.elecciones_backend.repositories;

import org.example.elecciones_backend.entities.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    List<Asistencia> findByJuradoId(Long juradoId);
    List<Asistencia> findByMesaId(Long mesaId);
    List<Asistencia> findByEstado(Asistencia.Estado estado);
    Optional<Asistencia> findByJuradoIdAndMesaId(Long juradoId, Long mesaId);
    
    @Query("SELECT a FROM Asistencia a LEFT JOIN FETCH a.jurado j LEFT JOIN FETCH j.persona LEFT JOIN FETCH a.mesa")
    List<Asistencia> findAllWithRelations();
    
    @Query("SELECT a FROM Asistencia a LEFT JOIN FETCH a.jurado j LEFT JOIN FETCH j.persona LEFT JOIN FETCH a.mesa WHERE a.mesa.id = :mesaId")
    List<Asistencia> findByMesaIdWithRelations(Long mesaId);
}
