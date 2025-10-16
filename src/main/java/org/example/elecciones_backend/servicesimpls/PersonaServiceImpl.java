package org.example.elecciones_backend.servicesimpls;

import lombok.AllArgsConstructor;
import org.example.elecciones_backend.dtos.PersonaDTO;
import org.example.elecciones_backend.entities.Persona;
import org.example.elecciones_backend.exceptions.ResourceNotFoundException;
import org.example.elecciones_backend.mapperdtos.PersonaMapper;
import org.example.elecciones_backend.repositories.PersonaRepository;
import org.example.elecciones_backend.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public PersonaDTO createPersona(PersonaDTO personaDTO) {
        Persona persona = PersonaMapper.mapPersonaDTOToPersona(personaDTO);
        return PersonaMapper.mapPersonaToPersonaDTO(personaRepository.save(persona));
    }

    @Override
    public PersonaDTO updatePersona(Long personaId, PersonaDTO personaDTO) {
        Persona persona = personaRepository.findById(personaId).orElseThrow(
                () -> new ResourceNotFoundException("Persona not found with id " + personaId)
        );

        persona.setCi(personaDTO.getCi());
        persona.setNombre(personaDTO.getNombre());
        persona.setApellido(personaDTO.getApellido());
        persona.setFechaNacimiento(personaDTO.getFechaNacimiento());
        persona.setCorreo(personaDTO.getCorreo());
        persona.setTelefono(personaDTO.getTelefono());
        persona.setCiudad(personaDTO.getCiudad());
        persona.setFotoCarnet(personaDTO.getFotoCarnet());

        return PersonaMapper.mapPersonaToPersonaDTO(personaRepository.save(persona));
    }

    @Override
    public String deletePersona(Long personaId) {
        Persona persona = personaRepository.findById(personaId).orElseThrow(
                () -> new ResourceNotFoundException("Persona not found with id " + personaId)
        );
        personaRepository.delete(persona);
        return "Persona has been deleted";
    }

    @Override
    public PersonaDTO getPersona(Long personaId) {
        Persona persona = personaRepository.findById(personaId).orElseThrow(
                () -> new ResourceNotFoundException("Persona not found with id " + personaId)
        );
        return PersonaMapper.mapPersonaToPersonaDTO(persona);
    }

    @Override
    public PersonaDTO getPersonaByCi(String ci) {
        Persona persona = personaRepository.findByCi(ci).orElseThrow(
                () -> new ResourceNotFoundException("Persona not found with CI " + ci)
        );
        return PersonaMapper.mapPersonaToPersonaDTO(persona);
    }

    @Override
    public List<PersonaDTO> getPersonas() {
        List<Persona> personas = personaRepository.findAll();
        return personas.stream().map(PersonaMapper::mapPersonaToPersonaDTO).collect(Collectors.toList());
    }
}
