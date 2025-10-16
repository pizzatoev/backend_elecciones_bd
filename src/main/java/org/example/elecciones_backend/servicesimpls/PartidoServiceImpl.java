package org.example.elecciones_backend.servicesimpls;

import lombok.AllArgsConstructor;
import org.example.elecciones_backend.dtos.PartidoDTO;
import org.example.elecciones_backend.entities.Partido;
import org.example.elecciones_backend.exceptions.ResourceNotFoundException;
import org.example.elecciones_backend.mapperdtos.PartidoMapper;
import org.example.elecciones_backend.repositories.PartidoRepository;
import org.example.elecciones_backend.services.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PartidoServiceImpl implements PartidoService {

    @Autowired
    private PartidoRepository partidoRepository;

    @Override
    public PartidoDTO createPartido(PartidoDTO partidoDTO) {
        Partido partido = PartidoMapper.mapPartidoDTOToPartido(partidoDTO);
        return PartidoMapper.mapPartidoToPartidoDTO(partidoRepository.save(partido));
    }

    @Override
    public PartidoDTO updatePartido(Long id, PartidoDTO partidoDTO) {
        Partido partido = partidoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Partido not found with id " + id)
        );

        partido.setSigla(partidoDTO.getSigla());
        partido.setNombre(partidoDTO.getNombre());
        partido.setEstado(partidoDTO.getEstado());

        return PartidoMapper.mapPartidoToPartidoDTO(partidoRepository.save(partido));
    }

    @Override
    public String deletePartido(Long id) {
        Partido partido = partidoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Partido not found with id " + id)
        );
        partidoRepository.delete(partido);
        return "Partido has been deleted";
    }

    @Override
    public PartidoDTO getPartido(Long id) {
        Partido partido = partidoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Partido not found with id " + id)
        );
        return PartidoMapper.mapPartidoToPartidoDTO(partido);
    }

    @Override
    public PartidoDTO getPartidoBySigla(String sigla) {
        Partido partido = partidoRepository.findBySigla(sigla).orElseThrow(
                () -> new ResourceNotFoundException("Partido not found with sigla " + sigla)
        );
        return PartidoMapper.mapPartidoToPartidoDTO(partido);
    }

    @Override
    public List<PartidoDTO> getPartidos() {
        List<Partido> partidos = partidoRepository.findAll();
        return partidos.stream().map(PartidoMapper::mapPartidoToPartidoDTO).collect(Collectors.toList());
    }

    @Override
    public List<PartidoDTO> getPartidosByEstado(Partido.Estado estado) {
        List<Partido> partidos = partidoRepository.findByEstado(estado);
        return partidos.stream().map(PartidoMapper::mapPartidoToPartidoDTO).collect(Collectors.toList());
    }
}
