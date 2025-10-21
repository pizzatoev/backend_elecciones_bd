-- =====================================================
-- CREAR BASE DE DATOS COMPLETA DESDE CERO
-- 100% Compatible con el Backend
-- =====================================================

-- Eliminar base de datos si existe
DROP DATABASE IF EXISTS elecciones_db;

-- Crear base de datos
CREATE DATABASE elecciones_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Usar la base de datos
USE elecciones_db;

-- =====================================================
-- 1. TABLA DEPARTAMENTOS
-- =====================================================
CREATE TABLE departamentos (
    id_departamento BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- =====================================================
-- 2. TABLA PROVINCIAS
-- =====================================================
CREATE TABLE provincias (
    id_provincia BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    id_departamento BIGINT NOT NULL,
    FOREIGN KEY (id_departamento) REFERENCES departamentos(id_departamento)
);

-- =====================================================
-- 3. TABLA MUNICIPIOS
-- =====================================================
CREATE TABLE municipios (
    id_municipio BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    id_provincia BIGINT NOT NULL,
    FOREIGN KEY (id_provincia) REFERENCES provincias(id_provincia)
);

-- =====================================================
-- 4. TABLA ASIENTOS
-- =====================================================
CREATE TABLE asientos (
    id_asiento BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    id_municipio BIGINT NOT NULL,
    FOREIGN KEY (id_municipio) REFERENCES municipios(id_municipio)
);

-- =====================================================
-- 5. TABLA RECINTOS
-- =====================================================
CREATE TABLE recintos (
    id_recinto BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    direccion VARCHAR(200),
    id_asiento BIGINT NOT NULL,
    FOREIGN KEY (id_asiento) REFERENCES asientos(id_asiento)
);

-- =====================================================
-- 6. TABLA MESAS
-- =====================================================
CREATE TABLE mesas (
    id_mesa BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero INT NOT NULL,
    id_recinto BIGINT NOT NULL,
    FOREIGN KEY (id_recinto) REFERENCES recintos(id_recinto)
);

-- =====================================================
-- 7. TABLA PARTIDOS
-- =====================================================
CREATE TABLE partidos (
    id_partido BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    sigla VARCHAR(20) NOT NULL UNIQUE,
    estado ENUM('ACTIVO', 'INACTIVO', 'DISUELTO') NOT NULL DEFAULT 'ACTIVO',
    logo_url VARCHAR(500)
);

-- =====================================================
-- 8. TABLA INSTITUCIONES
-- =====================================================
CREATE TABLE instituciones (
    id_institucion BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    sigla VARCHAR(20)
);

-- =====================================================
-- 9. TABLA PERSONAS
-- =====================================================
CREATE TABLE personas (
    id_persona BIGINT AUTO_INCREMENT PRIMARY KEY,
    ci VARCHAR(20) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE,
    telefono VARCHAR(20),
    correo VARCHAR(100),
    ciudad VARCHAR(100),
    foto_carnet VARCHAR(255),
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado ENUM('VIVO', 'FALLECIDO') NOT NULL DEFAULT 'VIVO',
    INDEX idx_personas_ci (ci)
);

-- =====================================================
-- 10. TABLA JURADOS
-- =====================================================
CREATE TABLE jurados (
    id_jurado BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_persona BIGINT NOT NULL,
    id_mesa BIGINT NOT NULL,
    cargo ENUM('PRESIDENTE', 'SECRETARIO', 'VOCAL', 'SUPLENTE') NOT NULL,
    verificado BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (id_persona) REFERENCES personas(id_persona),
    FOREIGN KEY (id_mesa) REFERENCES mesas(id_mesa)
);

-- =====================================================
-- 11. TABLA VEEDORES
-- =====================================================
CREATE TABLE veedores (
    id_veedor BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_persona BIGINT NOT NULL,
    id_institucion BIGINT,
    carta_respaldo VARCHAR(255),
    estado ENUM('PENDIENTE', 'APROBADO', 'RECHAZADO') NOT NULL DEFAULT 'PENDIENTE',
    motivo_rechazo VARCHAR(255),
    FOREIGN KEY (id_persona) REFERENCES personas(id_persona),
    FOREIGN KEY (id_institucion) REFERENCES instituciones(id_institucion)
);

-- =====================================================
-- 12. TABLA DELEGADOS
-- =====================================================
CREATE TABLE delegados (
    id_delegado BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_persona BIGINT NOT NULL,
    id_partido BIGINT NOT NULL,
    id_mesa BIGINT NOT NULL,
    habilitado BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (id_persona) REFERENCES personas(id_persona),
    FOREIGN KEY (id_partido) REFERENCES partidos(id_partido),
    FOREIGN KEY (id_mesa) REFERENCES mesas(id_mesa)
);

-- =====================================================
-- 13. TABLA USUARIOS
-- =====================================================
CREATE TABLE usuarios (
    id_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol ENUM('ADMIN', 'VOLUNTARIO') NOT NULL,
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =====================================================
-- 14. TABLA CREDENCIALES
-- =====================================================
CREATE TABLE credenciales (
    id_credencial BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_persona BIGINT NOT NULL,
    rol ENUM('JURADO', 'VEEDOR', 'DELEGADO') NOT NULL,
    qr_code VARCHAR(255),
    pdf_path VARCHAR(255),
    emitido_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_persona) REFERENCES personas(id_persona)
);

-- =====================================================
-- 15. TABLA ASISTENCIA
-- =====================================================
CREATE TABLE asistencia (
    id_asistencia BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_jurado BIGINT NOT NULL,
    id_mesa BIGINT NOT NULL,
    estado ENUM('PRESENTE', 'AUSENTE', 'PENDIENTE') NOT NULL DEFAULT 'AUSENTE',
    registrado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_jurado) REFERENCES jurados(id_jurado) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_mesa) REFERENCES mesas(id_mesa)
);

-- =====================================================
-- 16. TABLA HISTORIAL PERSONA
-- =====================================================
CREATE TABLE historial_persona (
    id_historial BIGINT AUTO_INCREMENT PRIMARY KEY,
    persona_id BIGINT NOT NULL,
    tipo_evento VARCHAR(50) NOT NULL,
    descripcion TEXT,
    fecha_evento DATETIME NOT NULL,
    usuario_responsable VARCHAR(100),
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (persona_id) REFERENCES personas(id_persona),
    INDEX idx_historial_persona_id (persona_id),
    INDEX idx_historial_persona_fecha (fecha_evento)
);

-- =====================================================
-- INSERTAR DATOS DE PRUEBA
-- =====================================================

-- Insertar departamentos
INSERT INTO departamentos (nombre) VALUES 
('La Paz'),
('Cochabamba'),
('Santa Cruz'),
('Oruro'),
('Potosí'),
('Tarija'),
('Chuquisaca'),
('Beni'),
('Pando');

-- Insertar provincias
INSERT INTO provincias (nombre, id_departamento) VALUES 
('La Paz', 1),
('Cercado', 2),
('Andrés Ibáñez', 3),
('Cercado', 4),
('Tomás Frías', 5),
('Cercado', 6),
('Oropeza', 7),
('Cercado', 8),
('Nicolás Suárez', 9);

-- Insertar municipios
INSERT INTO municipios (nombre, id_provincia) VALUES 
('La Paz', 1),
('Cochabamba', 2),
('Santa Cruz de la Sierra', 3),
('Oruro', 4),
('Potosí', 5),
('Tarija', 6),
('Sucre', 7),
('Trinidad', 8),
('Cobija', 9);

-- Insertar asientos
INSERT INTO asientos (nombre, id_municipio) VALUES 
('Asiento Central La Paz', 1),
('Asiento Norte La Paz', 1),
('Asiento Sur La Paz', 1),
('Asiento Central Cochabamba', 2),
('Asiento Norte Cochabamba', 2),
('Asiento Central Santa Cruz', 3),
('Asiento Norte Santa Cruz', 3),
('Asiento Central Oruro', 4),
('Asiento Central Potosí', 5),
('Asiento Central Tarija', 6);

-- Insertar recintos
INSERT INTO recintos (nombre, direccion, id_asiento) VALUES 
('Colegio San Calixto', 'Av. 6 de Agosto 123', 1),
('Unidad Educativa La Paz', 'Calle Comercio 456', 1),
('Escuela Central', 'Plaza Murillo 789', 1),
('Colegio Nacional', 'Av. Mariscal Santa Cruz 321', 2),
('Instituto Técnico', 'Calle Potosí 654', 2),
('Colegio Bolivar', 'Av. 16 de Julio 987', 3),
('Unidad Educativa Cochabamba', 'Av. Heroínas 147', 4),
('Colegio Nacional Cochabamba', 'Calle España 258', 4),
('Escuela Central Santa Cruz', 'Av. Cañoto 369', 6),
('Colegio Nacional Santa Cruz', 'Calle Warnes 741', 6);

-- Insertar mesas
INSERT INTO mesas (numero, id_recinto) VALUES 
(1, 1), (2, 1), (3, 1), (4, 1), (5, 1),
(1, 2), (2, 2), (3, 2), (4, 2),
(1, 3), (2, 3), (3, 3),
(1, 4), (2, 4), (3, 4), (4, 4), (5, 4),
(1, 5), (2, 5), (3, 5),
(1, 6), (2, 6), (3, 6), (4, 6),
(1, 7), (2, 7), (3, 7), (4, 7), (5, 7),
(1, 8), (2, 8), (3, 8), (4, 8),
(1, 9), (2, 9), (3, 9), (4, 9), (5, 9),
(1, 10), (2, 10), (3, 10), (4, 10);

-- Insertar partidos
INSERT INTO partidos (nombre, sigla, estado, logo_url) VALUES 
('Movimiento al Socialismo', 'MAS', 'ACTIVO', 'https://example.com/logo_mas.png'),
('Comunidad Ciudadana', 'CC', 'ACTIVO', 'https://example.com/logo_cc.png'),
('Creemos', 'CREEMOS', 'ACTIVO', 'https://example.com/logo_creemos.png'),
('Frente para la Victoria', 'FPV', 'ACTIVO', 'https://example.com/logo_fpv.png'),
('Partido Demócrata Cristiano', 'PDC', 'ACTIVO', 'https://example.com/logo_pdc.png');

-- Insertar instituciones
INSERT INTO instituciones (nombre, sigla) VALUES 
('Organización de Estados Americanos', 'OEA'),
('Unión Europea', 'UE'),
('Naciones Unidas', 'ONU'),
('Centro Carter', 'CC'),
('Fundación para el Desarrollo', 'FUNDES');

-- Insertar personas
INSERT INTO personas (ci, nombre, apellido, fecha_nacimiento, telefono, correo, ciudad, estado) VALUES 
('1234567', 'Juan', 'Pérez García', '1985-03-15', '70123456', 'juan.perez@email.com', 'La Paz', 'VIVO'),
('2345678', 'María', 'López Martínez', '1990-07-22', '70234567', 'maria.lopez@email.com', 'La Paz', 'VIVO'),
('3456789', 'Carlos', 'González Rodríguez', '1988-11-10', '70345678', 'carlos.gonzalez@email.com', 'La Paz', 'VIVO'),
('4567890', 'Ana', 'Fernández Sánchez', '1992-05-08', '70456789', 'ana.fernandez@email.com', 'La Paz', 'VIVO'),
('5678901', 'Luis', 'Hernández Jiménez', '1987-09-12', '70567890', 'luis.hernandez@email.com', 'La Paz', 'VIVO'),
('6789012', 'Carmen', 'Vargas Flores', '1983-12-03', '70678901', 'carmen.vargas@email.com', 'La Paz', 'VIVO'),
('7890123', 'Roberto', 'Mamani Quispe', '1986-08-25', '70789012', 'roberto.mamani@email.com', 'La Paz', 'VIVO'),
('8901234', 'Patricia', 'Condori Rojas', '1989-04-17', '70890123', 'patricia.condori@email.com', 'La Paz', 'VIVO'),
('9012345', 'Miguel', 'Choque Huanca', '1984-10-30', '70901234', 'miguel.choque@email.com', 'La Paz', 'VIVO'),
('0123456', 'Elena', 'Ticona Apaza', '1991-01-14', '70012345', 'elena.ticona@email.com', 'La Paz', 'VIVO');

-- Insertar jurados
INSERT INTO jurados (cargo, verificado, id_persona, id_mesa) VALUES 
('PRESIDENTE', true, 1, 1),
('SECRETARIO', true, 2, 1),
('VOCAL', true, 3, 1),
('PRESIDENTE', true, 4, 2),
('SECRETARIO', true, 5, 2),
('VOCAL', true, 6, 2),
('PRESIDENTE', false, 7, 3),
('SECRETARIO', false, 8, 3),
('VOCAL', false, 9, 3);

-- Insertar veedores
INSERT INTO veedores (id_persona, id_institucion, carta_respaldo, estado) VALUES 
(1, 1, 'carta_respaldo_1.pdf', 'APROBADO'),
(2, 2, 'carta_respaldo_2.pdf', 'APROBADO'),
(3, 3, 'carta_respaldo_3.pdf', 'PENDIENTE'),
(4, 4, 'carta_respaldo_4.pdf', 'APROBADO'),
(5, 5, 'carta_respaldo_5.pdf', 'PENDIENTE'),
(6, 1, 'carta_respaldo_6.pdf', 'APROBADO');

-- Insertar delegados
INSERT INTO delegados (id_persona, id_partido, id_mesa, habilitado) VALUES 
(1, 1, 1, true),
(2, 2, 2, true),
(3, 3, 3, true),
(4, 1, 4, false),
(5, 2, 5, true),
(6, 3, 6, true);

-- Insertar usuarios
INSERT INTO usuarios (username, password, rol) VALUES 
('admin', 'admin123', 'ADMIN'),
('voluntario1', 'vol123', 'VOLUNTARIO'),
('voluntario2', 'vol456', 'VOLUNTARIO');

-- Insertar credenciales
INSERT INTO credenciales (id_persona, rol, qr_code, pdf_path) VALUES 
(1, 'JURADO', 'QR1234567', '/credenciales/jurado_1234567.pdf'),
(2, 'VEEDOR', 'QR2345678', '/credenciales/veedor_2345678.pdf'),
(3, 'DELEGADO', 'QR3456789', '/credenciales/delegado_3456789.pdf');

-- Insertar asistencia
INSERT INTO asistencia (id_jurado, id_mesa, estado) VALUES 
(1, 1, 'PRESENTE'),
(2, 1, 'PRESENTE'),
(3, 1, 'AUSENTE'),
(4, 2, 'PRESENTE'),
(5, 2, 'PRESENTE'),
(6, 2, 'PRESENTE');

-- Insertar historial persona
INSERT INTO historial_persona (persona_id, tipo_evento, descripcion, fecha_evento, usuario_responsable) VALUES 
(1, 'REGISTRO', 'Persona registrada en el sistema', NOW(), 'admin'),
(2, 'REGISTRO', 'Persona registrada en el sistema', NOW(), 'admin'),
(3, 'REGISTRO', 'Persona registrada en el sistema', NOW(), 'admin'),
(1, 'ASIGNACION_JURADO', 'Asignado como jurado presidente', NOW(), 'admin'),
(2, 'ASIGNACION_VEEDOR', 'Asignado como veedor', NOW(), 'admin'),
(3, 'ASIGNACION_DELEGADO', 'Asignado como delegado', NOW(), 'admin');

-- =====================================================
-- VERIFICAR DATOS INSERTADOS
-- =====================================================
SELECT 'Departamentos:' as info, COUNT(*) as cantidad FROM departamentos;
SELECT 'Provincias:' as info, COUNT(*) as cantidad FROM provincias;
SELECT 'Municipios:' as info, COUNT(*) as cantidad FROM municipios;
SELECT 'Asientos:' as info, COUNT(*) as cantidad FROM asientos;
SELECT 'Recintos:' as info, COUNT(*) as cantidad FROM recintos;
SELECT 'Mesas:' as info, COUNT(*) as cantidad FROM mesas;
SELECT 'Partidos:' as info, COUNT(*) as cantidad FROM partidos;
SELECT 'Instituciones:' as info, COUNT(*) as cantidad FROM instituciones;
SELECT 'Personas:' as info, COUNT(*) as cantidad FROM personas;
SELECT 'Jurados:' as info, COUNT(*) as cantidad FROM jurados;
SELECT 'Veedores:' as info, COUNT(*) as cantidad FROM veedores;
SELECT 'Delegados:' as info, COUNT(*) as cantidad FROM delegados;
SELECT 'Usuarios:' as info, COUNT(*) as cantidad FROM usuarios;
SELECT 'Credenciales:' as info, COUNT(*) as cantidad FROM credenciales;
SELECT 'Asistencia:' as info, COUNT(*) as cantidad FROM asistencia;
SELECT 'Historial:' as info, COUNT(*) as cantidad FROM historial_persona;

-- =====================================================
-- CONSULTA FINAL - ROLES DE PERSONAS
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
WHERE p.ci IN ('1234567', '2345678', '3456789', '4567890', '5678901', '6789012', '7890123', '8901234', '9012345', '0123456');
