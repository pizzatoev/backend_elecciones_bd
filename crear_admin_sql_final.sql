-- Script para crear usuario administrador directamente en la base de datos
-- Ejecutar en DataGrip

-- Verificar si ya existe el usuario admin
SELECT * FROM usuarios WHERE username = 'admin';

-- Si no existe, crearlo
INSERT INTO usuarios (username, password, rol) 
VALUES ('admin', 'admin123', 'ADMIN');

-- Verificar que se creó correctamente
SELECT * FROM usuarios WHERE username = 'admin';
