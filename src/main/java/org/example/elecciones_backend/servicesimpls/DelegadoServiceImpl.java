package org.example.elecciones_backend.servicesimpls;

import lombok.AllArgsConstructor;
import org.example.elecciones_backend.dtos.DelegadoDTO;
import org.example.elecciones_backend.entities.Delegado;
import org.example.elecciones_backend.entities.Mesa;
import org.example.elecciones_backend.entities.Partido;
import org.example.elecciones_backend.entities.Persona;
import org.example.elecciones_backend.exceptions.ResourceNotFoundException;
import org.example.elecciones_backend.mapperdtos.DelegadoMapper;
import org.example.elecciones_backend.repositories.DelegadoRepository;
import org.example.elecciones_backend.repositories.MesaRepository;
import org.example.elecciones_backend.repositories.PartidoRepository;
import org.example.elecciones_backend.repositories.PersonaRepository;
import org.example.elecciones_backend.services.DelegadoService;
import org.example.elecciones_backend.services.RoleValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DelegadoServiceImpl implements DelegadoService {

    @Autowired
    private DelegadoRepository delegadoRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private RoleValidationService roleValidationService;

    @Override
    public DelegadoDTO createDelegado(DelegadoDTO delegadoDTO) {
        Delegado delegado = DelegadoMapper.mapDelegadoDTOToDelegado(delegadoDTO);
        
        if (delegadoDTO.getIdPersona() != null) {
            Persona persona = personaRepository.findById(delegadoDTO.getIdPersona())
                    .orElseThrow(() -> new ResourceNotFoundException("Persona not found with id " + delegadoDTO.getIdPersona()));
            
            // Validar que la persona no tenga roles duplicados
            roleValidationService.validateUniqueRole(persona, "Delegado");
            
            delegado.setPersona(persona);
        }
        
        if (delegadoDTO.getIdPartido() != null) {
            Partido partido = partidoRepository.findById(delegadoDTO.getIdPartido())
                    .orElseThrow(() -> new ResourceNotFoundException("Partido not found with id " + delegadoDTO.getIdPartido()));
            delegado.setPartido(partido);
        }
        
        if (delegadoDTO.getIdMesa() != null) {
            Mesa mesa = mesaRepository.findById(delegadoDTO.getIdMesa())
                    .orElseThrow(() -> new ResourceNotFoundException("Mesa not found with id " + delegadoDTO.getIdMesa()));
            delegado.setMesa(mesa);
        }
        
        return DelegadoMapper.mapDelegadoToDelegadoDTO(delegadoRepository.save(delegado));
    }

    @Override
    public DelegadoDTO updateDelegado(Long id, DelegadoDTO delegadoDTO) {
        Delegado delegado = delegadoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Delegado not found with id " + id)
        );

        delegado.setHabilitado(delegadoDTO.getHabilitado());
        
        if (delegadoDTO.getIdPersona() != null) {
            Persona persona = personaRepository.findById(delegadoDTO.getIdPersona())
                    .orElseThrow(() -> new ResourceNotFoundException("Persona not found with id " + delegadoDTO.getIdPersona()));
            delegado.setPersona(persona);
        } else {
            delegado.setPersona(null);
        }
        
        if (delegadoDTO.getIdPartido() != null) {
            Partido partido = partidoRepository.findById(delegadoDTO.getIdPartido())
                    .orElseThrow(() -> new ResourceNotFoundException("Partido not found with id " + delegadoDTO.getIdPartido()));
            delegado.setPartido(partido);
        } else {
            delegado.setPartido(null);
        }
        
        if (delegadoDTO.getIdMesa() != null) {
            Mesa mesa = mesaRepository.findById(delegadoDTO.getIdMesa())
                    .orElseThrow(() -> new ResourceNotFoundException("Mesa not found with id " + delegadoDTO.getIdMesa()));
            delegado.setMesa(mesa);
        } else {
            delegado.setMesa(null);
        }

        return DelegadoMapper.mapDelegadoToDelegadoDTO(delegadoRepository.save(delegado));
    }

    @Override
    public String deleteDelegado(Long id) {
        Delegado delegado = delegadoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Delegado not found with id " + id)
        );
        delegadoRepository.delete(delegado);
        return "Delegado has been deleted";
    }

    @Override
    public DelegadoDTO getDelegado(Long id) {
        Delegado delegado = delegadoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Delegado not found with id " + id)
        );
        return DelegadoMapper.mapDelegadoToDelegadoDTO(delegado);
    }

    @Override
    public List<DelegadoDTO> getDelegados() {
        List<Delegado> delegados = delegadoRepository.findAllWithRelations();
        return delegados.stream().map(DelegadoMapper::mapDelegadoToDelegadoDTO).collect(Collectors.toList());
    }

    @Override
    public List<DelegadoDTO> getDelegadosByPersona(Long personaId) {
        List<Delegado> delegados = delegadoRepository.findByPersonaId(personaId);
        return delegados.stream().map(DelegadoMapper::mapDelegadoToDelegadoDTO).collect(Collectors.toList());
    }

    @Override
    public List<DelegadoDTO> getDelegadosByPartido(Long partidoId) {
        List<Delegado> delegados = delegadoRepository.findByPartidoId(partidoId);
        return delegados.stream().map(DelegadoMapper::mapDelegadoToDelegadoDTO).collect(Collectors.toList());
    }

    @Override
    public List<DelegadoDTO> getDelegadosByMesa(Long mesaId) {
        List<Delegado> delegados = delegadoRepository.findByMesaId(mesaId);
        return delegados.stream().map(DelegadoMapper::mapDelegadoToDelegadoDTO).collect(Collectors.toList());
    }

    @Override
    public List<DelegadoDTO> getDelegadosByHabilitado(Boolean habilitado) {
        List<Delegado> delegados = delegadoRepository.findByHabilitado(habilitado);
        return delegados.stream().map(DelegadoMapper::mapDelegadoToDelegadoDTO).collect(Collectors.toList());
    }

    @Override
    public DelegadoDTO getDelegadoByPersonaPartidoAndMesa(Long personaId, Long partidoId, Long mesaId) {
        Delegado delegado = delegadoRepository.findByPersonaIdAndPartidoIdAndMesaId(personaId, partidoId, mesaId).orElseThrow(
                () -> new ResourceNotFoundException("Delegado not found with persona id " + personaId + ", partido id " + partidoId + " and mesa id " + mesaId)
        );
        return DelegadoMapper.mapDelegadoToDelegadoDTO(delegado);
    }

    @Override
    public DelegadoDTO getDelegadoByCi(String ci) {
        Delegado delegado = delegadoRepository.findByPersonaCiWithRelations(ci).orElseThrow(
                () -> new ResourceNotFoundException("Delegado not found with CI " + ci)
        );
        return DelegadoMapper.mapDelegadoToDelegadoDTO(delegado);
    }

    @Override
    public DelegadoDTO toggleHabilitado(Long id) {
        Delegado delegado = delegadoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Delegado not found with id " + id)
        );
        delegado.setHabilitado(!delegado.getHabilitado());
        return DelegadoMapper.mapDelegadoToDelegadoDTO(delegadoRepository.save(delegado));
    }
}
