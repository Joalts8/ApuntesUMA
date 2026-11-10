# Plantilla SDD con agentes

> Plantilla genérica para documentar cualquier proyecto con desarrollo dirigido por especificación (SDD): primero se escribe la spec, luego el plan, luego las tareas, y solo entonces se toca el código.
>
> **Cómo usar esta plantilla:** Copia todos los archivos de esta carpeta directamente a la **raíz de tu nuevo repositorio** (es fundamental que carpetas como `.github/` y `.agents/` queden en la raíz para que GitHub Actions y los asistentes las detecten correctamente). 
> Al iniciar el proyecto: ajusta la `licencia`, personaliza el `README.md` y `AGENTS.md`, y ejecuta `/sdd-constitution` para inicializar la constitución a partir de sus plantillas base. Los archivos restantes de documentación son opcionales. En seguridad, ajusta las opciones de GitHub y, si aplica, mantén `SECURITY.md` activando el canal privado para reportar vulnerabilidades. Rellena la constitución al arrancar y crea una carpeta por feature a partir de `features/NNN-nombre-feature/`. Sustituye todos los marcadores `<…>` en los archivos y borra las notas en _cursiva_.

## Estructura

```text
<raíz-del-proyecto>/
├── .github/                 ← configuración de GitHub (plantillas de PR, issues, bots y CI/CD)
│   ├── ISSUE_TEMPLATE/      ← plantillas para reportar bugs, proponer features y tareas técnicas
│   ├── workflows/           ← acciones automatizadas (triaje universal y DevSecOps con Strix)
│   ├── dependabot.yml       ← configuración de actualizaciones automáticas
│   ├── pull_request_template.md ← plantilla predeterminada de Pull Requests
│   └── readme_plantillas.md ← guía completa y plantillas dedicadas para CI y CD
├── .agents/                 ← configuración de agentes, comandos y skills
│   ├── agents/              ← subagentes especializados (sdd-plan-reviewer, sdd-qa-writer)
│   ├── commands/            ← comandos custom
│   └── skills/              ← catálogo de skills SDD (sdd, sdd-spec, sdd-plan, sdd-task, sdd-implement, etc.)
├── constitution/            ← reglas estables del proyecto (inicializable con /sdd-constitution)
│   └── (vacía)              ← mission.md, roadmap.md y tech-stack.md se generan desde assets de la skill
├── docs/                    ← documentación y memoria técnica del proyecto
│   ├── approved-decisions.md ← memoria técnica global (inicializable desde assets de sdd-decisions-review)
│   ├── ADR/                 ← Architectural Decision Records (creados con la skill adr)
│   │   └── (vacía)          ← los ADR se guardan aquí tras ser aprobados
│   └── NNN-nombre-feature/  ← contexto de feature: decisions.md, NN-task.md, qa-note.md
├── features/                ← especificaciones y planes de las features
│   └── NNN-nombre-feature/  ← carpeta dedicada a cada feature con spec.md, plan.md y tasks.md
├── src/                     ← código fuente del proyecto
├── AGENTS.md                ← directrices para los agentes y la fábrica autónoma
├── LICENSE                  ← licencia del proyecto
├── .gitignore               ← exclusiones de git (ADAPTAR manualmente según el stack)
├── .env.example             ← variables de entorno de ejemplo (el archivo .env real NUNCA se sube)
├── CONTRIBUTING.md          ← guía de contribución comunitaria
├── CODE_OF_CONDUCT.md       ← normas de comportamiento y convivencia
├── SECURITY.md              ← política de divulgación responsable de vulnerabilidades
└── README.md                ← resumen del proyecto para los usuarios.
```

> [!NOTE]
> **`constitution/` y `docs/ADR/` se entregan inicialmente vacías (con `.keep`):**
> - La constitución se genera al inicio del proyecto ejecutando la skill `/sdd-constitution`, que carga las plantillas base desde `.agents/skills/sdd-constitution/assets/`.
> - Los ADRs se crean cuando surge una decisión arquitectónica usando la skill `/adr`, que utiliza la plantilla en `.agents/skills/adr/assets/ADR-NN.md`.
> - La memoria global `docs/approved-decisions.md` se inicializa a partir de `.agents/skills/sdd-decisions-review/assets/approved-decisions.md`.

> [!IMPORTANT]
> **Adaptación manual de `.gitignore`:** El archivo `.gitignore` inicial es minimalista para que esté presente visualmente. Al iniciar tu proyecto real, debes configurarlo manualmente según el lenguaje y herramientas que uses (añadiendo `.env`, carpetas de dependencias como `node_modules/`, directorios de compilación como `target/` o `dist/`, y carpetas de reportes temporales como `security-reports/`).

---

## Flujo para una feature nueva

1. Crear `features/NNN-nombre-feature/` con el siguiente número libre (`001`, `002`, …).
2. Escribir `spec.md`: qué hace, por qué y criterios de aceptación medibles en notación EARS.
3. Escribir `plan.md`: enfoque técnico y decisiones, respetando `constitution/tech-stack.md` y consultando `docs/approved-decisions.md`.
4. Desglosar en `tasks.md` y marcar el progreso.
5. Implementar y validar mediante TDD (unitarios y persistencia con BD levantada; APIs externas no disponibles marcadas como "No comprobado").
6. Actualizar `constitution/roadmap.md` (mover la feature a "Hecho" tras emitir `qa-note.md`).

> La constitución manda: si una feature choca con `mission.md` o `tech-stack.md`, se replantea la feature, no la constitución.

---

## Configuración Manual en GitHub: CI/CD y GitHub Security

Al trasladar esta plantilla a un repositorio real de GitHub, los flujos automáticos necesitan que configures manualmente ciertos permisos, secretos y protecciones en la interfaz web de GitHub:

### 1. Secretos del Repositorio (`Settings > Secrets and variables > Actions`)

Debes crear los siguientes **Repository Secrets** según las integraciones que utilices:

| Secreto | Requerido por | Descripción |
| :--- | :--- | :--- |
| `DOCKERHUB_USERNAME` | CD (al crear `cd.yml` desde la plantilla) | (Opcional) Tu usuario en Docker Hub. Si no se define, se publica únicamente en GHCR. |
| `DOCKERHUB_TOKEN` | CD (al crear `cd.yml` desde la plantilla) | (Opcional) Token de Docker Hub con permisos de lectura y escritura (`Read & Write`). |
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
4. En el buscador de comprobaciones, selecciona los jobs que correspondan a tu stack (por ejemplo: `Escaneo de Secretos y SAST Universal`, `SCA de Dependencias y Vulnerabilidades`).

### 3. Gestión Manual en la pestaña `Security` de GitHub

El pipeline automatiza los escaneos en cada Pull Request, pero debes gestionar manualmente:
- **Alertas de Dependencias (Dependabot Alerts):** Revisar si las vulnerabilidades reportadas en los manifiestos de dependencias (`package.json`, `pom.xml`, `requirements.txt`, `go.mod`, etc.) afectan a tu código.
- **Alertas de Code Scanning (Semgrep & Trivy):** Revisar en `Security > Code scanning` las líneas marcadas con fallos de seguridad o descartar falsos positivos con justificación.
- **Canal Privado de Reportes de Seguridad (Security Advisories):** En `Security > Advisories`, activar los reportes privados para que investigadores puedan notificarte vulnerabilidades en privado antes de publicarlas.
- **Push Protection para Secret Scanning:** En `Settings > Code security and analysis`, asegurar que **Secret scanning** y **Push protection** estén activados para bloquear intentos de subir contraseñas o tokens directamente en la terminal.

---

**Creada por [Brais Moure](https://github.com/mouredev) en el curso de programación con agentes y curso de SDD.**  
_Plantilla extendida y adaptada con arquitectura de workflows CI/CD/DevSecOps y directrices para agentes y fábricas autónomas._
