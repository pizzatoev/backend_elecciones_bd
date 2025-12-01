# Crear Usuario Administrador - Postman

## Paso 1: Crear Usuario Administrador

**Método:** POST
**URL:** http://localhost:9090/api/usuarios
**Headers:** 
- Content-Type: application/json

**Body (raw JSON):**
```json
{
  "username": "admin",
  "password": "admin123",
  "rol": "ADMIN"
}
```

## Paso 2: Configurar Autenticación

1. En Postman, ve a la pestaña "Authorization"
2. Selecciona "Basic Auth"
3. Ingresa:
   - Username: admin
   - Password: admin123

## Paso 3: Probar /historial

**Método:** GET
**URL:** http://localhost:9090/historial

Deberías recibir una respuesta 200 OK con una lista vacía (o con datos si ya existen historiales).
