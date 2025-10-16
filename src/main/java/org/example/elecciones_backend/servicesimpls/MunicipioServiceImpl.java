package org.example.elecciones_backend.servicesimpls;

import lombok.AllArgsConstructor;
import org.example.elecciones_backend.dtos.MunicipioDTO;
import org.example.elecciones_backend.entities.Municipio;
import org.example.elecciones_backend.entities.Provincia;
import org.example.elecciones_backend.exceptions.ResourceNotFoundException;
import org.example.elecciones_backend.mapperdtos.MunicipioMapper;
import org.example.elecciones_backend.repositories.MunicipioRepository;
import org.example.elecciones_backend.repositories.ProvinciaRepository;
import org.example.elecciones_backend.services.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MunicipioServiceImpl implements MunicipioService {

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Override
    public MunicipioDTO createMunicipio(MunicipioDTO municipioDTO) {
        Municipio municipio = MunicipioMapper.mapMunicipioDTOToMunicipio(municipioDTO);
        
        if (municipioDTO.getIdProvincia() != null) {
            Provincia provincia = provinciaRepository.findById(municipioDTO.getIdProvincia())
                    .orElseThrow(() -> new ResourceNotFoundException("Provincia not found with id " + municipioDTO.getIdProvincia()));
            municipio.setProvincia(provincia);
        }
        
        return MunicipioMapper.mapMunicipioToMunicipioDTO(municipioRepository.save(municipio));
    }

    @Override
    public MunicipioDTO updateMunicipio(Long id, MunicipioDTO municipioDTO) {
        Municipio municipio = municipioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Municipio not found with id " + id)
        );

        municipio.setNombre(municipioDTO.getNombre());
        
        if (municipioDTO.getIdProvincia() != null) {
            Provincia provincia = provinciaRepository.findById(municipioDTO.getIdProvincia())
                    .orElseThrow(() -> new ResourceNotFoundException("Provincia not found with id " + municipioDTO.getIdProvincia()));
            municipio.setProvincia(provincia);
        } else {
            municipio.setProvincia(null);
        }

        return MunicipioMapper.mapMunicipioToMunicipioDTO(municipioRepository.save(municipio));
    }

    @Override
    public String deleteMunicipio(Long id) {
        Municipio municipio = municipioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Municipio not found with id " + id)
        );
        municipioRepository.delete(municipio);
        return "Municipio has been deleted";
    }

    @Override
    public MunicipioDTO getMunicipio(Long id) {
        Municipio municipio = municipioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Municipio not found with id " + id)
        );
        return MunicipioMapper.mapMunicipioToMunicipioDTO(municipio);
    }

    @Override
    public List<MunicipioDTO> getMunicipios() {
        List<Municipio> municipios = municipioRepository.findAllWithRelations();
        return municipios.stream().map(MunicipioMapper::mapMunicipioToMunicipioDTO).collect(Collectors.toList());
    }

    @Override
    public List<MunicipioDTO> getMunicipiosByProvincia(Long provinciaId) {
        List<Municipio> municipios = municipioRepository.findByProvinciaId(provinciaId);
        return municipios.stream().map(MunicipioMapper::mapMunicipioToMunicipioDTO).collect(Collectors.toList());
    }
}
