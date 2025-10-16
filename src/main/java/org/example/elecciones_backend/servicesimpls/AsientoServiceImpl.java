package org.example.elecciones_backend.servicesimpls;

import lombok.AllArgsConstructor;
import org.example.elecciones_backend.dtos.AsientoDTO;
import org.example.elecciones_backend.entities.Asiento;
import org.example.elecciones_backend.entities.Municipio;
import org.example.elecciones_backend.exceptions.ResourceNotFoundException;
import org.example.elecciones_backend.mapperdtos.AsientoMapper;
import org.example.elecciones_backend.repositories.AsientoRepository;
import org.example.elecciones_backend.repositories.MunicipioRepository;
import org.example.elecciones_backend.services.AsientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AsientoServiceImpl implements AsientoService {

    @Autowired
    private AsientoRepository asientoRepository;

    @Autowired
    private MunicipioRepository municipioRepository;

    @Override
    public AsientoDTO createAsiento(AsientoDTO asientoDTO) {
        Asiento asiento = AsientoMapper.mapAsientoDTOToAsiento(asientoDTO);
        
        if (asientoDTO.getIdMunicipio() != null) {
            Municipio municipio = municipioRepository.findById(asientoDTO.getIdMunicipio())
                    .orElseThrow(() -> new ResourceNotFoundException("Municipio not found with id " + asientoDTO.getIdMunicipio()));
            asiento.setMunicipio(municipio);
        }
        
        return AsientoMapper.mapAsientoToAsientoDTO(asientoRepository.save(asiento));
    }

    @Override
    public AsientoDTO updateAsiento(Long id, AsientoDTO asientoDTO) {
        Asiento asiento = asientoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Asiento not found with id " + id)
        );

        asiento.setNombre(asientoDTO.getNombre());
        
        if (asientoDTO.getIdMunicipio() != null) {
            Municipio municipio = municipioRepository.findById(asientoDTO.getIdMunicipio())
                    .orElseThrow(() -> new ResourceNotFoundException("Municipio not found with id " + asientoDTO.getIdMunicipio()));
            asiento.setMunicipio(municipio);
        } else {
            asiento.setMunicipio(null);
        }

        return AsientoMapper.mapAsientoToAsientoDTO(asientoRepository.save(asiento));
    }

    @Override
    public String deleteAsiento(Long id) {
        Asiento asiento = asientoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Asiento not found with id " + id)
        );
        asientoRepository.delete(asiento);
        return "Asiento has been deleted";
    }

    @Override
    public AsientoDTO getAsiento(Long id) {
        Asiento asiento = asientoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Asiento not found with id " + id)
        );
        return AsientoMapper.mapAsientoToAsientoDTO(asiento);
    }

    @Override
    public List<AsientoDTO> getAsientos() {
        List<Asiento> asientos = asientoRepository.findAllWithRelations();
        return asientos.stream().map(AsientoMapper::mapAsientoToAsientoDTO).collect(Collectors.toList());
    }

    @Override
    public List<AsientoDTO> getAsientosByMunicipio(Long municipioId) {
        List<Asiento> asientos = asientoRepository.findByMunicipioId(municipioId);
        return asientos.stream().map(AsientoMapper::mapAsientoToAsientoDTO).collect(Collectors.toList());
    }
}
