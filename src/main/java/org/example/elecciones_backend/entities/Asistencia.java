package org.example.elecciones_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "asistencia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asistencia")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jurado")
    private Jurado jurado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mesa")
    private Mesa mesa;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado = Estado.AUSENTE;

    @Column(name = "registrado_en", updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp registradoEn;

    public enum Estado {
        PRESENTE, AUSENTE, PENDIENTE
    }
}
