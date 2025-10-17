-- Crear usuario administrador directamente en la base de datos
-- Ejecutar en DataGrip

INSERT INTO usuarios (username, password, rol) 
VALUES ('admin', 'admin123', 'ADMIN');

-- Verificar que se creó
SELECT * FROM usuarios WHERE username = 'admin';
