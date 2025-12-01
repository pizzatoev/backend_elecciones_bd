-- Actualizar contraseñas de usuarios
USE elecciones_db;

UPDATE usuarios SET password = 'admin123' WHERE username = 'admin';
UPDATE usuarios SET password = 'vol123' WHERE username = 'voluntario1';
UPDATE usuarios SET password = 'vol456' WHERE username = 'voluntario2';

-- Verificar que se actualizaron correctamente
SELECT id, username, password, rol FROM usuarios;
