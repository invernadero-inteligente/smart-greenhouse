# 📚 Tutoriales - Invernadero Inteligente

Bienvenido a la sección de tutoriales. Aquí encontrarás guías detalladas sobre cómo usar los diferentes módulos de la aplicación.

## 📖 Guías Disponibles

### 1. **Autenticación y Gestión de Usuarios**
📄 [`AUTENTICACION_Y_USUARIOS.md`](./AUTENTICACION_Y_USUARIOS.md)

Aprende cómo:
- ✅ Registrar nuevos usuarios
- ✅ Hacer login y obtener tokens JWT
- ✅ Gestionar usuarios y roles (módulo admin)
- ✅ Probar todo en Postman con ejemplos prácticos

**Tiempo estimado de lectura:** 15-20 minutos

**Para:** Desarrolladores, administradores del sistema

---

## 🚀 Inicio Rápido

Si no tienes mucho tiempo:

1. Lee la sección "Introducción" de [Autenticación y Gestión de Usuarios](./AUTENTICACION_Y_USUARIOS.md)
2. Ve directamente a [Pruebas en Postman](./AUTENTICACION_Y_USUARIOS.md#pruebas-en-postman)
3. Sigue los pasos Test 1 al Test 6

**⏱️ Tiempo:** 5 minutos

---

## 🛠️ Herramientas Necesarias

Para seguir los tutoriales necesitas:

- **Postman** (descarga desde [postman.com](https://www.postman.com))
- **Backend ejecutándose** en `http://localhost:8080`
- **Base de datos** PostgreSQL activa

### Verificar que todo funciona

```bash
# Terminal/PowerShell
curl http://localhost:8080/api/auth/ping
# Debe responder: "Auth ping OK"
```

---

## 📋 Índice de Temas

| Tema | Ubicación | Nivel |
|------|-----------|-------|
| Registro de Usuarios | [AUTENTICACION_Y_USUARIOS.md](./AUTENTICACION_Y_USUARIOS.md#funcionalidad-de-registro) | Básico |
| Login y Tokens JWT | [AUTENTICACION_Y_USUARIOS.md](./AUTENTICACION_Y_USUARIOS.md#funcionalidad-de-login) | Básico |
| Módulo Admin | [AUTENTICACION_Y_USUARIOS.md](./AUTENTICACION_Y_USUARIOS.md#módulo-admin-gestión-de-usuarios-y-roles) | Intermedio |
| Gestión de Roles | [AUTENTICACION_Y_USUARIOS.md](./AUTENTICACION_Y_USUARIOS.md#roles-disponibles) | Intermedio |
| Pruebas en Postman | [AUTENTICACION_Y_USUARIOS.md](./AUTENTICACION_Y_USUARIOS.md#pruebas-en-postman) | Todos |
| Troubleshooting | [AUTENTICACION_Y_USUARIOS.md](./AUTENTICACION_Y_USUARIOS.md#troubleshooting) | Todos |

---

## 🎯 Por Tipo de Usuario

### Soy Desarrollador/a

- Lee: [Autenticación y Gestión de Usuarios](./AUTENTICACION_Y_USUARIOS.md) completo
- Enfócate en: Endpoints, validaciones y códigos de error
- Practica: Todos los tests en Postman

### Soy Administrador/a

- Lee: Secciones de [Gestión de Usuarios y Roles](./AUTENTICACION_Y_USUARIOS.md#módulo-admin-gestión-de-usuarios-y-roles)
- Enfócate en: Tests 6-13 de Postman
- Practica: Crear usuarios, cambiar roles, desactivar cuentas

### Soy Tester/QA

- Lee: [Pruebas en Postman](./AUTENTICACION_Y_USUARIOS.md#pruebas-en-postman) completa
- Practica: Todos los tests incluidos
- Revisa: Errores comunes y códigos de respuesta

---

## 💡 Consejos Útiles

### Guardar Tokens Automáticamente

En Postman, usa **Scripts** para guardar tokens automáticamente. Los tutoriales incluyen ejemplos de scripts para cada endpoint.

### Ambiente de Postman

Crea variables de entorno para no escribir URLs y tokens una y otra vez:

```
baseUrl = http://localhost:8080
token = (se rellena automáticamente)
adminToken = (se rellena automáticamente)
```

### Flujo Recomendado de Pruebas

```
Test 1 (Ping)
    ↓
Test 2, 3 (Registro)
    ↓
Test 4, 5 (Login Usuario)
    ↓
Test 6 (Login Admin)
    ↓
Test 7-13 (Admin Operations)
```

---

## ❓ Preguntas Frecuentes

**P: ¿Dónde consigo el token?**
R: Haz login en `/api/auth/login` y el servidor devuelve el token en la respuesta.

**P: ¿Por cuánto tiempo es válido el token?**
R: 24 horas (86400000 ms). Después necesitas hacer login de nuevo.

**P: ¿Puedo crear un usuario con rol ADMIN?**
R: Sí, desde el endpoint POST `/api/admin/users` con parámetro `"role": "ADMIN"`

**P: ¿Qué pasa si olvido mi contraseña?**
R: Actualmente no hay función de recuperación. El admin puede crear un nuevo usuario o resetear la contraseña en la BD.

**P: ¿Puedo cambiar mi propio rol?**
R: No. Los admins no pueden cambiar su propio rol por seguridad.

---

## 🔗 Enlaces Útiles

- [Backend - README Principal](../../../backend/invernadero_inteligente_backend/docs/README_BACKEND.md)
- [Variables de Entorno](../../../backend/invernadero_inteligente_backend/docs/VARIABLES_ENTORNO.md)
- [Estructura del Proyecto](../../../backend/invernadero_inteligente_backend/docs/ESTRUCTURA_CREADA.md)
- [Documentación de API REST](../../../backend/invernadero_inteligente_backend/docs/RESUMEN_EJECUTIVO.md)

---

## 📝 Contribuir

¿Encontraste un error en los tutoriales o tienes sugerencias?

- 📧 Contacta al equipo de desarrollo
- 📝 Crea un issue en el repositorio
- 💬 Sugiere mejoras en las reuniones de equipo

---

**Última actualización:** 5 de Mayo de 2026

**Estado:** ✅ Producción

---

*¿Necesitas ayuda? Consulta la sección de [Troubleshooting](./AUTENTICACION_Y_USUARIOS.md#troubleshooting) o contacta a tu administrador de sistemas.*
