# Plantilla SDD con agentes

> Plantilla genérica para documentar cualquier proyecto con desarrollo dirigido por especificación (SDD): primero se escribe la spec, luego el plan, luego las tareas, y solo entonces se toca el código.
>
> **Cómo usar esta plantilla:** copia todos los archivos de esta carpeta directamente a la **raíz de tu nuevo repositorio** (es fundamental que carpetas como `.github/` y `.agents/` queden en la raíz del repositorio para que GitHub Actions y los asistentes las detecten correctamente). A estas templates faltaría quitar/cambiar la `licencia`, editar el `readme.md` y `agents.md` y usar /sdd-constitution para crear la constitution. Los 3 .md restantes en `/template` son opcionales. En caso de la seguridad, ademas se harían ajusten en la seguridad del repositorio y si aplica crear `SECURITY.md` y abrir el canal privado para reportar fallos de seguridad. Rellena la `constitution/` una vez al arrancar y crea una carpeta por feature a partir de `features/NNN-nombre-feature/`. Sustituye todo lo que esté entre `<…>` y borra las notas en _cursiva_. No se aplica a `AGENTS.md`.


## Estructura

```
<raíz-del-proyecto>/
├── .github/                 ← configuración de GitHub (plantillas de PR, issues, bots y CI/CD)
│   ├── ISSUE_TEMPLATE/      ← plantillas para reportar bugs y proponer features
│   ├── workflows/           ← acciones automatizadas (triaje, CI, CD y DevSecOps)
│   └── pull_request_template.md ← plantilla automática de Pull Requests
├── .agents/                 ← comandos y skills para los agentes (Ver docs ya que algunos usan otras carpetas)
│   ├── commands/            ← comandos custom
│   └── skills/              ← catálogo de skills SDD (sdd, sdd-spec, sdd-plan, sdd-task, sdd-implements, etc.)
├── constitution/            ← reglas estables del proyecto
│   ├── mission.md           ← qué construimos y para quién
│   ├── roadmap.md           ← orden de las features y progreso
│   └── tech-stack.md        ← tecnologías, convenciones, seguridad y límites duros
├── docs/                    ← documentación del proyecto
│   ├── ADR/                 ← Architectural Decision Records (decisiones clave)
│   │   └── ADR-NN.md        ← plantilla de ADR
│   └── NNN-nombre-feature/  ← resúmenes de implementación (NN-task.md), qa-note.md y contexto
├── features/                ← carpetas donde se encontrarán las features
│   └── NNN-nombre-feature/  ← carpeta dedicada a cada feature con spec.md, plan.md y tasks.md
├── src/                     ← código fuente del proyecto
├── AGENTS.md                ← instrucciones para los agentes
├── LICENSE                  ← licencia de la plantilla (por la que se utilice)
├── .gitignore               ← archivos que no se suben al repo (ADAPTAR manualmente al stack)
├── .env.example             ← variables de entorno de ejemplo (el archivo .env real NUNCA se sube a GitHub)
├── CONTRIBUTING.md          ← explica cómo otras personas pueden contribuir 
├── CODE_OF_CONDUCT.md       ← establece las normas de comportamiento de la comunidad
├── SECURITY.md              ← indica cómo reportar vulnerabilidades de forma responsable
└── README.md                ← resumen del proyecto para los usuarios.
```

> [!IMPORTANT]
> **Adaptación manual de `.gitignore`:** El archivo `.gitignore` inicial es minimalista. Al iniciar tu proyecto real, debes configurarlo manualmente según el lenguaje y herramientas que uses (por ejemplo añadiendo `.env`, carpetas de dependencias como `node_modules/`, directorios de compilación como `target/` o `dist/`, y carpetas de reportes temporales como `security-reports/`).

_La constitución y cada feature pueden ser un único archivo si el proyecto/feature es pequeño._

---

## Flujo para una feature nueva

1. Crear `features/NNN-nombre-feature/` con el siguiente número libre (`001`, `002`, …).
2. Escribir `spec.md`: qué hace, por qué y criterios de aceptación medibles.
3. Escribir `plan.md`: enfoque técnico y decisiones, respetando `constitution/tech-stack.md`.
4. Desglosar en `tasks.md` y marcar el progreso.
5. Implementar y validar (build/tests/lint o lo que defina la constitución).
6. Actualizar `constitution/roadmap.md` (mover la feature a "Hecho").

> La constitución manda: si una feature choca con `mission.md` o `tech-stack.md`, se replantea la feature, no la constitución.

---


## Configuración Manual en GitHub: CI/CD y GitHub Security

Al trasladar esta plantilla a un repositorio real de GitHub, los flujos automáticos necesitan que configures manualmente ciertos permisos, secretos y protecciones en la interfaz web de GitHub:

### 1. Secretos del Repositorio (`Settings > Secrets and variables > Actions`)

Debes crear los siguientes **Repository Secrets** según las integraciones que utilices:

| Secreto | Requerido por | Descripción |
| :--- | :--- | :--- |
| `DOCKERHUB_USERNAME` | `cd.yml` | (Opcional) Tu usuario en Docker Hub. Si no se define, se publica únicamente en GHCR. |
| `DOCKERHUB_TOKEN` | `cd.yml` | (Opcional) Token de Docker Hub con permisos de lectura y escritura (`Read & Write`). |
| `OPENAI_API_KEY` o `ANTHROPIC_API_KEY` | `security.yml` (Strix) | (Opcional) Clave de API de LLM para pruebas dinámicas de penetración asistidas por IA. |
| `STRIX_API_KEY` | `security.yml` (Strix) | (Opcional) Clave de la plataforma Strix en la nube si usas su servicio gestionado. |

> [!NOTE]
> `GITHUB_TOKEN` es generado automáticamente por GitHub en cada ejecución de Actions con permisos para subir imágenes a GitHub Container Registry (`ghcr.io`) y cargar reportes SARIF a la pestaña *Security*. No tienes que crearlo manualmente.

### 2. Reglas de Protección de Ramas (`Settings > Branches`)

¿Qué son y para qué sirven?
Son protecciones que impiden que nadie (ni desarrolladores ni agentes de IA) suba código directamente a las ramas estables (`main` o `develop`). Garantizan que **todo cambio deba pasar obligatoriamente por un Pull Request y que todos los tests automáticos pasen en verde antes de que el código se fusione**.

Pasos recomendados:
1. En GitHub ve a **Settings > Branches** y añade una regla para `main` (y otra para `develop` si tu proyecto usa rama de desarrollo).
2. Activa la casilla **Require a pull request before merging** (obliga a usar Pull Requests).
3. Activa la casilla **Require status checks to pass before merging** (obliga a que los flujos automáticos terminen con éxito).
4. En el buscador de comprobaciones, selecciona los jobs que correspondan a tu stack (por ejemplo: `Verificación y Lint`, `Tests Automatizados`, `Escaneo de Secretos y SAST Universal`, `SCA de Dependencias`).

### 3. Gestión Manual en la pestaña `Security` de GitHub

El pipeline automatiza los escaneos en cada Pull Request, pero debes gestionar manualmente:
- **Alertas de Dependencias (Dependabot Alerts):** Revisar si las vulnerabilidades reportadas en los manifiestos de dependencias (`package.json`, `pom.xml`, `requirements.txt`, `go.mod`, etc.) afectan a tu código.
- **Alertas de Code Scanning (Semgrep & Trivy):** Revisar en `Security > Code scanning` las líneas marcadas con fallos de seguridad o descartar falsos positivos con justificación.
- **Canal Privado de Reportes de Seguridad (Security Advisories):** En `Security > Advisories`, activar los reportes privados para que investigadores puedan notificarte vulnerabilidades en privado antes de publicarlas.
- **Push Protection para Secret Scanning:** En `Settings > Code security and analysis`, asegurar que **Secret scanning** y **Push protection** estén activados para bloquear intentos de subir contraseñas o tokens directamente en la terminal.

---

**Creada por [Brais Moure](https://github.com/mouredev) en el curso de programación con agentes y curso de SDD.**  
_Plantilla extendida y adaptada con arquitectura de workflows CI/CD/DevSecOps y directrices para agentes._
