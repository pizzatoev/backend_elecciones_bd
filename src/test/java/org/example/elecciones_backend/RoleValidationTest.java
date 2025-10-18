package org.example.elecciones_backend;

import org.example.elecciones_backend.entities.Persona;
import org.example.elecciones_backend.entities.Jurado;
import org.example.elecciones_backend.entities.Veedor;
import org.example.elecciones_backend.entities.Delegado;
import org.example.elecciones_backend.exceptions.DuplicateRoleException;
import org.example.elecciones_backend.services.RoleValidationService;
import org.example.elecciones_backend.servicesimpls.RoleValidationServiceImpl;
import org.example.elecciones_backend.repositories.JuradoRepository;
import org.example.elecciones_backend.repositories.VeedorRepository;
import org.example.elecciones_backend.repositories.DelegadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleValidationTest {

    @Mock
    private JuradoRepository juradoRepository;

    @Mock
    private VeedorRepository veedorRepository;

    @Mock
    private DelegadoRepository delegadoRepository;

    @InjectMocks
    private RoleValidationServiceImpl roleValidationService;

    private Persona persona;

    @BeforeEach
    void setUp() {
        persona = new Persona();
        persona.setId(1L);
        persona.setCi("12345678");
        persona.setNombre("Juan");
        persona.setApellido("Pérez");
    }

    @Test
    void testValidateUniqueRole_NoExistingRole_ShouldNotThrowException() {
        // Arrange
        when(juradoRepository.findByPersonaId(1L)).thenReturn(Collections.emptyList());
        when(veedorRepository.findByPersonaId(1L)).thenReturn(Collections.emptyList());
        when(delegadoRepository.findByPersonaId(1L)).thenReturn(Collections.emptyList());

        // Act & Assert
        assertDoesNotThrow(() -> {
            roleValidationService.validateUniqueRole(persona, "Jurado");
        });
    }

    @Test
    void testValidateUniqueRole_ExistingJuradoRole_ShouldThrowException() {
        // Arrange
        Jurado existingJurado = new Jurado();
        existingJurado.setId(1L);
        existingJurado.setPersona(persona);
        
        when(juradoRepository.findByPersonaId(1L)).thenReturn(Arrays.asList(existingJurado));
        when(veedorRepository.findByPersonaId(1L)).thenReturn(Collections.emptyList());
        when(delegadoRepository.findByPersonaId(1L)).thenReturn(Collections.emptyList());

        // Act & Assert
        DuplicateRoleException exception = assertThrows(DuplicateRoleException.class, () -> {
            roleValidationService.validateUniqueRole(persona, "Veedor");
        });

        assertTrue(exception.getMessage().contains("Esta persona ya tiene un rol asignado"));
        assertTrue(exception.getMessage().contains("Jurado"));
    }

    @Test
    void testValidateUniqueRole_ExistingVeedorRole_ShouldThrowException() {
        // Arrange
        Veedor existingVeedor = new Veedor();
        existingVeedor.setId(1L);
        existingVeedor.setPersona(persona);
        
        when(juradoRepository.findByPersonaId(1L)).thenReturn(Collections.emptyList());
        when(veedorRepository.findByPersonaId(1L)).thenReturn(Arrays.asList(existingVeedor));
        when(delegadoRepository.findByPersonaId(1L)).thenReturn(Collections.emptyList());

        // Act & Assert
        DuplicateRoleException exception = assertThrows(DuplicateRoleException.class, () -> {
            roleValidationService.validateUniqueRole(persona, "Delegado");
        });

        assertTrue(exception.getMessage().contains("Esta persona ya tiene un rol asignado"));
        assertTrue(exception.getMessage().contains("Veedor"));
    }

    @Test
    void testValidateUniqueRole_ExistingDelegadoRole_ShouldThrowException() {
        // Arrange
        Delegado existingDelegado = new Delegado();
        existingDelegado.setId(1L);
        existingDelegado.setPersona(persona);
        
        when(juradoRepository.findByPersonaId(1L)).thenReturn(Collections.emptyList());
        when(veedorRepository.findByPersonaId(1L)).thenReturn(Collections.emptyList());
        when(delegadoRepository.findByPersonaId(1L)).thenReturn(Arrays.asList(existingDelegado));

        // Act & Assert
        DuplicateRoleException exception = assertThrows(DuplicateRoleException.class, () -> {
            roleValidationService.validateUniqueRole(persona, "Jurado");
        });

        assertTrue(exception.getMessage().contains("Esta persona ya tiene un rol asignado"));
        assertTrue(exception.getMessage().contains("Delegado"));
    }

    @Test
    void testHasExistingRole_NoRoles_ShouldReturnFalse() {
        // Arrange
        when(juradoRepository.findByPersonaId(1L)).thenReturn(Collections.emptyList());
        when(veedorRepository.findByPersonaId(1L)).thenReturn(Collections.emptyList());
        when(delegadoRepository.findByPersonaId(1L)).thenReturn(Collections.emptyList());

        // Act
        boolean hasRole = roleValidationService.hasExistingRole(persona);

        // Assert
        assertFalse(hasRole);
    }

    @Test
    void testHasExistingRole_WithJuradoRole_ShouldReturnTrue() {
        // Arrange
        Jurado existingJurado = new Jurado();
        existingJurado.setId(1L);
        existingJurado.setPersona(persona);
        
        when(juradoRepository.findByPersonaId(1L)).thenReturn(Arrays.asList(existingJurado));
        when(veedorRepository.findByPersonaId(1L)).thenReturn(Collections.emptyList());
        when(delegadoRepository.findByPersonaId(1L)).thenReturn(Collections.emptyList());

        // Act
        boolean hasRole = roleValidationService.hasExistingRole(persona);

        // Assert
        assertTrue(hasRole);
    }

    @Test
    void testGetExistingRole_NoRoles_ShouldReturnNull() {
        // Arrange
        when(juradoRepository.findByPersonaId(1L)).thenReturn(Collections.emptyList());
        when(veedorRepository.findByPersonaId(1L)).thenReturn(Collections.emptyList());
        when(delegadoRepository.findByPersonaId(1L)).thenReturn(Collections.emptyList());

        // Act
        String existingRole = roleValidationService.getExistingRole(persona);

        // Assert
        assertNull(existingRole);
    }

    @Test
    void testGetExistingRole_WithJuradoRole_ShouldReturnJurado() {
        // Arrange
        Jurado existingJurado = new Jurado();
        existingJurado.setId(1L);
        existingJurado.setPersona(persona);
        
        when(juradoRepository.findByPersonaId(1L)).thenReturn(Arrays.asList(existingJurado));
        when(veedorRepository.findByPersonaId(1L)).thenReturn(Collections.emptyList());
        when(delegadoRepository.findByPersonaId(1L)).thenReturn(Collections.emptyList());

        // Act
        String existingRole = roleValidationService.getExistingRole(persona);

        // Assert
        assertEquals("Jurado", existingRole);
    }

    @Test
    void testGetExistingRole_WithVeedorRole_ShouldReturnVeedor() {
        // Arrange
        Veedor existingVeedor = new Veedor();
        existingVeedor.setId(1L);
        existingVeedor.setPersona(persona);
        
        when(juradoRepository.findByPersonaId(1L)).thenReturn(Collections.emptyList());
        when(veedorRepository.findByPersonaId(1L)).thenReturn(Arrays.asList(existingVeedor));
        when(delegadoRepository.findByPersonaId(1L)).thenReturn(Collections.emptyList());

        // Act
        String existingRole = roleValidationService.getExistingRole(persona);

        // Assert
        assertEquals("Veedor", existingRole);
    }

    @Test
    void testGetExistingRole_WithDelegadoRole_ShouldReturnDelegado() {
        // Arrange
        Delegado existingDelegado = new Delegado();
        existingDelegado.setId(1L);
        existingDelegado.setPersona(persona);
        
        when(juradoRepository.findByPersonaId(1L)).thenReturn(Collections.emptyList());
        when(veedorRepository.findByPersonaId(1L)).thenReturn(Collections.emptyList());
        when(delegadoRepository.findByPersonaId(1L)).thenReturn(Arrays.asList(existingDelegado));

        // Act
        String existingRole = roleValidationService.getExistingRole(persona);

        // Assert
        assertEquals("Delegado", existingRole);
    }
}
