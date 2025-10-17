-- Script para crear usuario administrador
-- Ejecutar en DataGrip o MySQL Workbench

-- Insertar usuario administrador
INSERT INTO usuarios (username, password, rol) 
VALUES ('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'ADMIN');

-- Verificar que se creó correctamente
SELECT * FROM usuarios WHERE username = 'admin';
