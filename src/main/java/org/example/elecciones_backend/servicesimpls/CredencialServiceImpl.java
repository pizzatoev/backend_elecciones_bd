package org.example.elecciones_backend.servicesimpls;

import lombok.AllArgsConstructor;
import org.example.elecciones_backend.dtos.CredencialDTO;
import org.example.elecciones_backend.entities.Credencial;
import org.example.elecciones_backend.entities.Persona;
import org.example.elecciones_backend.exceptions.ResourceNotFoundException;
import org.example.elecciones_backend.mapperdtos.CredencialMapper;
import org.example.elecciones_backend.repositories.CredencialRepository;
import org.example.elecciones_backend.repositories.PersonaRepository;
import org.example.elecciones_backend.services.CredencialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CredencialServiceImpl implements CredencialService {

    @Autowired
    private CredencialRepository credencialRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public CredencialDTO createCredencial(CredencialDTO credencialDTO) {
        Credencial credencial = CredencialMapper.mapCredencialDTOToCredencial(credencialDTO);
        
        if (credencialDTO.getIdPersona() != null) {
            Persona persona = personaRepository.findById(credencialDTO.getIdPersona())
                    .orElseThrow(() -> new ResourceNotFoundException("Persona not found with id " + credencialDTO.getIdPersona()));
            credencial.setPersona(persona);
        }
        
        return CredencialMapper.mapCredencialToCredencialDTO(credencialRepository.save(credencial));
    }

    @Override
    public CredencialDTO updateCredencial(Long id, CredencialDTO credencialDTO) {
        Credencial credencial = credencialRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Credencial not found with id " + id)
        );

        credencial.setRol(credencialDTO.getRol());
        credencial.setQrCode(credencialDTO.getQrCode());
        credencial.setPdfPath(credencialDTO.getPdfPath());
        
        if (credencialDTO.getIdPersona() != null) {
            Persona persona = personaRepository.findById(credencialDTO.getIdPersona())
                    .orElseThrow(() -> new ResourceNotFoundException("Persona not found with id " + credencialDTO.getIdPersona()));
            credencial.setPersona(persona);
        } else {
            credencial.setPersona(null);
        }

        return CredencialMapper.mapCredencialToCredencialDTO(credencialRepository.save(credencial));
    }

    @Override
    public String deleteCredencial(Long id) {
        Credencial credencial = credencialRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Credencial not found with id " + id)
        );
        credencialRepository.delete(credencial);
        return "Credencial has been deleted";
    }

    @Override
    public CredencialDTO getCredencial(Long id) {
        Credencial credencial = credencialRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Credencial not found with id " + id)
        );
        return CredencialMapper.mapCredencialToCredencialDTO(credencial);
    }

    @Override
    public List<CredencialDTO> getCredenciales() {
        List<Credencial> credenciales = credencialRepository.findAllWithRelations();
        return credenciales.stream().map(CredencialMapper::mapCredencialToCredencialDTO).collect(Collectors.toList());
    }

    @Override
    public List<CredencialDTO> getCredencialesByPersona(Long personaId) {
        List<Credencial> credenciales = credencialRepository.findByPersonaId(personaId);
        return credenciales.stream().map(CredencialMapper::mapCredencialToCredencialDTO).collect(Collectors.toList());
    }

    @Override
    public List<CredencialDTO> getCredencialesByRol(Credencial.Rol rol) {
        List<Credencial> credenciales = credencialRepository.findByRol(rol);
        return credenciales.stream().map(CredencialMapper::mapCredencialToCredencialDTO).collect(Collectors.toList());
    }

    @Override
    public CredencialDTO getCredencialByPersonaAndRol(Long personaId, Credencial.Rol rol) {
        Credencial credencial = credencialRepository.findByPersonaIdAndRol(personaId, rol).orElseThrow(
                () -> new ResourceNotFoundException("Credencial not found with persona id " + personaId + " and rol " + rol)
        );
        return CredencialMapper.mapCredencialToCredencialDTO(credencial);
    }

    @Override
    public CredencialDTO generarCredencial(Long idPersona, Credencial.Rol rol) {
        // Verificar que la persona existe
        Persona persona = personaRepository.findById(idPersona)
                .orElseThrow(() -> new ResourceNotFoundException("Persona not found with id " + idPersona));
        
        // Verificar si ya existe una credencial para esta persona y rol
        Credencial credencialExistente = credencialRepository.findByPersonaIdAndRol(idPersona, rol).orElse(null);
        
        if (credencialExistente != null) {
            // Actualizar la credencial existente
            credencialExistente.setQrCode("QR_" + idPersona + "_" + rol + "_" + System.currentTimeMillis());
            credencialExistente.setPdfPath("/credenciales/pdf/" + idPersona + "_" + rol + ".pdf");
            return CredencialMapper.mapCredencialToCredencialDTO(credencialRepository.save(credencialExistente));
        } else {
            // Crear nueva credencial
            Credencial nuevaCredencial = new Credencial();
            nuevaCredencial.setPersona(persona);
            nuevaCredencial.setRol(rol);
            nuevaCredencial.setQrCode("QR_" + idPersona + "_" + rol + "_" + System.currentTimeMillis());
            nuevaCredencial.setPdfPath("/credenciales/pdf/" + idPersona + "_" + rol + ".pdf");
            return CredencialMapper.mapCredencialToCredencialDTO(credencialRepository.save(nuevaCredencial));
        }
    }

    @Override
    public org.springframework.http.ResponseEntity<byte[]> descargarPdf(Long id) {
        Credencial credencial = credencialRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Credencial not found with id " + id)
        );
        
        // Por ahora, devolvemos un PDF de ejemplo
        // En una implementación real, aquí se generaría el PDF con los datos de la credencial
        String pdfContent = "PDF de credencial para " + credencial.getPersona().getNombre() + " " + credencial.getPersona().getApellido();
        byte[] pdfBytes = pdfContent.getBytes();
        
        return org.springframework.http.ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition", "attachment; filename=credencial_" + id + ".pdf")
                .body(pdfBytes);
    }

    @Override
    public org.springframework.http.ResponseEntity<byte[]> verQr(Long id) {
        Credencial credencial = credencialRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Credencial not found with id " + id)
        );
        
        // Por ahora, devolvemos una imagen QR de ejemplo
        // En una implementación real, aquí se generaría el QR con los datos de la credencial
        String qrContent = "QR Code para " + credencial.getPersona().getNombre() + " " + credencial.getPersona().getApellido();
        byte[] qrBytes = qrContent.getBytes();
        
        return org.springframework.http.ResponseEntity.ok()
                .header("Content-Type", "image/png")
                .body(qrBytes);
    }
}
