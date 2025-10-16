package org.example.elecciones_backend.services;

import org.example.elecciones_backend.dtos.PersonaDTO;
import java.util.List;

public interface PersonaService {
    PersonaDTO createPersona(PersonaDTO personaDTO);
    PersonaDTO updatePersona(Long personaId, PersonaDTO personaDTO);
    String deletePersona(Long personaId);
    PersonaDTO getPersona(Long personaId);
    PersonaDTO getPersonaByCi(String ci);
    List<PersonaDTO> getPersonas();
}
