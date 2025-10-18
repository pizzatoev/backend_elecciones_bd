package org.example.elecciones_backend.servicesimpls;

import lombok.AllArgsConstructor;
import org.example.elecciones_backend.entities.Delegado;
import org.example.elecciones_backend.entities.Jurado;
import org.example.elecciones_backend.entities.Persona;
import org.example.elecciones_backend.entities.Veedor;
import org.example.elecciones_backend.exceptions.DuplicateRoleException;
import org.example.elecciones_backend.repositories.DelegadoRepository;
import org.example.elecciones_backend.repositories.JuradoRepository;
import org.example.elecciones_backend.repositories.VeedorRepository;
import org.example.elecciones_backend.services.RoleValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleValidationServiceImpl implements RoleValidationService {

    @Autowired
    private JuradoRepository juradoRepository;

    @Autowired
    private VeedorRepository veedorRepository;

    @Autowired
    private DelegadoRepository delegadoRepository;

    @Override
    public void validateUniqueRole(Persona persona, String tipoRol) {
        if (hasExistingRole(persona)) {
            String rolExistente = getExistingRole(persona);
            throw new DuplicateRoleException(
                "Esta persona ya tiene un rol asignado (" + rolExistente + ") y no puede ser registrada nuevamente como " + tipoRol + "."
            );
        }
    }

    @Override
    public boolean hasExistingRole(Persona persona) {
        // Verificar si es jurado
        List<Jurado> jurados = juradoRepository.findByPersonaId(persona.getId());
        if (!jurados.isEmpty()) {
            return true;
        }

        // Verificar si es veedor
        List<Veedor> veedores = veedorRepository.findByPersonaId(persona.getId());
        if (!veedores.isEmpty()) {
            return true;
        }

        // Verificar si es delegado
        List<Delegado> delegados = delegadoRepository.findByPersonaId(persona.getId());
        if (!delegados.isEmpty()) {
            return true;
        }

        return false;
    }

    @Override
    public String getExistingRole(Persona persona) {
        // Verificar si es jurado
        List<Jurado> jurados = juradoRepository.findByPersonaId(persona.getId());
        if (!jurados.isEmpty()) {
            return "Jurado";
        }

        // Verificar si es veedor
        List<Veedor> veedores = veedorRepository.findByPersonaId(persona.getId());
        if (!veedores.isEmpty()) {
            return "Veedor";
        }

        // Verificar si es delegado
        List<Delegado> delegados = delegadoRepository.findByPersonaId(persona.getId());
        if (!delegados.isEmpty()) {
            return "Delegado";
        }

        return null;
    }
}
