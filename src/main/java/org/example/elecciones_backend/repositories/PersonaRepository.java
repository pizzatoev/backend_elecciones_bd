package org.example.elecciones_backend.repositories;

import org.example.elecciones_backend.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Optional<Persona> findByCi(String ci); // Para buscar personas por CI
    Optional<Persona> findByCorreo(String correo); // Para buscar personas por correo - Módulo Infraestructura
}
