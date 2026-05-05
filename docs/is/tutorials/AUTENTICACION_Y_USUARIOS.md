# Tutoriales: Autenticación y Gestión de Usuarios

## 📋 Tabla de Contenidos

1. [Introducción](#introducción)
2. [Funcionalidad de Registro](#funcionalidad-de-registro)
3. [Funcionalidad de Login](#funcionalidad-de-login)
4. [Módulo Admin: Gestión de Usuarios y Roles](#módulo-admin-gestión-de-usuarios-y-roles)
5. [Pruebas en Postman](#pruebas-en-postman)
6. [Notas Importantes](#notas-importantes)

---

## Introducción

La aplicación Invernadero Inteligente cuenta con un sistema de autenticación basado en **JWT (JSON Web Tokens)** y un módulo administrativo para gestionar usuarios y roles. Este tutorial te guiará a través de:

- **Registro**: Crear nuevas cuentas de usuario
- **Login**: Autenticarse y obtener un token JWT
- **Gestión de Usuarios (Admin)**: Listar, crear, actualizar usuarios y cambiar sus roles (solo para administradores)

---

## Funcionalidad de Registro

### ¿Qué es?

El registro permite crear una nueva cuenta de usuario en el sistema. Los nuevos usuarios se crean con el rol `OPERATOR` por defecto y con estado activo.

### Endpoint

```
POST /api/auth/register
```

### Datos Requeridos

El cuerpo de la solicitud debe incluir:

```json
{
  "fullName": "Nombre Completo del Usuario",
  "email": "usuario@ejemplo.com",
  "password": "MiContraseña123!",
  "confirmPassword": "MiContraseña123!"
}
```

### Validaciones

- **fullName**: No puede estar vacío
- **email**: Debe ser un email válido y único en el sistema
- **password**: Mínimo 8 caracteres, debe contener mayúsculas, minúsculas y números
- **confirmPassword**: Debe coincidir exactamente con `password`

### Respuesta Exitosa (200 OK)

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c3VhcmlvQGVqZW1wbG8uY29tIiwicm9sZSI6Ik9QRVJBVE9SIiwidXNlcklkIjoyLCJpYXQiOjE2MDAzMDAwMDAsImV4cCI6MTYwMDM4NjQwMH0.a1b2c3d4e5f6",
  "tokenType": "Bearer",
  "expiresIn": 86400000,
  "userId": 2,
  "email": "usuario@ejemplo.com",
  "role": "OPERATOR"
}
```

### Errores Comunes

| Código | Mensaje | Causa |
|--------|---------|-------|
| 400 | "El correo electrónico ya está registrado" | El email ya existe en el sistema |
| 400 | "Las contraseñas no coinciden" | Los campos `password` y `confirmPassword` no son iguales |
| 400 | "El correo no tiene un formato válido" | El email no es válido |
| 400 | "La contraseña no cumple con los requisitos" | La contraseña no tiene la longitud o complejidad requerida |

---

## Funcionalidad de Login

### ¿Qué es?

El login autentica un usuario existente y devuelve un **token JWT** que se utiliza para acceder a endpoints protegidos.

### Endpoint

```
POST /api/auth/login
```

### Datos Requeridos

```json
{
  "email": "usuario@ejemplo.com",
  "password": "MiContraseña123!"
}
```

### Respuesta Exitosa (200 OK)

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c3VhcmlvQGVqZW1wbG8uY29tIiwicm9sZSI6Ik9QRVJBVE9SIiwidXNlcklkIjoyLCJpYXQiOjE2MDAzMDAwMDAsImV4cCI6MTYwMDM4NjQwMH0.a1b2c3d4e5f6",
  "tokenType": "Bearer",
  "expiresIn": 86400000,
  "userId": 2,
  "email": "usuario@ejemplo.com",
  "role": "OPERATOR"
}
```

### Información del Token

El token es válido por **24 horas** (86400000 milisegundos) y contiene:
- `sub`: Email del usuario
- `role`: Rol del usuario (ADMIN, OPERATOR, VIEWER)
- `userId`: ID único del usuario
- `iat`: Timestamp de creación
- `exp`: Timestamp de expiración

### Errores Comunes

| Código | Mensaje | Causa |
|--------|---------|-------|
| 400 | "Correo o contraseña incorrectos" | Las credenciales no coinciden |
| 400 | "El correo es obligatorio" | El email está vacío |
| 400 | "La contraseña es obligatoria" | La contraseña está vacía |

---

## Módulo Admin: Gestión de Usuarios y Roles

### ¿Qué es?

El módulo admin permite a los administradores gestionar todos los usuarios del sistema: listar, crear, actualizar información, cambiar roles y desactivar cuentas.

### Acceso

**Solo usuarios con rol `ADMIN` pueden acceder** a estos endpoints. El token JWT debe incluirse en el header `Authorization` con formato `Bearer <token>`.

### Roles Disponibles

- **ADMIN**: Acceso total al sistema y módulo de administración
- **OPERATOR**: Acceso a funcionalidades operativas normales
- **VIEWER**: Acceso de solo lectura

### Endpoints Disponibles

#### 1. Listar Todos los Usuarios

```
GET /api/admin/users
```

**Headers:**
```
Authorization: Bearer <token>
```

**Respuesta Exitosa (200 OK):**
```json
[
  {
    "id": 1,
    "fullName": "Administrador del Sistema",
    "email": "admin@invernadero.com",
    "role": "ADMIN",
    "active": true
  },
  {
    "id": 2,
    "fullName": "Juan Pérez",
    "email": "juan@ejemplo.com",
    "role": "OPERATOR",
    "active": true
  }
]
```

---

#### 2. Obtener Usuario por ID

```
GET /api/admin/users/{id}
```

**Ejemplo:**
```
GET /api/admin/users/2
```

**Respuesta Exitosa (200 OK):**
```json
{
  "id": 2,
  "fullName": "Juan Pérez",
  "email": "juan@ejemplo.com",
  "role": "OPERATOR",
  "active": true
}
```

---

#### 3. Crear Nuevo Usuario

```
POST /api/admin/users
```

**Headers:**
```
Authorization: Bearer <token>
Content-Type: application/json
```

**Cuerpo:**
```json
{
  "fullName": "María García",
  "email": "maria@ejemplo.com",
  "password": "Password123!",
  "role": "OPERATOR"
}
```

**Validaciones:**
- `fullName`: No puede estar vacío
- `email`: Debe ser válido y único
- `password`: Mínimo 8 caracteres, mayúsculas, minúsculas y números
- `role`: Debe ser uno de: ADMIN, OPERATOR, VIEWER

**Respuesta Exitosa (201 CREATED):**
```json
{
  "id": 3,
  "fullName": "María García",
  "email": "maria@ejemplo.com",
  "role": "OPERATOR",
  "active": true
}
```

---

#### 4. Actualizar Información de Usuario

```
PUT /api/admin/users/{id}
```

**Headers:**
```
Authorization: Bearer <token>
Content-Type: application/json
```

**Cuerpo:**
```json
{
  "fullName": "María García López",
  "email": "maria.garcia@ejemplo.com",
  "role": "VIEWER"
}
```

**Restricciones:**
- Un admin **no puede cambiar su propio rol**
- El email debe ser único (si cambias el email actual)

**Respuesta Exitosa (200 OK):**
```json
{
  "id": 3,
  "fullName": "María García López",
  "email": "maria.garcia@ejemplo.com",
  "role": "VIEWER",
  "active": true
}
```

---

#### 5. Cambiar Estado de Cuenta (Activar/Desactivar)

```
PATCH /api/admin/users/{id}/status
```

**Headers:**
```
Authorization: Bearer <token>
Content-Type: application/json
```

**Cuerpo:**
```json
{
  "active": false
}
```

**Restricciones:**
- Un admin **no puede desactivar su propia cuenta**

**Respuesta Exitosa (200 OK):**
```json
{
  "id": 3,
  "fullName": "María García López",
  "email": "maria.garcia@ejemplo.com",
  "role": "VIEWER",
  "active": false
}
```

---

### Errores de Autorización

Si intentas acceder sin token o con un token inválido:

| Código | Mensaje | Causa |
|--------|---------|-------|
| 401 | "Unauthorized" | Token ausente o expirado |
| 403 | "Access Denied" | No tienes rol ADMIN |

---

## Pruebas en Postman

### Paso 1: Configurar el Ambiente (Opcional pero Recomendado)

1. Abre Postman
2. Haz clic en **Environments** (engranaje superior derecho)
3. Crea un nuevo ambiente llamado "Invernadero Local"
4. Añade las siguientes variables:

```
Variable: baseUrl
Initial Value: http://localhost:8080
Current Value: http://localhost:8080

Variable: token
Initial Value: (vacío)
Current Value: (vacío)

Variable: adminToken
Initial Value: (vacío)
Current Value: (vacío)
```

---

### Paso 2: Pruebas de Registro y Login

#### Test 1: Verificar que el Servidor está Disponible

- **Método:** GET
- **URL:** `{{baseUrl}}/api/auth/ping`
- **Respuesta esperada:** "Auth ping OK"

---

#### Test 2: Registrar un Nuevo Usuario

- **Método:** POST
- **URL:** `{{baseUrl}}/api/auth/register`
- **Headers:**
  ```
  Content-Type: application/json
  ```
- **Body (JSON):**
  ```json
  {
    "fullName": "Juan Operador",
    "email": "juan.operador@invernadero.com",
    "password": "Password123!",
    "confirmPassword": "Password123!"
  }
  ```

**Resultado esperado:**
- Código: 200
- La respuesta incluye un `token` JWT

**Scripts (Post-request):**
Añade este script para guardar el token:
```javascript
if (pm.response.code === 200) {
  var jsonData = pm.response.json();
  pm.environment.set("token", jsonData.token);
  pm.environment.set("userId", jsonData.userId);
  console.log("Token guardado: " + jsonData.token.substring(0, 20) + "...");
}
```

---

#### Test 3: Intentar Registrar con Email Duplicado

- **Método:** POST
- **URL:** `{{baseUrl}}/api/auth/register`
- **Body:**
  ```json
  {
    "fullName": "Juan Operador 2",
    "email": "juan.operador@invernadero.com",
    "password": "Password123!",
    "confirmPassword": "Password123!"
  }
  ```

**Resultado esperado:**
- Código: 400
- Mensaje: "El correo electrónico ya está registrado"

---

#### Test 4: Login con Credenciales Correctas

- **Método:** POST
- **URL:** `{{baseUrl}}/api/auth/login`
- **Body:**
  ```json
  {
    "email": "juan.operador@invernadero.com",
    "password": "Password123!"
  }
  ```

**Resultado esperado:**
- Código: 200
- La respuesta incluye `token`, `role: "OPERATOR"`

---

#### Test 5: Login con Contraseña Incorrecta

- **Método:** POST
- **URL:** `{{baseUrl}}/api/auth/login`
- **Body:**
  ```json
  {
    "email": "juan.operador@invernadero.com",
    "password": "WrongPassword123!"
  }
  ```

**Resultado esperado:**
- Código: 400
- Mensaje: "Correo o contraseña incorrectos"

---

### Paso 3: Login como Administrador

#### Test 6: Login con Credenciales del Admin

- **Método:** POST
- **URL:** `{{baseUrl}}/api/auth/login`
- **Body:**
  ```json
  {
    "email": "admin@invernadero.com",
    "password": "password"
  }
  ```

**Resultado esperado:**
- Código: 200
- Respuesta incluye `token` y `role: "ADMIN"`

**Scripts (Post-request):**
```javascript
if (pm.response.code === 200) {
  var jsonData = pm.response.json();
  pm.environment.set("adminToken", jsonData.token);
  console.log("Admin token guardado");
}
```

---

### Paso 4: Pruebas del Módulo Admin

#### Test 7: Listar Todos los Usuarios

- **Método:** GET
- **URL:** `{{baseUrl}}/api/admin/users`
- **Headers:**
  ```
  Authorization: Bearer {{adminToken}}
  ```

**Resultado esperado:**
- Código: 200
- Array con todos los usuarios registrados

---

#### Test 8: Obtener Usuario por ID

- **Método:** GET
- **URL:** `{{baseUrl}}/api/admin/users/2`
- **Headers:**
  ```
  Authorization: Bearer {{adminToken}}
  ```

**Resultado esperado:**
- Código: 200
- Información del usuario con ID 2

---

#### Test 9: Crear Nuevo Usuario (como Admin)

- **Método:** POST
- **URL:** `{{baseUrl}}/api/admin/users`
- **Headers:**
  ```
  Authorization: Bearer {{adminToken}}
  Content-Type: application/json
  ```
- **Body:**
  ```json
  {
    "fullName": "María Supervisora",
    "email": "maria.supervisora@invernadero.com",
    "password": "Password123!",
    "role": "OPERATOR"
  }
  ```

**Resultado esperado:**
- Código: 201
- Respuesta incluye el usuario creado con ID asignado

---

#### Test 10: Cambiar Rol de Usuario

- **Método:** PUT
- **URL:** `{{baseUrl}}/api/admin/users/2`
- **Headers:**
  ```
  Authorization: Bearer {{adminToken}}
  Content-Type: application/json
  ```
- **Body:**
  ```json
  {
    "fullName": "Juan Operador",
    "email": "juan.operador@invernadero.com",
    "role": "VIEWER"
  }
  ```

**Resultado esperado:**
- Código: 200
- Usuario actualizado con nuevo rol `VIEWER`

---

#### Test 11: Desactivar Cuenta de Usuario

- **Método:** PATCH
- **URL:** `{{baseUrl}}/api/admin/users/2/status`
- **Headers:**
  ```
  Authorization: Bearer {{adminToken}}
  Content-Type: application/json
  ```
- **Body:**
  ```json
  {
    "active": false
  }
  ```

**Resultado esperado:**
- Código: 200
- Usuario con `active: false`

---

#### Test 12: Intentar Acceder sin Token

- **Método:** GET
- **URL:** `{{baseUrl}}/api/admin/users`
- **Headers:** (sin Authorization)

**Resultado esperado:**
- Código: 401
- Mensaje de Unauthorized

---

#### Test 13: Intentar Acceder sin Rol Admin

- **Método:** GET
- **URL:** `{{baseUrl}}/api/admin/users`
- **Headers:**
  ```
  Authorization: Bearer {{token}}
  ```
  (usando token de usuario OPERATOR)

**Resultado esperado:**
- Código: 403
- Mensaje de Access Denied

---

## Notas Importantes

### Seguridad

1. **Nunca compartas tu token JWT** - contiene información sensible
2. **Los tokens expiran en 24 horas** - necesitarás hacer login de nuevo después
3. **Las contraseñas se almacenan encriptadas** usando BCrypt
4. **El email de admin es** `admin@invernadero.com` con contraseña `password`

### Flujo Típico

```
1. Usuario se registra en /api/auth/register
   ↓
2. Sistema crea usuario con rol OPERATOR
   ↓
3. Usuario hace login en /api/auth/login
   ↓
4. Sistema devuelve token JWT (24 horas válido)
   ↓
5. Usuario usa token en header Authorization
   ↓
6. Admin puede gestionar usuarios desde /api/admin/users
```

### Variables de Entorno (Backend)

El backend se configura mediante variables de entorno. Las principales son:

```
SPRING_DATASOURCE_URL=jdbc:postgresql://invernadero-postgres:5432/invernadero_db
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres
JWT_SECRET=mi-clave-secreta-muy-larga-para-firmar-tokens
JWT_EXPIRATION_MS=86400000
```

### Comandos Útiles (Docker)

```bash
# Ver logs del backend
docker logs -f invernadero-backend

# Entrar a la base de datos
docker exec -it invernadero-postgres psql -U postgres -d invernadero_db

# Ver usuarios en la BD
SELECT id, email, role, active FROM users;

# Reiniciar servicios
docker-compose -f docker-compose.backend.yml restart
```

---

## Troubleshooting

### "Token expirado"
- Haz login de nuevo para obtener un nuevo token

### "Correo o contraseña incorrectos"
- Verifica que escribiste correctamente el email y contraseña
- Asegúrate de que la cuenta existe

### "Access Denied"
- Verifica que tienes rol ADMIN
- Usa el `adminToken` en lugar del `token` regular

### "La contraseña no cumple con los requisitos"
- Debe tener: mínimo 8 caracteres, mayúsculas, minúsculas y números
- Ejemplo válido: `Password123!`

---

**Última actualización:** 5 de Mayo de 2026

Para más información, consulta los archivos de documentación técnica del backend en [backend/invernadero_inteligente_backend/docs](../../../backend/invernadero_inteligente_backend/docs/README_BACKEND.md).
