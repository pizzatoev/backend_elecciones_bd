package org.example.elecciones_backend.repositories;

import org.example.elecciones_backend.entities.Veedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VeedorRepository extends JpaRepository<Veedor, Long> {
    List<Veedor> findByPersonaId(Long personaId);
    List<Veedor> findByInstitucionId(Long institucionId);
    List<Veedor> findByEstado(Veedor.Estado estado);
    Optional<Veedor> findByPersonaIdAndInstitucionId(Long personaId, Long institucionId);
    
    @Query("SELECT v FROM Veedor v LEFT JOIN FETCH v.persona LEFT JOIN FETCH v.institucion")
    List<Veedor> findAllWithRelations();
    
    @Query("SELECT v FROM Veedor v LEFT JOIN FETCH v.persona LEFT JOIN FETCH v.institucion WHERE v.persona.ci = :ci")
    Optional<Veedor> findByPersonaCiWithRelations(String ci);
}
