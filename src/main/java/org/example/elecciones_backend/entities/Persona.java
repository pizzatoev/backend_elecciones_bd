package org.example.elecciones_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "personas", indexes = {
    @Index(name = "idx_personas_ci", columnList = "ci")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long id;

    @Column(name = "ci", nullable = false, unique = true, length = 20)
    private String ci;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "correo", length = 100)
    private String correo;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "ciudad", length = 100)
    private String ciudad;

    @Column(name = "foto_carnet", length = 255)
    private String fotoCarnet;

    @Column(name = "creado_en", updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp creadoEn;
}
