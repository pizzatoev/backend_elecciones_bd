package org.example.elecciones_backend.servicesimpls;

import lombok.AllArgsConstructor;
import org.example.elecciones_backend.dtos.MesaDTO;
import org.example.elecciones_backend.entities.Mesa;
import org.example.elecciones_backend.entities.Recinto;
import org.example.elecciones_backend.exceptions.ResourceNotFoundException;
import org.example.elecciones_backend.mapperdtos.MesaMapper;
import org.example.elecciones_backend.repositories.MesaRepository;
import org.example.elecciones_backend.repositories.RecintoRepository;
import org.example.elecciones_backend.services.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MesaServiceImpl implements MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private RecintoRepository recintoRepository;

    @Override
    public MesaDTO createMesa(MesaDTO mesaDTO) {
        Mesa mesa = MesaMapper.mapMesaDTOToMesa(mesaDTO);
        
        if (mesaDTO.getIdRecinto() != null) {
            Recinto recinto = recintoRepository.findById(mesaDTO.getIdRecinto())
                    .orElseThrow(() -> new ResourceNotFoundException("Recinto not found with id " + mesaDTO.getIdRecinto()));
            mesa.setRecinto(recinto);
        }
        
        return MesaMapper.mapMesaToMesaDTO(mesaRepository.save(mesa));
    }

    @Override
    public MesaDTO updateMesa(Long id, MesaDTO mesaDTO) {
        Mesa mesa = mesaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Mesa not found with id " + id)
        );

        mesa.setNumero(mesaDTO.getNumero());
        
        if (mesaDTO.getIdRecinto() != null) {
            Recinto recinto = recintoRepository.findById(mesaDTO.getIdRecinto())
                    .orElseThrow(() -> new ResourceNotFoundException("Recinto not found with id " + mesaDTO.getIdRecinto()));
            mesa.setRecinto(recinto);
        } else {
            mesa.setRecinto(null);
        }

        return MesaMapper.mapMesaToMesaDTO(mesaRepository.save(mesa));
    }

    @Override
    public String deleteMesa(Long id) {
        Mesa mesa = mesaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Mesa not found with id " + id)
        );
        mesaRepository.delete(mesa);
        return "Mesa has been deleted";
    }

    @Override
    public MesaDTO getMesa(Long id) {
        Mesa mesa = mesaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Mesa not found with id " + id)
        );
        return MesaMapper.mapMesaToMesaDTO(mesa);
    }

    @Override
    public List<MesaDTO> getMesas() {
        List<Mesa> mesas = mesaRepository.findAllWithRelations();
        return mesas.stream().map(MesaMapper::mapMesaToMesaDTO).collect(Collectors.toList());
    }

    @Override
    public List<MesaDTO> getMesasByRecinto(Long recintoId) {
        List<Mesa> mesas = mesaRepository.findByRecintoId(recintoId);
        return mesas.stream().map(MesaMapper::mapMesaToMesaDTO).collect(Collectors.toList());
    }
}
