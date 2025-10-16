package org.example.elecciones_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jurados")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jurado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jurado")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mesa")
    private Mesa mesa;

    @Enumerated(EnumType.STRING)
    @Column(name = "cargo", nullable = false)
    private Cargo cargo;

    @Column(name = "verificado", nullable = false)
    private Boolean verificado = false;

    public enum Cargo {
        PRESIDENTE, SECRETARIO, VOCAL, SUPLENTE
    }
}
