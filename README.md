# 🌿 Sistema de Gestión de Invernadero Inteligente

**Proyecto Integrador — Ciencias Básicas de la Computación / Integración I**  
**IS + IoT + IA**

---

## 📋 Descripción

Sistema integrado de bajo costo para automatizar el monitoreo y control de variables agroclimaticas en un invernadero. Integra tres equipos de desarrollo trabajando en paralelo sobre una plataforma unificada.

## 🏗️ Estructura del repositorio

```
invernadero-smart/
│
├── backend/            → API REST — Java + Spring Boot          [Equipo IS]
├── frontend/           → Aplicación web — React + Material UI   [Equipo IS]
├── mobile/             → App móvil — React Native               [Equipo IS]
├── database/
│   ├── migrations/     → Scripts SQL PostgreSQL                  [Equipo IS]
│   └── seeds/          → Datos de prueba                        [Equipo IS]
│
├── iot/
│   ├── firmware/       → Código ESP32                           [Equipo IoT]
│   ├── gateway/        → Python + Flask + MQTT                  [Equipo IoT]
│   ├── sensors/        → Configuración de sensores              [Equipo IoT]
│   └── actuators/      → Control de actuadores                  [Equipo IoT]
│
├── ai/
│   ├── models/         → Modelos Keras/TensorFlow               [Equipo IA]
│   ├── training/       → Datasets y scripts de entrenamiento    [Equipo IA]
│   ├── services/       → APIs Flask que exponen los modelos     [Equipo IA]
│   └── notebooks/      → Jupyter — exploración y análisis       [Equipo IA]
│
├── docs/
│   ├── general/        → Documentación compartida (todos)
│   ├── is/             → Documentación equipo IS
│   ├── iot/            → Documentación equipo IoT
│   └── ia/             → Documentación equipo IA
│
├── .github/
│   ├── CODEOWNERS      → Control de permisos por carpeta
│   └── workflows/      → CI/CD (GitHub Actions)
│
├── docker-compose.yml  → Levanta todo el entorno local
└── README.md
```

## 👥 Equipos

| Equipo | Líder | Carpetas |
|--------|-------|----------|
| Líder del proyecto | Nicolas Mosquera | Todo el repo |
| Ingeniería de Software | Jorel Vargas | `/backend` `/frontend` `/mobile` `/database` `/docs/is` |
| IoT | Juan Esteban Rojas | `/iot` `/docs/iot` |
| Inteligencia Artificial | Amaydy Quimboa | `/ai` `/docs/ia` |

## 🌿 Estrategia de ramas

| Rama | Uso |
|------|-----|
| `main` | Código estable. Solo merge via Pull Request aprobado por CODEOWNERS. |
| `develop` | Integración de features antes de pasar a main. |
| `feature/is/<nombre>` | Feature del equipo IS. Ej: `feature/is/login` |
| `feature/iot/<nombre>` | Feature del equipo IoT. Ej: `feature/iot/sensor-temperatura` |
| `feature/ia/<nombre>` | Feature del equipo IA. Ej: `feature/ia/modelo-maduracion` |
| `docs/<nombre>` | Cambios de documentación. Ej: `docs/vision-rup-v2` |

## ⚙️ Cómo empezar

```bash
# 1. Clonar el repositorio
git clone https://github.com/[nombre-org]/invernadero-smart.git
cd invernadero-smart

# 2. Crear tu rama de trabajo (ejemplo equipo IS)
git checkout develop
git checkout -b feature/is/nombre-funcionalidad

# 3. Hacer cambios, commit y push
git add .
git commit -m "feat(is): descripción del cambio"
git push origin feature/is/nombre-funcionalidad

# 4. Abrir Pull Request hacia develop en GitHub
```

## 📁 Documentación

La documentación del proyecto se encuentra en `/docs`:

| Documento | Ubicación |
|-----------|-----------|
| Documento de Visión RUP v2 | `/docs/is/Vision_RUP_v2_Invernadero.docx` |
| SRS IEEE 29148 | `/docs/is/SRS_IEEE29148_Invernadero.docx` |
| Plantilla de requerimientos (RF) | `/docs/is/Plantilla_RF.docx` |

## 🛠️ Stack tecnológico

| Equipo | Tecnologías |
|--------|-------------|
| IS — Backend | Java 17 + Spring Boot |
| IS — Frontend | React + Material UI |
| IS — Móvil | React Native |
| IS — BD | PostgreSQL + MongoDB |
| IS — Reportes | JasperReports + D3.js + Highcharts |
| IoT | Python + Flask + MQTT + ESP32 + Raspberry Pi |
| IA | Python + Keras + TensorFlow + Flask |

## 📐 Metodología

- **Proceso:** RUP (Rational Unified Process)
- **Gestión de sprints:** Scrum — gestionado en Taiga
- **Control de versiones:** GitHub (este repositorio)
- **Estándar de documentación:** IEEE 29148:2018

---

> **Líder del proyecto:** Nicolas Mosquera  
> **Institución:** [Nombre de la universidad]  
> **Asignatura:** Ciencias Básicas de la Computación — Integración I
