package org.example.elecciones_backend.services;

import org.example.elecciones_backend.entities.Persona;

public interface RoleValidationService {
    
    /**
     * Valida que una persona no tenga roles duplicados
     * @param persona La persona a validar
     * @param tipoRol El tipo de rol que se está intentando asignar
     * @throws org.example.elecciones_backend.exceptions.DuplicateRoleException Si la persona ya tiene un rol asignado
     */
    void validateUniqueRole(Persona persona, String tipoRol);
    
    /**
     * Verifica si una persona ya tiene un rol asignado
     * @param persona La persona a verificar
     * @return true si ya tiene un rol, false en caso contrario
     */
    boolean hasExistingRole(Persona persona);
    
    /**
     * Obtiene el rol existente de una persona
     * @param persona La persona a verificar
     * @return El nombre del rol existente o null si no tiene ninguno
     */
    String getExistingRole(Persona persona);
}
