package org.example.elecciones_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "credenciales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credencial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_credencial")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private Rol rol;

    @Column(name = "qr_code", length = 255)
    private String qrCode;

    @Column(name = "pdf_path", length = 255)
    private String pdfPath;

    @Column(name = "emitido_en", updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp emitidoEn;

    public enum Rol {
        JURADO, VEEDOR, DELEGADO
    }
}
