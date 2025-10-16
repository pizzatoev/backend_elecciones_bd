package org.example.elecciones_backend.repositories;

import org.example.elecciones_backend.entities.Credencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CredencialRepository extends JpaRepository<Credencial, Long> {
    List<Credencial> findByPersonaId(Long personaId);
    List<Credencial> findByRol(Credencial.Rol rol);
    Optional<Credencial> findByPersonaIdAndRol(Long personaId, Credencial.Rol rol);
    
    @Query("SELECT c FROM Credencial c LEFT JOIN FETCH c.persona")
    List<Credencial> findAllWithRelations();
}
