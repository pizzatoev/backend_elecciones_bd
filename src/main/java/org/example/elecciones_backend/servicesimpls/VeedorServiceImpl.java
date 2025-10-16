package org.example.elecciones_backend.servicesimpls;

import lombok.AllArgsConstructor;
import org.example.elecciones_backend.dtos.VeedorDTO;
import org.example.elecciones_backend.entities.Institucion;
import org.example.elecciones_backend.entities.Persona;
import org.example.elecciones_backend.entities.Veedor;
import org.example.elecciones_backend.exceptions.ResourceNotFoundException;
import org.example.elecciones_backend.mapperdtos.VeedorMapper;
import org.example.elecciones_backend.repositories.InstitucionRepository;
import org.example.elecciones_backend.repositories.PersonaRepository;
import org.example.elecciones_backend.repositories.VeedorRepository;
import org.example.elecciones_backend.services.VeedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VeedorServiceImpl implements VeedorService {

    @Autowired
    private VeedorRepository veedorRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private InstitucionRepository institucionRepository;

    @Override
    public VeedorDTO createVeedor(VeedorDTO veedorDTO) {
        Veedor veedor = VeedorMapper.mapVeedorDTOToVeedor(veedorDTO);
        
        if (veedorDTO.getIdPersona() != null) {
            Persona persona = personaRepository.findById(veedorDTO.getIdPersona())
                    .orElseThrow(() -> new ResourceNotFoundException("Persona not found with id " + veedorDTO.getIdPersona()));
            veedor.setPersona(persona);
        }
        
        if (veedorDTO.getIdInstitucion() != null) {
            Institucion institucion = institucionRepository.findById(veedorDTO.getIdInstitucion())
                    .orElseThrow(() -> new ResourceNotFoundException("Institucion not found with id " + veedorDTO.getIdInstitucion()));
            veedor.setInstitucion(institucion);
        }
        
        return VeedorMapper.mapVeedorToVeedorDTO(veedorRepository.save(veedor));
    }

    @Override
    public VeedorDTO updateVeedor(Long id, VeedorDTO veedorDTO) {
        Veedor veedor = veedorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Veedor not found with id " + id)
        );

        veedor.setCartaRespaldo(veedorDTO.getCartaRespaldo());
        veedor.setEstado(veedorDTO.getEstado());
        veedor.setMotivoRechazo(veedorDTO.getMotivoRechazo());
        
        if (veedorDTO.getIdPersona() != null) {
            Persona persona = personaRepository.findById(veedorDTO.getIdPersona())
                    .orElseThrow(() -> new ResourceNotFoundException("Persona not found with id " + veedorDTO.getIdPersona()));
            veedor.setPersona(persona);
        } else {
            veedor.setPersona(null);
        }
        
        if (veedorDTO.getIdInstitucion() != null) {
            Institucion institucion = institucionRepository.findById(veedorDTO.getIdInstitucion())
                    .orElseThrow(() -> new ResourceNotFoundException("Institucion not found with id " + veedorDTO.getIdInstitucion()));
            veedor.setInstitucion(institucion);
        } else {
            veedor.setInstitucion(null);
        }

        return VeedorMapper.mapVeedorToVeedorDTO(veedorRepository.save(veedor));
    }

    @Override
    public String deleteVeedor(Long id) {
        Veedor veedor = veedorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Veedor not found with id " + id)
        );
        veedorRepository.delete(veedor);
        return "Veedor has been deleted";
    }

    @Override
    public VeedorDTO getVeedor(Long id) {
        Veedor veedor = veedorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Veedor not found with id " + id)
        );
        return VeedorMapper.mapVeedorToVeedorDTO(veedor);
    }

    @Override
    public List<VeedorDTO> getVeedores() {
        List<Veedor> veedores = veedorRepository.findAllWithRelations();
        return veedores.stream().map(VeedorMapper::mapVeedorToVeedorDTO).collect(Collectors.toList());
    }

    @Override
    public List<VeedorDTO> getVeedoresByPersona(Long personaId) {
        List<Veedor> veedores = veedorRepository.findByPersonaId(personaId);
        return veedores.stream().map(VeedorMapper::mapVeedorToVeedorDTO).collect(Collectors.toList());
    }

    @Override
    public List<VeedorDTO> getVeedoresByInstitucion(Long institucionId) {
        List<Veedor> veedores = veedorRepository.findByInstitucionId(institucionId);
        return veedores.stream().map(VeedorMapper::mapVeedorToVeedorDTO).collect(Collectors.toList());
    }

    @Override
    public List<VeedorDTO> getVeedoresByEstado(Veedor.Estado estado) {
        List<Veedor> veedores = veedorRepository.findByEstado(estado);
        return veedores.stream().map(VeedorMapper::mapVeedorToVeedorDTO).collect(Collectors.toList());
    }

    @Override
    public VeedorDTO getVeedorByPersonaAndInstitucion(Long personaId, Long institucionId) {
        Veedor veedor = veedorRepository.findByPersonaIdAndInstitucionId(personaId, institucionId).orElseThrow(
                () -> new ResourceNotFoundException("Veedor not found with persona id " + personaId + " and institucion id " + institucionId)
        );
        return VeedorMapper.mapVeedorToVeedorDTO(veedor);
    }

    @Override
    public VeedorDTO getVeedorByCi(String ci) {
        Veedor veedor = veedorRepository.findByPersonaCiWithRelations(ci).orElseThrow(
                () -> new ResourceNotFoundException("Veedor not found with CI " + ci)
        );
        return VeedorMapper.mapVeedorToVeedorDTO(veedor);
    }

    @Override
    public VeedorDTO aprobarVeedor(Long id) {
        Veedor veedor = veedorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Veedor not found with id " + id)
        );
        veedor.setEstado(Veedor.Estado.APROBADO);
        veedor.setMotivoRechazo(null);
        return VeedorMapper.mapVeedorToVeedorDTO(veedorRepository.save(veedor));
    }

    @Override
    public VeedorDTO rechazarVeedor(Long id, String motivo) {
        Veedor veedor = veedorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Veedor not found with id " + id)
        );
        veedor.setEstado(Veedor.Estado.RECHAZADO);
        veedor.setMotivoRechazo(motivo);
        return VeedorMapper.mapVeedorToVeedorDTO(veedorRepository.save(veedor));
    }
}
