-- Script simple para llenar la base de datos
-- Ejecutar en DataGrip paso a paso

-- 1. Insertar departamentos
INSERT INTO departamentos (nombre) VALUES ('La Paz');

-- 2. Insertar provincias
INSERT INTO provincias (nombre, id_departamento) VALUES ('La Paz', 1);

-- 3. Insertar municipios
INSERT INTO municipios (nombre, id_provincia) VALUES ('La Paz', 1);

-- 4. Insertar asientos
INSERT INTO asientos (nombre, id_municipio) VALUES ('Asiento Central La Paz', 1);

-- 5. Insertar recintos
INSERT INTO recintos (nombre, direccion, id_asiento) VALUES ('Colegio San Calixto', 'Av. 6 de Agosto 123', 1);

-- 6. Insertar mesas
INSERT INTO mesas (numero, id_recinto) VALUES (1, 1);

-- 7. Insertar partidos (sin columna logo)
INSERT INTO partidos (nombre, sigla) VALUES 
('Movimiento al Socialismo', 'MAS'),
('Comunidad Ciudadana', 'CC'),
('Creemos', 'CREEMOS');

-- 8. Insertar personas
INSERT INTO personas (ci, nombre, apellido, fecha_nacimiento, telefono, correo, ciudad, estado) VALUES 
('1234567', 'Juan', 'Pérez García', '1985-03-15', '70123456', 'juan.perez@email.com', 'La Paz', 'VIVO'),
('2345678', 'María', 'López Martínez', '1990-07-22', '70234567', 'maria.lopez@email.com', 'La Paz', 'VIVO'),
('3456789', 'Carlos', 'González Rodríguez', '1988-11-10', '70345678', 'carlos.gonzalez@email.com', 'La Paz', 'VIVO');

-- 9. Insertar jurados
INSERT INTO jurados (cargo, verificado, id_persona, id_mesa) VALUES 
('PRESIDENTE', true, 1, 1),
('SECRETARIO', true, 2, 1),
('VOCAL', true, 3, 1);

-- 10. Insertar veedores
INSERT INTO veedores (id_persona) VALUES (1);

-- 11. Insertar delegados
INSERT INTO delegados (id_persona, id_partido, id_mesa) VALUES (2, 1, 1);

-- Verificar datos
SELECT 'Personas:' as info, COUNT(*) as cantidad FROM personas;
SELECT 'Jurados:' as info, COUNT(*) as cantidad FROM jurados;
SELECT 'Veedores:' as info, COUNT(*) as cantidad FROM veedores;
SELECT 'Delegados:' as info, COUNT(*) as cantidad FROM delegados;
