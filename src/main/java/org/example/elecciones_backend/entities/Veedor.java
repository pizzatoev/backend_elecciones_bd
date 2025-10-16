package org.example.elecciones_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "veedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Veedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_veedor")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_institucion")
    private Institucion institucion;

    @Column(name = "carta_respaldo", length = 255)
    private String cartaRespaldo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado = Estado.PENDIENTE;

    @Column(name = "motivo_rechazo", length = 255)
    private String motivoRechazo;

    public enum Estado {
        PENDIENTE, APROBADO, RECHAZADO
    }
}
