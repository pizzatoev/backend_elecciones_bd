package org.example.elecciones_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "partidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_partido")
    private Long id;

    @Column(name = "sigla", nullable = false, unique = true, length = 20)
    private String sigla;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado = Estado.ACTIVO;

    public enum Estado {
        ACTIVO, INACTIVO
    }
}
