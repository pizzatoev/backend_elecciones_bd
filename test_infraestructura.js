/**
 * Script de prueba para el módulo Infraestructura
 * Responsabilidad: Waldir Trancoso
 */

const BASE_URL = 'http://localhost:8080/api';

// Función auxiliar para hacer requests
async function makeRequest(url, options = {}) {
    try {
        const response = await fetch(url, {
            headers: {
                'Content-Type': 'application/json',
                ...options.headers
            },
            ...options
        });
        
        const data = await response.json();
        console.log(`✅ ${options.method || 'GET'} ${url}:`, data);
        return { success: true, data, status: response.status };
    } catch (error) {
        console.log(`❌ ${options.method || 'GET'} ${url}:`, error.message);
        return { success: false, error: error.message };
    }
}

// Pruebas de validaciones en Personas
async function testPersonaValidations() {
    console.log('\n🧪 PROBANDO VALIDACIONES DE PERSONAS...\n');
    
    // 1. Crear persona válida
    console.log('1. Creando persona válida...');
    const personaValida = await makeRequest(`${BASE_URL}/personas`, {
        method: 'POST',
        body: JSON.stringify({
            ci: '12345678',
            nombre: 'Juan',
            apellido: 'Pérez',
            correo: 'juan.perez@email.com',
            telefono: '123456789',
            ciudad: 'La Paz',
            estado: 'VIVO'
        })
    });
    
    // 2. Probar validación de campos vacíos
    console.log('\n2. Probando validación de campos vacíos...');
    await makeRequest(`${BASE_URL}/personas`, {
        method: 'POST',
        body: JSON.stringify({
            ci: '',
            nombre: '',
            apellido: ''
        })
    });
    
    // 3. Probar validación de formato de correo
    console.log('\n3. Probando validación de formato de correo...');
    await makeRequest(`${BASE_URL}/personas`, {
        method: 'POST',
        body: JSON.stringify({
            ci: '87654321',
            nombre: 'María',
            apellido: 'González',
            correo: 'correo-invalido'
        })
    });
    
    // 4. Probar duplicado de CI
    console.log('\n4. Probando duplicado de CI...');
    await makeRequest(`${BASE_URL}/personas`, {
        method: 'POST',
        body: JSON.stringify({
            ci: '12345678', // Mismo CI que el anterior
            nombre: 'Pedro',
            apellido: 'López',
            correo: 'pedro.lopez@email.com'
        })
    });
    
    return personaValida.data?.id;
}

// Pruebas de Partidos
async function testPartidos() {
    console.log('\n🧪 PROBANDO FUNCIONALIDAD DE PARTIDOS...\n');
    
    // 1. Crear partido
    console.log('1. Creando partido...');
    const partido = await makeRequest(`${BASE_URL}/partidos`, {
        method: 'POST',
        body: JSON.stringify({
            sigla: 'MAS',
            nombre: 'Movimiento al Socialismo',
            estado: 'ACTIVO'
        })
    });
    
    const partidoId = partido.data?.id;
    if (!partidoId) return;
    
    // 2. Subir logo
    console.log('\n2. Subiendo logo del partido...');
    await makeRequest(`${BASE_URL}/partidos/upload-logo/${partidoId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: 'logoUrl=http://localhost:8080/uploads/partidos/logo_mas.png'
    });
    
    // 3. Cambiar estado a DISUELTO
    console.log('\n3. Cambiando estado a DISUELTO...');
    await makeRequest(`${BASE_URL}/partidos/${partidoId}/estado?nuevoEstado=DISUELTO`, {
        method: 'PUT'
    });
    
    // 4. Listar partidos
    console.log('\n4. Listando todos los partidos...');
    await makeRequest(`${BASE_URL}/partidos`);
    
    return partidoId;
}

// Pruebas de archivos estáticos
async function testStaticFiles() {
    console.log('\n🧪 PROBANDO ARCHIVOS ESTÁTICOS...\n');
    
    console.log('Probando acceso a archivos estáticos...');
    console.log('URL de prueba: http://localhost:8080/uploads/partidos/logo_mas.png');
    console.log('(Nota: Esta URL funcionará solo si existe el archivo)');
}

// Función principal de pruebas
async function runTests() {
    console.log('🚀 INICIANDO PRUEBAS DEL MÓDULO INFRAESTRUCTURA');
    console.log('Responsabilidad: Waldir Trancoso\n');
    
    try {
        // Ejecutar pruebas
        const personaId = await testPersonaValidations();
        const partidoId = await testPartidos();
        await testStaticFiles();
        
        console.log('\n✅ TODAS LAS PRUEBAS COMPLETADAS');
        console.log(`Persona creada con ID: ${personaId}`);
        console.log(`Partido creado con ID: ${partidoId}`);
        
    } catch (error) {
        console.error('\n❌ ERROR EN LAS PRUEBAS:', error);
    }
}

// Ejecutar si se llama directamente
if (typeof window === 'undefined') {
    // Node.js
    const fetch = require('node-fetch');
    runTests();
} else {
    // Navegador
    runTests();
}
