-- Script para llenar la base de datos completa del sistema electoral
-- Ejecutar en DataGrip

-- Limpiar datos existentes (en orden correcto por foreign keys)
DELETE FROM jurados;
DELETE FROM veedores;
DELETE FROM delegados;
DELETE FROM mesas;
DELETE FROM recintos;
DELETE FROM asientos;
DELETE FROM partidos;
DELETE FROM historial_persona;
DELETE FROM personas;
DELETE FROM municipios;
DELETE FROM provincias;
DELETE FROM departamentos;

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

-- Insertar partidos (sin columna color)
INSERT INTO partidos (nombre, sigla, logo) VALUES 
('Movimiento al Socialismo', 'MAS', 'logo_mas.png'),
('Comunidad Ciudadana', 'CC', 'logo_cc.png'),
('Creemos', 'CREEMOS', 'logo_creemos.png'),
('Frente para la Victoria', 'FPV', 'logo_fpv.png'),
('Partido Demócrata Cristiano', 'PDC', 'logo_pdc.png');

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

-- Insertar veedores (sin columna id_mesa ni verificado)
INSERT INTO veedores (id_persona) VALUES 
(1),
(2),
(3),
(4),
(5),
(6);

-- Insertar delegados (sin columna verificado)
INSERT INTO delegados (id_persona, id_partido, id_mesa) VALUES 
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 1, 4),
(5, 2, 5),
(6, 3, 6);

-- Verificar datos insertados
SELECT 'Departamentos:' as info, COUNT(*) as cantidad FROM departamentos;
SELECT 'Provincias:' as info, COUNT(*) as cantidad FROM provincias;
SELECT 'Municipios:' as info, COUNT(*) as cantidad FROM municipios;
SELECT 'Asientos:' as info, COUNT(*) as cantidad FROM asientos;
SELECT 'Recintos:' as info, COUNT(*) as cantidad FROM recintos;
SELECT 'Mesas:' as info, COUNT(*) as cantidad FROM mesas;
SELECT 'Partidos:' as info, COUNT(*) as cantidad FROM partidos;
SELECT 'Personas:' as info, COUNT(*) as cantidad FROM personas;
SELECT 'Jurados:' as info, COUNT(*) as cantidad FROM jurados;
SELECT 'Veedores:' as info, COUNT(*) as cantidad FROM veedores;
SELECT 'Delegados:' as info, COUNT(*) as cantidad FROM delegados;

-- Mostrar resumen de roles
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
