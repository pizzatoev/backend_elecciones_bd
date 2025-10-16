package org.example.elecciones_backend.servicesimpls;

import lombok.AllArgsConstructor;
import org.example.elecciones_backend.dtos.RecintoDTO;
import org.example.elecciones_backend.entities.Asiento;
import org.example.elecciones_backend.entities.Recinto;
import org.example.elecciones_backend.exceptions.ResourceNotFoundException;
import org.example.elecciones_backend.mapperdtos.RecintoMapper;
import org.example.elecciones_backend.repositories.AsientoRepository;
import org.example.elecciones_backend.repositories.RecintoRepository;
import org.example.elecciones_backend.services.RecintoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecintoServiceImpl implements RecintoService {

    @Autowired
    private RecintoRepository recintoRepository;

    @Autowired
    private AsientoRepository asientoRepository;

    @Override
    public RecintoDTO createRecinto(RecintoDTO recintoDTO) {
        Recinto recinto = RecintoMapper.mapRecintoDTOToRecinto(recintoDTO);
        
        if (recintoDTO.getIdAsiento() != null) {
            Asiento asiento = asientoRepository.findById(recintoDTO.getIdAsiento())
                    .orElseThrow(() -> new ResourceNotFoundException("Asiento not found with id " + recintoDTO.getIdAsiento()));
            recinto.setAsiento(asiento);
        }
        
        return RecintoMapper.mapRecintoToRecintoDTO(recintoRepository.save(recinto));
    }

    @Override
    public RecintoDTO updateRecinto(Long id, RecintoDTO recintoDTO) {
        Recinto recinto = recintoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recinto not found with id " + id)
        );

        recinto.setNombre(recintoDTO.getNombre());
        recinto.setDireccion(recintoDTO.getDireccion());
        
        if (recintoDTO.getIdAsiento() != null) {
            Asiento asiento = asientoRepository.findById(recintoDTO.getIdAsiento())
                    .orElseThrow(() -> new ResourceNotFoundException("Asiento not found with id " + recintoDTO.getIdAsiento()));
            recinto.setAsiento(asiento);
        } else {
            recinto.setAsiento(null);
        }

        return RecintoMapper.mapRecintoToRecintoDTO(recintoRepository.save(recinto));
    }

    @Override
    public String deleteRecinto(Long id) {
        Recinto recinto = recintoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recinto not found with id " + id)
        );
        recintoRepository.delete(recinto);
        return "Recinto has been deleted";
    }

    @Override
    public RecintoDTO getRecinto(Long id) {
        Recinto recinto = recintoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recinto not found with id " + id)
        );
        return RecintoMapper.mapRecintoToRecintoDTO(recinto);
    }

    @Override
    public List<RecintoDTO> getRecintos() {
        List<Recinto> recintos = recintoRepository.findAllWithRelations();
        return recintos.stream().map(RecintoMapper::mapRecintoToRecintoDTO).collect(Collectors.toList());
    }

    @Override
    public List<RecintoDTO> getRecintosByAsiento(Long asientoId) {
        List<Recinto> recintos = recintoRepository.findByAsientoId(asientoId);
        return recintos.stream().map(RecintoMapper::mapRecintoToRecintoDTO).collect(Collectors.toList());
    }
}
