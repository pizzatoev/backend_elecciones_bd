package org.example.elecciones_backend.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.elecciones_backend.entities.Persona;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDTO {
    private Long id;
    private String ci;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String correo;
    private String telefono;
    private String ciudad;
    private String fotoCarnet;
    private Persona.EstadoPersona estado; // Campo estado_vida - Módulo Infraestructura
}
