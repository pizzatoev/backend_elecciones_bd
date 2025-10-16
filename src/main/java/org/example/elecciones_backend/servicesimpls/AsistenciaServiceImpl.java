package org.example.elecciones_backend.servicesimpls;

import lombok.AllArgsConstructor;
import org.example.elecciones_backend.dtos.AsistenciaDTO;
import org.example.elecciones_backend.entities.Asistencia;
import org.example.elecciones_backend.entities.Jurado;
import org.example.elecciones_backend.entities.Mesa;
import org.example.elecciones_backend.exceptions.ResourceNotFoundException;
import org.example.elecciones_backend.mapperdtos.AsistenciaMapper;
import org.example.elecciones_backend.repositories.AsistenciaRepository;
import org.example.elecciones_backend.repositories.JuradoRepository;
import org.example.elecciones_backend.repositories.MesaRepository;
import org.example.elecciones_backend.services.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AsistenciaServiceImpl implements AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private JuradoRepository juradoRepository;

    @Autowired
    private MesaRepository mesaRepository;

    @Override
    public AsistenciaDTO createAsistencia(AsistenciaDTO asistenciaDTO) {
        Asistencia asistencia = AsistenciaMapper.mapAsistenciaDTOToAsistencia(asistenciaDTO);
        
        if (asistenciaDTO.getIdJurado() != null) {
            Jurado jurado = juradoRepository.findById(asistenciaDTO.getIdJurado())
                    .orElseThrow(() -> new ResourceNotFoundException("Jurado not found with id " + asistenciaDTO.getIdJurado()));
            asistencia.setJurado(jurado);
        }
        
        if (asistenciaDTO.getIdMesa() != null) {
            Mesa mesa = mesaRepository.findById(asistenciaDTO.getIdMesa())
                    .orElseThrow(() -> new ResourceNotFoundException("Mesa not found with id " + asistenciaDTO.getIdMesa()));
            asistencia.setMesa(mesa);
        }
        
        return AsistenciaMapper.mapAsistenciaToAsistenciaDTO(asistenciaRepository.save(asistencia));
    }

    @Override
    public AsistenciaDTO updateAsistencia(Long id, AsistenciaDTO asistenciaDTO) {
        Asistencia asistencia = asistenciaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Asistencia not found with id " + id)
        );

        asistencia.setEstado(asistenciaDTO.getEstado());
        
        if (asistenciaDTO.getIdJurado() != null) {
            Jurado jurado = juradoRepository.findById(asistenciaDTO.getIdJurado())
                    .orElseThrow(() -> new ResourceNotFoundException("Jurado not found with id " + asistenciaDTO.getIdJurado()));
            asistencia.setJurado(jurado);
        } else {
            asistencia.setJurado(null);
        }
        
        if (asistenciaDTO.getIdMesa() != null) {
            Mesa mesa = mesaRepository.findById(asistenciaDTO.getIdMesa())
                    .orElseThrow(() -> new ResourceNotFoundException("Mesa not found with id " + asistenciaDTO.getIdMesa()));
            asistencia.setMesa(mesa);
        } else {
            asistencia.setMesa(null);
        }

        return AsistenciaMapper.mapAsistenciaToAsistenciaDTO(asistenciaRepository.save(asistencia));
    }

    @Override
    public String deleteAsistencia(Long id) {
        Asistencia asistencia = asistenciaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Asistencia not found with id " + id)
        );
        asistenciaRepository.delete(asistencia);
        return "Asistencia has been deleted";
    }

    @Override
    public AsistenciaDTO getAsistencia(Long id) {
        Asistencia asistencia = asistenciaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Asistencia not found with id " + id)
        );
        return AsistenciaMapper.mapAsistenciaToAsistenciaDTO(asistencia);
    }

    @Override
    public List<AsistenciaDTO> getAsistencias() {
        List<Asistencia> asistencias = asistenciaRepository.findAllWithRelations();
        return asistencias.stream().map(AsistenciaMapper::mapAsistenciaToAsistenciaDTO).collect(Collectors.toList());
    }

    @Override
    public List<AsistenciaDTO> getAsistenciasByJurado(Long juradoId) {
        List<Asistencia> asistencias = asistenciaRepository.findByJuradoId(juradoId);
        return asistencias.stream().map(AsistenciaMapper::mapAsistenciaToAsistenciaDTO).collect(Collectors.toList());
    }

    @Override
    public List<AsistenciaDTO> getAsistenciasByMesa(Long mesaId) {
        List<Asistencia> asistencias = asistenciaRepository.findByMesaIdWithRelations(mesaId);
        return asistencias.stream().map(AsistenciaMapper::mapAsistenciaToAsistenciaDTO).collect(Collectors.toList());
    }

    @Override
    public List<AsistenciaDTO> getAsistenciasByEstado(Asistencia.Estado estado) {
        List<Asistencia> asistencias = asistenciaRepository.findByEstado(estado);
        return asistencias.stream().map(AsistenciaMapper::mapAsistenciaToAsistenciaDTO).collect(Collectors.toList());
    }

    @Override
    public AsistenciaDTO getAsistenciaByJuradoAndMesa(Long juradoId, Long mesaId) {
        Asistencia asistencia = asistenciaRepository.findByJuradoIdAndMesaId(juradoId, mesaId).orElseThrow(
                () -> new ResourceNotFoundException("Asistencia not found with jurado id " + juradoId + " and mesa id " + mesaId)
        );
        return AsistenciaMapper.mapAsistenciaToAsistenciaDTO(asistencia);
    }

    @Override
    public String marcarPresente(Long id) {
        Asistencia asistencia = asistenciaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Asistencia not found with id " + id)
        );
        asistencia.setEstado(Asistencia.Estado.PRESENTE);
        asistenciaRepository.save(asistencia);
        return "Asistencia marcada como presente";
    }

    @Override
    public String marcarAusente(Long id) {
        Asistencia asistencia = asistenciaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Asistencia not found with id " + id)
        );
        asistencia.setEstado(Asistencia.Estado.AUSENTE);
        asistenciaRepository.save(asistencia);
        return "Asistencia marcada como ausente";
    }

    @Override
    public String crearAsistenciasParaMesa(Long mesaId) {
        try {
            // Buscar todos los jurados de la mesa usando una consulta directa
            List<Jurado> jurados = juradoRepository.findAll().stream()
                .filter(j -> j.getMesa() != null && j.getMesa().getId().equals(mesaId))
                .collect(java.util.stream.Collectors.toList());
            
            if (jurados.isEmpty()) {
                return "No hay jurados asignados a esta mesa";
            }
            
            int creados = 0;
            for (Jurado jurado : jurados) {
                try {
                    // Crear nueva asistencia sin verificar duplicados por ahora
                    Asistencia asistencia = new Asistencia();
                    asistencia.setJurado(jurado);
                    asistencia.setMesa(jurado.getMesa());
                    asistencia.setEstado(Asistencia.Estado.AUSENTE);
                    asistenciaRepository.save(asistencia);
                    creados++;
                } catch (Exception e) {
                    // Log del error pero continuar con el siguiente jurado
                    System.err.println("Error al crear asistencia para jurado " + jurado.getId() + ": " + e.getMessage());
                }
            }
            
            return "Se crearon " + creados + " registros de asistencia para la mesa";
        } catch (Exception e) {
            System.err.println("Error en crearAsistenciasParaMesa: " + e.getMessage());
            e.printStackTrace();
            return "Error al crear asistencias: " + e.getMessage();
        }
    }

}
