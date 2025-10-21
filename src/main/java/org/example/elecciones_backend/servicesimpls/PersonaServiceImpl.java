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
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public PersonaDTO createPersona(PersonaDTO personaDTO) {
        // Validaciones de datos - Módulo Infraestructura
        // Responsabilidad: Waldir Trancoso
        validatePersonaData(personaDTO);
        
        // Verificar duplicados
        if (personaRepository.findByCi(personaDTO.getCi()).isPresent()) {
            throw new IllegalArgumentException("Ya existe una persona con CI: " + personaDTO.getCi());
        }
        
        if (personaDTO.getCorreo() != null && !personaDTO.getCorreo().isEmpty()) {
            if (personaRepository.findByCorreo(personaDTO.getCorreo()).isPresent()) {
                throw new IllegalArgumentException("Ya existe una persona con correo: " + personaDTO.getCorreo());
            }
        }
        
        Persona persona = PersonaMapper.mapPersonaDTOToPersona(personaDTO);
        return PersonaMapper.mapPersonaToPersonaDTO(personaRepository.save(persona));
    }

    @Override
    public PersonaDTO updatePersona(Long personaId, PersonaDTO personaDTO) {
        // Validaciones de datos - Módulo Infraestructura
        // Responsabilidad: Waldir Trancoso
        validatePersonaData(personaDTO);
        
        Persona persona = personaRepository.findById(personaId).orElseThrow(
                () -> new ResourceNotFoundException("Persona not found with id " + personaId)
        );

        // Verificar duplicados (excluyendo el registro actual)
        if (!persona.getCi().equals(personaDTO.getCi())) {
            if (personaRepository.findByCi(personaDTO.getCi()).isPresent()) {
                throw new IllegalArgumentException("Ya existe una persona con CI: " + personaDTO.getCi());
            }
        }
        
        if (personaDTO.getCorreo() != null && !personaDTO.getCorreo().isEmpty()) {
            if (!personaDTO.getCorreo().equals(persona.getCorreo())) {
                if (personaRepository.findByCorreo(personaDTO.getCorreo()).isPresent()) {
                    throw new IllegalArgumentException("Ya existe una persona con correo: " + personaDTO.getCorreo());
                }
            }
        }

        persona.setCi(personaDTO.getCi());
        persona.setNombre(personaDTO.getNombre());
        persona.setApellido(personaDTO.getApellido());
        persona.setFechaNacimiento(personaDTO.getFechaNacimiento());
        persona.setCorreo(personaDTO.getCorreo());
        persona.setTelefono(personaDTO.getTelefono());
        persona.setCiudad(personaDTO.getCiudad());
        persona.setFotoCarnet(personaDTO.getFotoCarnet());
        persona.setEstado(personaDTO.getEstado() != null ? personaDTO.getEstado() : Persona.EstadoPersona.VIVO); // Campo estado_vida - Módulo Infraestructura

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
    
    /**
     * Valida los datos de una persona - Módulo Infraestructura
     * Responsabilidad: Waldir Trancoso
     */
    private void validatePersonaData(PersonaDTO personaDTO) {
        // Validar campos obligatorios
        if (personaDTO.getNombre() == null || personaDTO.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        
        if (personaDTO.getApellido() == null || personaDTO.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio");
        }
        
        if (personaDTO.getCi() == null || personaDTO.getCi().trim().isEmpty()) {
            throw new IllegalArgumentException("El CI es obligatorio");
        }
        
        // Validar formato de CI (solo números)
        if (!Pattern.matches("^\\d+$", personaDTO.getCi().trim())) {
            throw new IllegalArgumentException("El CI debe contener solo números");
        }
        
        // Validar formato de correo si se proporciona
        if (personaDTO.getCorreo() != null && !personaDTO.getCorreo().trim().isEmpty()) {
            String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
            if (!Pattern.matches(emailPattern, personaDTO.getCorreo().trim())) {
                throw new IllegalArgumentException("El formato del correo electrónico no es válido");
            }
        }
        
        // Validar longitud de campos
        if (personaDTO.getNombre().trim().length() > 100) {
            throw new IllegalArgumentException("El nombre no puede exceder 100 caracteres");
        }
        
        if (personaDTO.getApellido().trim().length() > 100) {
            throw new IllegalArgumentException("El apellido no puede exceder 100 caracteres");
        }
        
        if (personaDTO.getCi().trim().length() > 20) {
            throw new IllegalArgumentException("El CI no puede exceder 20 caracteres");
        }
        
        if (personaDTO.getCorreo() != null && personaDTO.getCorreo().trim().length() > 100) {
            throw new IllegalArgumentException("El correo no puede exceder 100 caracteres");
        }
    }
}
