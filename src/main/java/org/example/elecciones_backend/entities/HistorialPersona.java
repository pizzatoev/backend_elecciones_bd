package org.example.elecciones_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * Entidad HistorialPersona - Módulo Infraestructura
 * Responsabilidad: Waldir Trancoso
 * 
 * Representa el historial de cambios y eventos relacionados con una persona.
 * Mantiene una relación ManyToOne con la entidad Persona.
 */
@Entity
@Table(name = "historial_persona", indexes = {
    @Index(name = "idx_historial_persona_id", columnList = "persona_id"),
    @Index(name = "idx_historial_persona_fecha", columnList = "fecha_evento")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialPersona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;

    @Column(name = "tipo_evento", nullable = false, length = 50)
    private String tipoEvento;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_evento", nullable = false)
    private LocalDateTime fechaEvento;

    @Column(name = "usuario_responsable", length = 100)
    private String usuarioResponsable;

    @Column(name = "creado_en", updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp creadoEn;
}
