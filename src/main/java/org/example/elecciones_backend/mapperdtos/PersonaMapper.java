package org.example.elecciones_backend.mapperdtos;

import org.example.elecciones_backend.dtos.PersonaDTO;
import org.example.elecciones_backend.entities.Persona;

public class PersonaMapper {

    public static PersonaDTO mapPersonaToPersonaDTO(Persona persona) {
        return new PersonaDTO(
                persona.getId(),
                persona.getCi(),
                persona.getNombre(),
                persona.getApellido(),
                persona.getFechaNacimiento(),
                persona.getCorreo(),
                persona.getTelefono(),
                persona.getCiudad(),
                persona.getFotoCarnet()
        );
    }

    public static Persona mapPersonaDTOToPersona(PersonaDTO personaDTO) {
        Persona persona = new Persona();
        persona.setId(personaDTO.getId());
        persona.setCi(personaDTO.getCi());
        persona.setNombre(personaDTO.getNombre());
        persona.setApellido(personaDTO.getApellido());
        persona.setFechaNacimiento(personaDTO.getFechaNacimiento());
        persona.setCorreo(personaDTO.getCorreo());
        persona.setTelefono(personaDTO.getTelefono());
        persona.setCiudad(personaDTO.getCiudad());
        persona.setFotoCarnet(personaDTO.getFotoCarnet());
        return persona;
    }
}
