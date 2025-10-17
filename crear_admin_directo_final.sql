-- Crear usuario administrador directamente en DataGrip
-- Ejecutar este script en DataGrip

-- Verificar si ya existe
SELECT * FROM usuarios WHERE username = 'admin2';

-- Si no existe, crearlo
INSERT INTO usuarios (username, password, password_hash, rol) 
VALUES ('admin2', 'admin123', 'admin123', 'ADMIN');

-- Verificar que se creó
SELECT * FROM usuarios WHERE username = 'admin2';
