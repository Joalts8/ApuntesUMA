# Guía de Plantillas y Flujos de GitHub

Este directorio contiene la configuración de GitHub del repositorio: plantillas estandarizadas para Pull Requests e Issues, enlaces de contacto y flujos de automatización para GitHub Web, GitHub CLI (`gh`), GitHub Actions y asistentes de IA.

---

## Insignias de Estado (Badges de Ejemplo)

> [!NOTE]
> Al encontrarse esta carpeta dentro de `Template/.github` (no en la raíz del repositorio), los workflows no se activan de forma automática. Cuando copies o traslades esta plantilla a la raíz de tu proyecto final, sustituye `<OWNER>` y `<REPO>` por tu usuario/organización y nombre de repositorio en GitHub, y `<DOCKERHUB_USER>` por tu usuario de Docker Hub.

### Badges estándar de GitHub Actions:
```markdown
[![CI - Integración Continua](https://github.com/<OWNER>/<REPO>/actions/workflows/ci.yml/badge.svg)](https://github.com/<OWNER>/<REPO>/actions/workflows/ci.yml)
[![CD - Despliegue Continuo (Docker Hub & GHCR)](https://github.com/<OWNER>/<REPO>/actions/workflows/cd.yml/badge.svg)](https://github.com/<OWNER>/<REPO>/actions/workflows/cd.yml)
[![DevSecOps & Security Analysis](https://github.com/<OWNER>/<REPO>/actions/workflows/security.yml/badge.svg)](https://github.com/<OWNER>/<REPO>/actions/workflows/security.yml)
```

### Badges dinámicos con Shields.io (Workflows, Seguridad y Contenedores):
```markdown
<!-- Estado de flujos -->
[![CI Status](https://img.shields.io/github/actions/workflow/status/<OWNER>/<REPO>/ci.yml?branch=main&label=CI&logo=githubactions)](https://github.com/<OWNER>/<REPO>/actions/workflows/ci.yml)
[![CD Status](https://img.shields.io/github/actions/workflow/status/<OWNER>/<REPO>/cd.yml?label=CD%20Deploy&logo=docker)](https://github.com/<OWNER>/<REPO>/actions/workflows/cd.yml)
[![Security Scan](https://img.shields.io/github/actions/workflow/status/<OWNER>/<REPO>/security.yml?label=DevSecOps&logo=githubactions)](https://github.com/<OWNER>/<REPO>/actions/workflows/security.yml)

<!-- Imágenes en Registries -->
[![Docker Pulls](https://img.shields.io/docker/pulls/<DOCKERHUB_USER>/springboot-app?logo=docker&logoColor=white)](https://hub.docker.com/r/<DOCKERHUB_USER>/springboot-app)
[![Docker Image Version](https://img.shields.io/docker/v/<DOCKERHUB_USER>/springboot-app?sort=semver&logo=docker&logoColor=white)](https://hub.docker.com/r/<DOCKERHUB_USER>/springboot-app)
[![GHCR Version](https://img.shields.io/badge/GHCR-ghcr.io%2F<OWNER>%2F<REPO>-blue?logo=github)](https://github.com/<OWNER>/<REPO>/pkgs/container/<REPO>)
```

---

## Estructura del directorio

```text
.github/
├── ISSUE_TEMPLATE/
│   ├── bug_report.md              # Plantilla para reportar incidencias/bugs ([Bug]: ...)
│   ├── feature_request.md         # Plantilla para proponer nuevas funcionalidades ([Feature]: ...)
│   ├── chore_or_refactor.md       # Plantilla para tareas técnicas, dependencias o refactor ([Chore]: ...)
│   ├── documentation.md           # Plantilla para mejoras y erratas de documentación ([Docs]: ...)
│   └── config.yml                 # Configuración del selector (deshabilita issues en blanco y enlaces de ayuda)
├── workflows/
│   ├── issue-triage.yml           # GitHub Action: bot de triaje automático y etiquetado
│   ├── ci.yml                     # GitHub Action: CI (ejemplo Java + Spring Boot, adaptable a cualquier stack)
│   ├── cd.yml                     # GitHub Action: CD multi-registro (GHCR out-of-the-box y Docker Hub opcional)
│   └── security.yml               # GitHub Action: DevSecOps (TruffleHog, Semgrep, Trivy, OWASP Noir, Strix)
├── dependabot.yml                 # Configuración de Dependabot: PRs automáticos de dependencias y seguridad
├── pull_request_template.md       # Plantilla predeterminada para Pull Requests
└── README.md                      # Esta guía de uso y mantenimiento
```

---

## 1. Instrucciones de uso de las Plantillas de Issues y PRs

### Plantilla de Pull Requests: [`pull_request_template.md`](pull_request_template.md)
* **Propósito:** Asegurar que ningún cambio entre a `develop` o `main` sin contexto, tests ni trazabilidad con el SDD.
* **Cómo usarla:**
  - Al abrir un PR desde GitHub Web o con `gh pr create`, el archivo se carga automáticamente en la descripción.
  - **Obligatorio rellenar:**
    1. **Tipo de cambio:** Marcar con `[x]` si es `feat`, `fix`, `docs`, `refactor`, etc.
    2. **Descripción y motivación:** Explicar el qué y el porqué del cambio.
    3. **Enlace a feature/issue:** Referenciar `Closes #NN` o la carpeta `features/NNN-nombre/`.
    4. **Checklist de verificación:** Confirmar que los tests pasan en local, no se han roto contratos y la cobertura es adecuada.

### Plantillas de Issues: [`ISSUE_TEMPLATE/`](ISSUE_TEMPLATE/)
El directorio contiene 4 tipos de plantillas estructuradas + configuración:
- [`bug_report.md`](ISSUE_TEMPLATE/bug_report.md): Para errores de software. Obliga a indicar comportamiento esperado, pasos para reproducir y logs. Genera títulos con prefijo `[Bug]: `.
- [`feature_request.md`](ISSUE_TEMPLATE/feature_request.md): Para nuevas funciones o propuestas de arquitectura. Genera títulos con prefijo `[Feature]: `.
- [`chore_or_refactor.md`](ISSUE_TEMPLATE/chore_or_refactor.md): Para tareas técnicas, actualización de dependencias, scripts o refactorización. Genera `[Chore]: `.
- [`documentation.md`](ISSUE_TEMPLATE/documentation.md): Para correcciones en specs, docs o tutoriales. Genera `[Docs]: `.
- [`config.yml`](ISSUE_TEMPLATE/config.yml): Deshabilita los issues en blanco (`blank_issues_enabled: false`) y redirige incidencias de seguridad a `SECURITY.md`.

### Cómo crear Issues y PRs según la herramienta:

#### A. Desde la Web de GitHub
- **PR:** Click en *New Pull Request* -> La descripción se carga automáticamente.
- **Issue:** Click en *Issues > New Issue* -> Se muestra el selector visual con las 4 plantillas y botones de soporte.

#### B. Desde GitHub CLI (`gh`)
```bash
# Crear PR interactivo con la plantilla:
gh pr create

# Crear Issue seleccionando la plantilla deseada:
gh issue create
```

#### C. Desde Asistentes de IA y Agentes (Normas de `AGENTS.md`)
Los agentes que operan en el repositorio siguen estas pautas estrictas:
- **Al abrir PRs:** Deben completar todas las secciones de `pull_request_template.md` sustituyendo los placeholders `<...>` sin dejarlos en blanco.
- **Al abrir Issues:** Deben respetar obligatoriamente el prefijo correspondiente en el título (`[Bug]: `, `[Feature]: `, etc.) para que el bot de triaje automático aplique las etiquetas correctas.

---

## 2. Automatizaciones y Flujos de CI/CD/DevSecOps

### Triaje de Incidencias: [`workflows/issue-triage.yml`](workflows/issue-triage.yml)
Se dispara al abrir o editar issues:
- Etiqueta automáticamente según el título (`bug`, `enhancement`, `chore`, `documentation`).
- Valida que no se dejen secciones vacías con `<...>` y añade advertencias amistosas al autor si falta información.

### Integración Continua (CI): [`workflows/ci.yml`](workflows/ci.yml)
- **Disparo:** `push` en cualquier rama de trabajo / worktree (ej. `feature/*`, `fix/*`), y en `pull_request` contra `main` y `develop`.
- **Trabajo realizado:**
  1. `check-and-lint`: Setup JDK 21, wrappers de Maven/Gradle y análisis Checkstyle/compilación.
  2. `test`: Ejecución de tests unitarios y de integración de Spring Boot.
  3. `build`: Empaquetado del JAR ejecutable (`package` / `bootJar`).

### Despliegue Continuo (CD): [`workflows/cd.yml`](workflows/cd.yml)
- **Disparo:** Tras el merge (`push`) en `main` o `develop`, creación de tags de versión (`v*.*.*`), o manual (`workflow_dispatch`).
- **Trabajo realizado:**
  1. Compila o empaqueta la aplicación.
  2. Construye la imagen Docker multi-plataforma con Buildx utilizando [`docker/Dockerfile`](../docker/Dockerfile).
  3. Publica de forma predeterminada en **GitHub Container Registry (`ghcr.io`)** (funciona automáticamente sin configurar secretos) y, si se han proporcionado credenciales, también en **Docker Hub**.
  4. Aplica el paso final de despliegue o notificación.

### DevSecOps & Seguridad: [`workflows/security.yml`](workflows/security.yml)
- **Disparo:** `push` en ramas de trabajo, `pull_request` a `main`/`develop`, schedule semanal y manual (`workflow_dispatch`).
- **Niveles de análisis:**
  1. **TruffleHog:** Escaneo profundo de secretos, tokens y claves privadas en el historial de commits.
  2. **Semgrep (SAST):** Análisis estático de vulnerabilidades en el código, exportando informe SARIF a la pestaña *Security* de GitHub.
  3. **Trivy (SCA):** Escaneo de CVEs en dependencias (`pom.xml`, `package.json`, `requirements.txt`, etc.) y en el Dockerfile.
  4. **OWASP Noir:** Extracción y mapeo de la superficie de ataque y endpoints HTTP (Spring Boot / APIs Web).
  5. **Strix (Pentest IA Opcional):** Ejecución bajo demanda (`workflow_dispatch`) o programada de auditoría dinámica asistida por IA si se configuran claves LLM.

### Actualizaciones Automáticas: [`dependabot.yml`](dependabot.yml)
- **Propósito:** Automatizar la creación de Pull Requests cuando se publiquen parches de seguridad o nuevas versiones de dependencias del proyecto y de GitHub Actions.
- **Frecuencia:** Revisión semanal programada.

---

## 3. Guía de Modificación de los Workflows según Necesidades del Proyecto

Los flujos `.yml` están parametrizados como ejemplo funcional para Java + Spring Boot, pero son fácilmente adaptables a cualquier tecnología o flujo de trabajo. A continuación se detallan las modificaciones más habituales:

### A. Modificar o Cambiar de Lenguaje / Runtime en `ci.yml`

1. **Cambiar la versión o distribución de Java:**
   En el step `actions/setup-java@v4`:
   ```yaml
   with:
     distribution: 'temurin' # Otras opciones: 'corretto', 'zulu', 'microsoft'
     java-version: '17'      # Cambiar de '21' a '17' si el proyecto usa Java 17 LTS
     cache: 'gradle'         # Si usas Gradle en vez de Maven
   ```

2. **Adaptar a Node.js / TypeScript / React / Angular:**
   Reemplaza el step de setup de Java por `actions/setup-node@v4` y ajusta los comandos:
   ```yaml
   - uses: actions/setup-node@v4
     with:
       node-version: '20'
       cache: 'npm' # o 'pnpm' / 'yarn'
   - run: npm ci
   - run: npm run lint
   - run: npm test -- --coverage
   - run: npm run build
   ```

3. **Adaptar a Python (FastAPI / Django):**
   ```yaml
   - uses: actions/setup-python@v5
     with:
       python-version: '3.11'
       cache: 'pip'
   - run: pip install -r requirements.txt
   - run: pytest --cov
   ```

---

### B. Modificar la Estrategia de Ramas o Disparadores (`on:`)

Si tu equipo usa otra convención de nombres (por ejemplo `dev` en lugar de `develop`, o solo `main`):
1. **En `ci.yml` y `security.yml`:**
   ```yaml
   on:
     push:
       branches:
         - '**'
         - '!main'
         - '!dev'          # Si la rama de desarrollo se llama dev
     pull_request:
       branches: [ main, dev ]
   ```
2. **Si NO usas worktrees ni ramas temporales con push directo:**
   Puedes eliminar el bloque `push` y dejar que el CI se ejecute únicamente cuando se abran Pull Requests hacia `main` y `develop`.

---

### C. Personalizar el Flujo de Despliegue en `cd.yml`

1. **Ubicación del Dockerfile:**
   Por defecto, `cd.yml` busca el archivo en `docker/Dockerfile`. Si prefieres ubicarlo en la raíz del proyecto o en otra ruta, ajusta la propiedad `file:` en el step `docker/build-push-action@v5`:
   ```yaml
   with:
     context: .
     file: ./docker/Dockerfile # o ./Dockerfile
   ```

2. **Publicar en GitHub Container Registry (GHCR) vs Docker Hub:**
   - **GHCR (Predeterminado):** Funciona sin configuración adicional. Las imágenes se publican bajo `ghcr.io/<usuario>/<repositorio>` usando el `GITHUB_TOKEN` automático.
   - **Docker Hub (Opcional):** Si configuras los secretos `DOCKERHUB_USERNAME` y `DOCKERHUB_TOKEN` en tu repositorio, el pipeline iniciará sesión y publicará automáticamente también en Docker Hub sin que tengas que editar código.

3. **Añadir el paso de despliegue real en tu infraestructura:**
   En el job `deploy:`, sustituye los comandos de `echo` por la acción que use tu entorno:
   - **SSH a VPS / Servidor propio:** Usando `appleboy/ssh-action@master` para ejecutar `docker compose pull && docker compose up -d`.
   - **Kubernetes / Helm:** Usando `azure/k8s-deploy@v5`.
   - **AWS / GCP / Azure:** Usando sus respectivas acciones oficiales.

---

### D. Ajustar y Calibrar la Seguridad en `security.yml`

1. **Ajustar severidad en Trivy:**
   Si Trivy te bloquea por fallos de nivel medio o bajo, puedes filtrar para que solo reporte fallos críticos:
   ```yaml
   with:
     severity: 'CRITICAL' # o 'CRITICAL,HIGH'
   ```
2. **Proyectos que NO son APIs Web (Apps de escritorio, CLI o librerías):**
   - El step de **OWASP Noir** busca controladores HTTP. Si tu proyecto es una librería interna o app móvil nativa, puedes desactivar el job añadiendo en su cabecera:
     ```yaml
     noir-attack-surface:
       if: false # Desactiva el análisis de endpoints HTTP
     ```
3. **Pentesting Asistido por IA con Strix (Opcional):**
   - Este job se ejecuta **únicamente bajo demanda** (`workflow_dispatch`) marcando la casilla de ejecución o en el escaneo semanal si se han configurado secretos de API (`OPENAI_API_KEY`, `ANTHROPIC_API_KEY` o `STRIX_API_KEY`).
   - Sirve para auditar endpoints o código en busca de lógica vulnerable mediante agentes autónomos.

---

## 4. Acciones Manuales a Realizar en GitHub Web

Al trasladar esta plantilla a un repositorio nuevo en GitHub, debes realizar las siguientes configuraciones desde la interfaz web del repositorio:

### 1. Secretos del Repositorio (`Settings > Secrets and variables > Actions`)
Configura los siguientes secretos según los servicios externos que utilices:

| Secreto | Requerido por | Descripción |
| :--- | :--- | :--- |
| `DOCKERHUB_USERNAME` | `cd.yml` | (Opcional) Usuario en Docker Hub. Si no se define, se publica solo en GHCR. |
| `DOCKERHUB_TOKEN` | `cd.yml` | (Opcional) Personal Access Token de Docker Hub con permisos `Read & Write`. |
| `OPENAI_API_KEY` / `ANTHROPIC_API_KEY` | `security.yml` (Strix) | (Opcional) Clave para activar el análisis dinámico de pentesting con IA. |
| `STRIX_API_KEY` | `security.yml` (Strix) | (Opcional) Clave de la plataforma Strix en la nube si usas su servicio gestionado. |

### 2. Reglas de Protección de Ramas (`Settings > Branches`)
Para impedir que se suba código roto o sin verificar directamente a las ramas principales:
1. Pulsa en **Add branch ruleset** o **Add rule** para `main` (y otra para `develop` si existe).
2. Activa **Require a pull request before merging**.
3. Activa **Require status checks to pass before merging** y marca como obligatorios los checks del pipeline (ej. `Verificación de Código y Checkstyle`, `Tests Unitarios e Integración`, `Escaneo de Secretos y SAST Universal`, `SCA de Dependencias`).

### 3. Opciones de Seguridad del Repositorio (`Settings > Code security and analysis`)
1. **Secret Scanning & Push Protection:** Activa ambas opciones para que GitHub bloquee inmediatamente cualquier intento de hacer `git push` con contraseñas o tokens desde la terminal.
2. **Private Vulnerability Reporting:** En `Security > Advisories`, habilita los reportes privados para que la comunidad pueda avisarte de vulnerabilidades confidencialmente sin exponerlas en issues públicos.

---

## 5. Actualizaciones Automáticas y Pull Requests con Dependabot

¿Puede GitHub crear Pull Requests automáticamente cuando una librería tiene fallos o queda obsoleta? **Sí, gracias a Dependabot.**

### ¿Cómo funciona en esta plantilla?
1. **Pull Requests de Seguridad (Vulnerability Alerts):**
   - Ve a `Settings > Code security and analysis`.
   - Activa **Dependabot alerts** y **Dependabot security updates**.
   - **Resultado:** En cuanto se publica una vulnerabilidad conocida (CVE) que afecta a una librería de tu proyecto, Dependabot genera automáticamente un Pull Request con la versión corregida listo para que lo revises y fusiones.

2. **Pull Requests de Mantenimiento Periódico:**
   - El archivo [`.github/dependabot.yml`](dependabot.yml) incluido en esta plantilla revisa semanalmente si existen nuevas versiones de tus dependencias y de las GitHub Actions usadas en los flujos.
   - Si existen versiones más recientes, abrirá un PR con la actualización de dependencias, permitiendo mantener el software siempre al día sin esfuerzo manual.
