package org.example.elecciones_backend.servicesimpls;

import lombok.AllArgsConstructor;
import org.example.elecciones_backend.dtos.JuradoDTO;
import org.example.elecciones_backend.entities.Jurado;
import org.example.elecciones_backend.entities.Mesa;
import org.example.elecciones_backend.entities.Persona;
import org.example.elecciones_backend.entities.Recinto;
import org.example.elecciones_backend.entities.Asiento;
import org.example.elecciones_backend.entities.Veedor;
import org.example.elecciones_backend.entities.Delegado;
import org.example.elecciones_backend.exceptions.ResourceNotFoundException;
import org.example.elecciones_backend.mapperdtos.JuradoMapper;
import org.example.elecciones_backend.repositories.JuradoRepository;
import org.example.elecciones_backend.repositories.MesaRepository;
import org.example.elecciones_backend.repositories.PersonaRepository;
import org.example.elecciones_backend.repositories.VeedorRepository;
import org.example.elecciones_backend.repositories.DelegadoRepository;
import org.example.elecciones_backend.services.JuradoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JuradoServiceImpl implements JuradoService {

    @Autowired
    private JuradoRepository juradoRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private VeedorRepository veedorRepository;

    @Autowired
    private DelegadoRepository delegadoRepository;

    @Override
    public JuradoDTO createJurado(JuradoDTO juradoDTO) {
        Jurado jurado = JuradoMapper.mapJuradoDTOToJurado(juradoDTO);
        
        if (juradoDTO.getIdPersona() != null) {
            Persona persona = personaRepository.findById(juradoDTO.getIdPersona())
                    .orElseThrow(() -> new ResourceNotFoundException("Persona not found with id " + juradoDTO.getIdPersona()));
            jurado.setPersona(persona);
        }
        
        if (juradoDTO.getIdMesa() != null) {
            Mesa mesa = mesaRepository.findById(juradoDTO.getIdMesa())
                    .orElseThrow(() -> new ResourceNotFoundException("Mesa not found with id " + juradoDTO.getIdMesa()));
            jurado.setMesa(mesa);
        }
        
        return JuradoMapper.mapJuradoToJuradoDTO(juradoRepository.save(jurado));
    }

    @Override
    public JuradoDTO updateJurado(Long id, JuradoDTO juradoDTO) {
        Jurado jurado = juradoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Jurado not found with id " + id)
        );

        jurado.setCargo(juradoDTO.getCargo());
        jurado.setVerificado(juradoDTO.getVerificado());
        
        if (juradoDTO.getIdPersona() != null) {
            Persona persona = personaRepository.findById(juradoDTO.getIdPersona())
                    .orElseThrow(() -> new ResourceNotFoundException("Persona not found with id " + juradoDTO.getIdPersona()));
            jurado.setPersona(persona);
        } else {
            jurado.setPersona(null);
        }
        
        if (juradoDTO.getIdMesa() != null) {
            Mesa mesa = mesaRepository.findById(juradoDTO.getIdMesa())
                    .orElseThrow(() -> new ResourceNotFoundException("Mesa not found with id " + juradoDTO.getIdMesa()));
            jurado.setMesa(mesa);
        } else {
            jurado.setMesa(null);
        }

        return JuradoMapper.mapJuradoToJuradoDTO(juradoRepository.save(jurado));
    }

    @Override
    public String deleteJurado(Long id) {
        Jurado jurado = juradoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Jurado not found with id " + id)
        );
        juradoRepository.delete(jurado);
        return "Jurado has been deleted";
    }

    @Override
    public JuradoDTO getJurado(Long id) {
        Jurado jurado = juradoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Jurado not found with id " + id)
        );
        return JuradoMapper.mapJuradoToJuradoDTO(jurado);
    }

    @Override
    public List<JuradoDTO> getJurados() {
        List<Jurado> jurados = juradoRepository.findAllWithRelations();
        return jurados.stream().map(JuradoMapper::mapJuradoToJuradoDTO).collect(Collectors.toList());
    }

    @Override
    public List<JuradoDTO> getJuradosByPersona(Long personaId) {
        List<Jurado> jurados = juradoRepository.findByPersonaId(personaId);
        return jurados.stream().map(JuradoMapper::mapJuradoToJuradoDTO).collect(Collectors.toList());
    }

    @Override
    public List<JuradoDTO> getJuradosByMesa(Long mesaId) {
        List<Jurado> jurados = juradoRepository.findByMesaId(mesaId);
        return jurados.stream().map(JuradoMapper::mapJuradoToJuradoDTO).collect(Collectors.toList());
    }

    @Override
    public List<JuradoDTO> getJuradosByCargo(Jurado.Cargo cargo) {
        List<Jurado> jurados = juradoRepository.findByCargo(cargo);
        return jurados.stream().map(JuradoMapper::mapJuradoToJuradoDTO).collect(Collectors.toList());
    }

    @Override
    public List<JuradoDTO> getJuradosByVerificado(Boolean verificado) {
        List<Jurado> jurados = juradoRepository.findByVerificado(verificado);
        return jurados.stream().map(JuradoMapper::mapJuradoToJuradoDTO).collect(Collectors.toList());
    }

    @Override
    public JuradoDTO getJuradoByPersonaAndMesa(Long personaId, Long mesaId) {
        Jurado jurado = juradoRepository.findByPersonaIdAndMesaId(personaId, mesaId).orElseThrow(
                () -> new ResourceNotFoundException("Jurado not found with persona id " + personaId + " and mesa id " + mesaId)
        );
        return JuradoMapper.mapJuradoToJuradoDTO(jurado);
    }

    @Override
    public JuradoDTO getJuradoByCi(String ci) {
        Jurado jurado = juradoRepository.findByPersonaCiWithRelations(ci).orElseThrow(
                () -> new ResourceNotFoundException("Jurado not found with CI " + ci)
        );
        return JuradoMapper.mapJuradoToJuradoDTO(jurado);
    }

    @Override
    @Transactional
    public List<JuradoDTO> sortearJurados() {
        // Obtener todas las mesas
        List<Mesa> mesas = mesaRepository.findAll();
        List<JuradoDTO> juradosSorteados = new ArrayList<>();
        
        // Obtener IDs de personas que ya son veedores o delegados
        List<Long> personasExcluidas = new ArrayList<>();
        
        // Agregar IDs de veedores
        List<Veedor> veedores = veedorRepository.findAll();
        personasExcluidas.addAll(veedores.stream()
                .map(veedor -> veedor.getPersona().getId())
                .collect(Collectors.toList()));
        
        // Agregar IDs de delegados
        List<Delegado> delegados = delegadoRepository.findAll();
        personasExcluidas.addAll(delegados.stream()
                .map(delegado -> delegado.getPersona().getId())
                .collect(Collectors.toList()));
        
        for (Mesa mesa : mesas) {
            // Obtener la ciudad del recinto de la mesa
            Recinto recinto = mesa.getRecinto();
            Asiento asiento = recinto.getAsiento();
            String ciudadRecinto = asiento.getMunicipio().getNombre(); // Asumiendo que municipio tiene nombre
            
            // Buscar personas de la misma ciudad que no sean veedores o delegados
            List<Persona> personasElegibles = personaRepository.findAll().stream()
                    .filter(persona -> persona.getCiudad() != null && 
                            persona.getCiudad().equalsIgnoreCase(ciudadRecinto) &&
                            !personasExcluidas.contains(persona.getId()))
                    .collect(Collectors.toList());
            
            // Si hay al menos 3 personas elegibles, hacer el sorteo
            if (personasElegibles.size() >= 3) {
                // Mezclar la lista para obtener selección aleatoria
                Collections.shuffle(personasElegibles);
                
                // Tomar las primeras 3 personas
                List<Persona> personasSeleccionadas = personasElegibles.subList(0, 3);
                
                // Crear jurados con los cargos correspondientes
                Jurado.Cargo[] cargos = {Jurado.Cargo.PRESIDENTE, Jurado.Cargo.SECRETARIO, Jurado.Cargo.VOCAL};
                
                for (int i = 0; i < 3; i++) {
                    Jurado jurado = new Jurado();
                    jurado.setPersona(personasSeleccionadas.get(i));
                    jurado.setMesa(mesa);
                    jurado.setCargo(cargos[i]);
                    jurado.setVerificado(false);
                    
                    Jurado juradoGuardado = juradoRepository.save(jurado);
                    juradosSorteados.add(JuradoMapper.mapJuradoToJuradoDTO(juradoGuardado));
                }
            }
        }
        
        return juradosSorteados;
    }

    @Override
    @Transactional
    public void eliminarSorteo() {
        juradoRepository.deleteAll();
    }
}
