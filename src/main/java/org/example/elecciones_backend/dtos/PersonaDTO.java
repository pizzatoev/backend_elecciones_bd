package org.example.elecciones_backend.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
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
}
