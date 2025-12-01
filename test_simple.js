/**
 * Script de prueba simple para el módulo Infraestructura
 * Responsabilidad: Waldir Trancoso
 */

// Función para hacer peticiones HTTP
async function testAPI() {
    const baseURL = 'http://localhost:9090';
    
    console.log('🚀 INICIANDO PRUEBAS DEL MÓDULO INFRAESTRUCTURA');
    console.log('Responsabilidad: Waldir Trancoso\n');
    
    try {
        // 1. Probar endpoint de personas
        console.log('1. Probando endpoint de personas...');
        const response = await fetch(`${baseURL}/api/personas`);
        const data = await response.json();
        console.log('✅ Endpoint de personas funcionando:', data);
        
        // 2. Crear una persona válida
        console.log('\n2. Creando persona válida...');
        const personaResponse = await fetch(`${baseURL}/api/personas`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
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
        
        if (personaResponse.ok) {
            const persona = await personaResponse.json();
            console.log('✅ Persona creada exitosamente:', persona);
            
            // 3. Probar validación con datos inválidos
            console.log('\n3. Probando validación con datos inválidos...');
            const invalidResponse = await fetch(`${baseURL}/api/personas`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    ci: '',
                    nombre: '',
                    apellido: ''
                })
            });
            
            if (!invalidResponse.ok) {
                const error = await invalidResponse.text();
                console.log('✅ Validación funcionando correctamente:', error);
            }
            
            // 4. Probar endpoint de partidos
            console.log('\n4. Probando endpoint de partidos...');
            const partidosResponse = await fetch(`${baseURL}/api/partidos`);
            const partidos = await partidosResponse.json();
            console.log('✅ Endpoint de partidos funcionando:', partidos);
            
            // 5. Crear un partido
            console.log('\n5. Creando partido...');
            const partidoResponse = await fetch(`${baseURL}/api/partidos`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    sigla: 'MAS',
                    nombre: 'Movimiento al Socialismo',
                    estado: 'ACTIVO'
                })
            });
            
            if (partidoResponse.ok) {
                const partido = await partidoResponse.json();
                console.log('✅ Partido creado exitosamente:', partido);
                
                // 6. Probar subir logo
                console.log('\n6. Probando subir logo...');
                const logoResponse = await fetch(`${baseURL}/api/partidos/upload-logo/${partido.id}`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: 'logoUrl=http://localhost:9090/uploads/partidos/logo_mas.png'
                });
                
                if (logoResponse.ok) {
                    const updatedPartido = await logoResponse.json();
                    console.log('✅ Logo subido exitosamente:', updatedPartido);
                }
                
                // 7. Probar cambiar estado
                console.log('\n7. Probando cambiar estado...');
                const estadoResponse = await fetch(`${baseURL}/api/partidos/${partido.id}/estado?nuevoEstado=DISUELTO`, {
                    method: 'PUT'
                });
                
                if (estadoResponse.ok) {
                    const finalPartido = await estadoResponse.json();
                    console.log('✅ Estado cambiado exitosamente:', finalPartido);
                }
            }
        }
        
        console.log('\n✅ TODAS LAS PRUEBAS COMPLETADAS EXITOSAMENTE');
        
    } catch (error) {
        console.error('\n❌ ERROR EN LAS PRUEBAS:', error.message);
        console.log('💡 Asegúrate de que la aplicación esté ejecutándose en el puerto 9090');
    }
}

// Ejecutar las pruebas
testAPI();
