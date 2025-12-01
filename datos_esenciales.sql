-- =====================================================
-- DATOS ESENCIALES PARA PROBAR EL SISTEMA
-- =====================================================

-- Limpiar datos existentes
DELETE FROM asistencia;
DELETE FROM credenciales;
DELETE FROM historial_persona;
DELETE FROM jurados;
DELETE FROM veedores;
DELETE FROM delegados;
DELETE FROM mesas;
DELETE FROM recintos;
DELETE FROM asientos;
DELETE FROM partidos;
DELETE FROM personas;
DELETE FROM municipios;
DELETE FROM provincias;
DELETE FROM departamentos;
DELETE FROM instituciones;
DELETE FROM usuarios;

-- =====================================================
-- INSERTAR DATOS MÍNIMOS
-- =====================================================

-- 1. Departamento
INSERT INTO departamentos (nombre) VALUES ('La Paz');

-- 2. Provincia
INSERT INTO provincias (nombre, id_departamento) VALUES ('La Paz', 1);

-- 3. Municipio
INSERT INTO municipios (nombre, id_provincia) VALUES ('La Paz', 1);

-- 4. Asiento
INSERT INTO asientos (nombre, id_municipio) VALUES ('Asiento Central La Paz', 1);

-- 5. Recinto
INSERT INTO recintos (nombre, direccion, id_asiento) VALUES ('Colegio San Calixto', 'Av. 6 de Agosto 123', 1);

-- 6. Mesa
INSERT INTO mesas (numero, id_recinto) VALUES (1, 1);

-- 7. Partido
INSERT INTO partidos (nombre, sigla, estado) VALUES ('Movimiento al Socialismo', 'MAS', 'ACTIVO');

-- 8. Institución
INSERT INTO instituciones (nombre, sigla) VALUES ('Organización de Estados Americanos', 'OEA');

-- 9. Personas
INSERT INTO personas (ci, nombre, apellido, fecha_nacimiento, telefono, correo, ciudad, estado) VALUES 
('1234567', 'Juan', 'Pérez García', '1985-03-15', '70123456', 'juan.perez@email.com', 'La Paz', 'VIVO'),
('2345678', 'María', 'López Martínez', '1990-07-22', '70234567', 'maria.lopez@email.com', 'La Paz', 'VIVO'),
('3456789', 'Carlos', 'González Rodríguez', '1988-11-10', '70345678', 'carlos.gonzalez@email.com', 'La Paz', 'VIVO');

-- 10. Jurados
INSERT INTO jurados (cargo, verificado, id_persona, id_mesa) VALUES 
('PRESIDENTE', true, 1, 1),
('SECRETARIO', true, 2, 1),
('VOCAL', true, 3, 1);

-- 11. Veedores
INSERT INTO veedores (id_persona, id_institucion, carta_respaldo, estado) VALUES 
(1, 1, 'carta_respaldo_1.pdf', 'APROBADO'),
(2, 1, 'carta_respaldo_2.pdf', 'APROBADO'),
(3, 1, 'carta_respaldo_3.pdf', 'PENDIENTE');

-- 12. Delegados
INSERT INTO delegados (id_persona, id_partido, id_mesa, habilitado) VALUES 
(1, 1, 1, true),
(2, 1, 1, true),
(3, 1, 1, true);

-- 13. Usuarios
INSERT INTO usuarios (username, password, rol) VALUES 
('admin', 'admin123', 'ADMIN'),
('voluntario1', 'vol123', 'VOLUNTARIO');

-- 14. Credenciales
INSERT INTO credenciales (id_persona, rol, qr_code, pdf_path) VALUES 
(1, 'JURADO', 'QR1234567', '/credenciales/jurado_1234567.pdf'),
(2, 'VEEDOR', 'QR2345678', '/credenciales/veedor_2345678.pdf'),
(3, 'DELEGADO', 'QR3456789', '/credenciales/delegado_3456789.pdf');

-- 15. Asistencia
INSERT INTO asistencia (id_jurado, id_mesa, estado) VALUES 
(1, 1, 'PRESENTE'),
(2, 1, 'PRESENTE'),
(3, 1, 'AUSENTE');

-- 16. Historial
INSERT INTO historial_persona (persona_id, tipo_evento, descripcion, fecha_evento, usuario_responsable) VALUES 
(1, 'REGISTRO', 'Persona registrada en el sistema', NOW(), 'admin'),
(2, 'REGISTRO', 'Persona registrada en el sistema', NOW(), 'admin'),
(3, 'REGISTRO', 'Persona registrada en el sistema', NOW(), 'admin');

-- =====================================================
-- VERIFICAR DATOS
-- =====================================================
SELECT 'Personas:' as info, COUNT(*) as cantidad FROM personas;
SELECT 'Jurados:' as info, COUNT(*) as cantidad FROM jurados;
SELECT 'Veedores:' as info, COUNT(*) as cantidad FROM veedores;
SELECT 'Delegados:' as info, COUNT(*) as cantidad FROM delegados;

-- =====================================================
-- CONSULTA FINAL
-- =====================================================
SELECT 
    p.ci,
    p.nombre,
    p.apellido,
    CASE 
        WHEN j.id_jurado IS NOT NULL THEN 'JURADO'
        WHEN v.id_veedor IS NOT NULL THEN 'VEEDOR'
        WHEN d.id_delegado IS NOT NULL THEN 'DELEGADO'
        ELSE 'NO TIENE ROL'
    END as rol
FROM personas p
LEFT JOIN jurados j ON p.id_persona = j.id_persona
LEFT JOIN veedores v ON p.id_persona = v.id_persona
LEFT JOIN delegados d ON p.id_persona = d.id_persona
WHERE p.ci IN ('1234567', '2345678', '3456789');
