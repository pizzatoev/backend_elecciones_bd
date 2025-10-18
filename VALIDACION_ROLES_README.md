# Validación de Roles Exclusivos

## Descripción
Este sistema implementa la validación de roles exclusivos para evitar que una misma persona tenga múltiples roles en el sistema de elecciones (Jurado, Veedor, Delegado).

## Funcionalidades Implementadas

### Backend

#### 1. Excepción Personalizada
- **Archivo**: `DuplicateRoleException.java`
- **Propósito**: Maneja errores específicos cuando se intenta asignar un rol a una persona que ya tiene otro rol.

#### 2. Servicio de Validación
- **Archivo**: `RoleValidationService.java` y `RoleValidationServiceImpl.java`
- **Métodos**:
  - `validateUniqueRole(Persona persona, String tipoRol)`: Valida que una persona no tenga roles duplicados
  - `hasExistingRole(Persona persona)`: Verifica si una persona ya tiene un rol asignado
  - `getExistingRole(Persona persona)`: Obtiene el rol existente de una persona

#### 3. Manejador Global de Excepciones
- **Archivo**: `GlobalExceptionHandler.java`
- **Propósito**: Maneja las excepciones `DuplicateRoleException` y devuelve respuestas HTTP 409 (Conflict) con mensajes descriptivos.

#### 4. Integración en Servicios
Los siguientes servicios han sido actualizados para incluir validación de roles:
- `JuradoServiceImpl.java`
- `VeedorServiceImpl.java`
- `DelegadoServiceImpl.java`

### Frontend

#### 1. Hook de Validación de Roles
- **Archivo**: `useRoleValidation.js`
- **Funcionalidades**:
  - Manejo centralizado de errores de roles duplicados
  - Detección automática de errores HTTP 409
  - Mensajes de error personalizados

#### 2. Componentes de UI
- **ErrorAlert.jsx**: Componente de alerta mejorado para mostrar errores
- **RoleInfo.jsx**: Muestra información sobre roles existentes
- **PersonaRoleValidator.jsx**: Valida roles antes de la asignación

#### 3. Servicios Actualizados
Los siguientes servicios han sido actualizados:
- `JuradoService.js` - Método `createJuradoWithValidation`
- `VeedorService.js` - Método `createVeedorWithValidation`
- `DelegadoService.js` - Método `createDelegadoWithValidation`

## Flujo de Validación

### 1. Al Registrar un Jurado
```java
// En JuradoServiceImpl.createJurado()
if (juradoDTO.getIdPersona() != null) {
    Persona persona = personaRepository.findById(juradoDTO.getIdPersona())
            .orElseThrow(() -> new ResourceNotFoundException("Persona not found"));
    
    // Validar que la persona no tenga roles duplicados
    roleValidationService.validateUniqueRole(persona, "Jurado");
    
    jurado.setPersona(persona);
}
```

### 2. Al Registrar un Veedor
```java
// En VeedorServiceImpl.createVeedor()
if (veedorDTO.getIdPersona() != null) {
    Persona persona = personaRepository.findById(veedorDTO.getIdPersona())
            .orElseThrow(() -> new ResourceNotFoundException("Persona not found"));
    
    // Validar que la persona no tenga roles duplicados
    roleValidationService.validateUniqueRole(persona, "Veedor");
    
    veedor.setPersona(persona);
}
```

### 3. Al Registrar un Delegado
```java
// En DelegadoServiceImpl.createDelegado()
if (delegadoDTO.getIdPersona() != null) {
    Persona persona = personaRepository.findById(delegadoDTO.getIdPersona())
            .orElseThrow(() -> new ResourceNotFoundException("Persona not found"));
    
    // Validar que la persona no tenga roles duplicados
    roleValidationService.validateUniqueRole(persona, "Delegado");
    
    delegado.setPersona(persona);
}
```

## Respuestas de Error

### Backend (HTTP 409 Conflict)
```json
{
    "timestamp": "2024-01-15T10:30:00",
    "status": 409,
    "error": "Conflict",
    "message": "Esta persona ya tiene un rol asignado (Jurado) y no puede ser registrada nuevamente como Veedor.",
    "path": "/api/veedores"
}
```

### Frontend
El frontend detecta automáticamente los errores HTTP 409 y muestra mensajes de error descriptivos al usuario.

## Uso en el Frontend

### 1. Usar el Hook de Validación
```javascript
import { useRoleValidation } from '../hooks/useRoleValidation';

const MyComponent = () => {
  const { error, success, handleError, handleSuccess, clearMessages } = useRoleValidation();
  
  // Usar en operaciones que pueden generar errores de roles duplicados
};
```

### 2. Usar Componentes de Alerta
```javascript
import ErrorAlert from '../components/ErrorAlert';

// En el JSX
<ErrorAlert 
  error={error} 
  onClose={clearMessages}
  variant="danger"
/>
```

## Casos de Uso

1. **Registro de Jurado**: Si una persona ya es Veedor o Delegado, no puede ser registrada como Jurado.
2. **Registro de Veedor**: Si una persona ya es Jurado o Delegado, no puede ser registrada como Veedor.
3. **Registro de Delegado**: Si una persona ya es Jurado o Veedor, no puede ser registrada como Delegado.

## Mensajes de Error

- **Español**: "Esta persona ya tiene un rol asignado (Jurado) y no puede ser registrada nuevamente como Veedor."
- **Contexto**: El mensaje incluye el rol existente y el rol que se está intentando asignar.

## Consideraciones Técnicas

1. **Transacciones**: Las validaciones se realizan antes de guardar en la base de datos.
2. **Performance**: Se consultan múltiples tablas, pero las consultas son optimizadas.
3. **Escalabilidad**: El sistema puede manejar grandes volúmenes de datos.
4. **Mantenibilidad**: El código está bien estructurado y es fácil de mantener.

## Próximas Mejoras

1. **Cache de Roles**: Implementar cache para mejorar performance.
2. **Validación en Lote**: Permitir validación de múltiples personas simultáneamente.
3. **Historial de Roles**: Mantener historial de cambios de roles.
4. **Notificaciones**: Enviar notificaciones cuando se detecten conflictos de roles.
