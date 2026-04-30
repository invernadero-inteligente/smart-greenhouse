# 🌿 Configuración de Variables de Entorno

## Descripción

El proyecto utiliza **variables de entorno** para gestionar configuraciones sensibles (contraseñas, claves JWT, credenciales de BD, etc.) sin exponerlas en el código fuente.

---

## 📋 Archivos de Configuración

| Archivo | Propósito | Incluir en Git |
|---------|-----------|-----------------|
| **`.env.example`** | Plantilla con valores por defecto (sin valores reales) | ✅ Sí |
| **`template.env`** | Plantilla completa con documentación detallada | ✅ Sí |
| **`.env`** | Archivo real con tus configuraciones locales | ❌ NO (en .gitignore) |

---

## 🚀 Cómo Configurar Localmente

### 1. Crear archivo `.env`
```bash
# En la raíz del proyecto backend)
cp .env.example .env
```

### 2. Editar `.env` con tus valores
```ini
# Ejemplo de configuración local
DB_HOST=localhost
DB_PORT=5432
DB_NAME=invernadero_db
DB_USERNAME=postgres
DB_PASSWORD=tu_contraseña_real
JWT_SECRET=tu_clave_secreta_minimo_32_caracteres
MAIL_USERNAME=tu_email@gmail.com
MAIL_PASSWORD=tu_contraseña_app
```

### 3. Spring Boot importará automáticamente el archivo
El archivo `application.properties` contiene:
```properties
spring.config.import=optional:file:.env[.properties]
```

Esto hace que Spring Boot busque y cargue las variables desde `.env` automáticamente.

---

## 🔧 Variables por Categoría

### 🗄️ Base de Datos (PostgreSQL)
```ini
DB_HOST=localhost
DB_PORT=5432
DB_NAME=invernadero_db
DB_USERNAME=postgres
DB_PASSWORD=postgres123
```

### 🔐 Seguridad (JWT)
```ini
JWT_SECRET=clave_secreta_de_minimo_32_caracteres
JWT_EXPIRATION_MS=86400000        # 24 horas
JWT_REFRESH_EXPIRATION_MS=604800000  # 7 días
```

### 🌐 CORS (Orígenes permitidos)
```ini
CORS_ALLOWED_ORIGINS=http://localhost:3000,http://localhost:5173
```

### 📧 Email
```ini
MAIL_HOST=smtp.gmail.com
MAIL_PORT=587
MAIL_USERNAME=tu_email@gmail.com
MAIL_PASSWORD=contraseña_de_aplicacion  # NO la contraseña de Gmail
```

### 🔌 IoT / MQTT
```ini
IOT_MQTT_BROKER_URL=mqtt://localhost:1883
IOT_MQTT_CLIENT_ID=invernadero-backend
```

---

## ⚠️ Buenas Prácticas

### ✅ Hacer
- ✔️ Mantener `template.env` en el repositorio con documentación
- ✔️ Mantener `.env.example` en el repositorio (valores de ejemplo)
- ✔️ Cambiar `.env` a valores reales en cada entorno
- ✔️ Usar contraseñas seguras en producción
- ✔️ Documentar qué hace cada variable

### ❌ NO Hacer
- ❌ Commitear el archivo `.env` real
- ❌ Usar contraseñas débiles
- ❌ Exponer valores sensibles en logs
- ❌ Hardcodear valores en el código

---

## 🔑 Generar JWT_SECRET Seguro

En caso de necesitar una clave segura para `JWT_SECRET`:

### Opción 1: Linux/Mac
```bash
openssl rand -base64 32
```

### Opción 2: Windows PowerShell
```powershell
[Convert]::ToBase64String((Get-Random -InputObject (32..255) -Count 32))
```

### Opción 3: Online
Usa [https://generate-random.org/](https://generate-random.org/) y genera 32 caracteres base64.

---

## 📱 Configuración por Entornos

Si necesitas múltiples entornos (desarrollo, staging, producción):

```bash
# Desarrollo
.env.development

# Staging
.env.staging

# Producción
.env.production
```

Para usar uno específico, modifica `application.properties`:
```properties
spring.config.import=optional:file:.env.${ENVIRONMENT}[.properties]
```

O exporta la variable antes de ejecutar:
```bash
export ENVIRONMENT=development
mvn spring-boot:run
```

---

## ✅ Checklist de Configuración Inicial

- [ ] Copiar `.env.example` como `.env`
- [ ] Editar `.env` con credenciales locales
- [ ] Verificar que `.env` está en `.gitignore`
- [ ] Compilar: `mvn clean compile`
- [ ] Ejecutar: `mvn spring-boot:run`
- [ ] Probar endpoint: `curl http://localhost:8080/api/v1/invernaderos`

---

## 🐛 Troubleshooting

### "No configuration properties found"
- Verifica que el archivo `.env` existe en la raíz del proyecto
- Asegúrate que `spring.config.import=optional:file:.env[.properties]` está en `application.properties`

### Variables no se cargan
- Recuerda reiniciar la aplicación después de cambiar `.env`
- Verifica la sintaxis del archivo `.env` (sin espacios alrededor de `=`)

### "Could not resolve placeholder"
- Una variable en `application.properties` referencia una que no existe en `.env`
- Usa valores por defecto: `${VARIABLE_NAME:valor_defecto}`

---

## 📚 Documentación Adicional

Para más información sobre la estructura del proyecto, consulta:
- `docs/README_BACKEND.md` - Documentación técnica completa
- `docs/GUIA_RAPIDA.md` - Guía de desarrollo rápido


