# Guía de Plantillas y Flujos de GitHub

Este directorio contiene la configuración de GitHub del repositorio: plantillas estandarizadas para Pull Requests e Issues, enlaces de contacto y flujos de automatización para GitHub Web, GitHub CLI (`gh`), GitHub Actions y asistentes de IA.

---

## Insignias de Estado (Badges de Ejemplo)

> [!NOTE]
> Al encontrarse esta carpeta dentro de `Template/.github` (no en la raíz del repositorio), los workflows no se activan de forma automática. Cuando copies o traslades esta plantilla a la raíz de tu proyecto final, sustituye `<OWNER>` y `<REPO>` por tu usuario/organización y nombre de repositorio en GitHub, y `<DOCKERHUB_USER>` por tu usuario de Docker Hub.

### Badges estándar de GitHub Actions:
```markdown
[![DevSecOps & Security Analysis](https://github.com/<OWNER>/<REPO>/actions/workflows/security.yml/badge.svg)](https://github.com/<OWNER>/<REPO>/actions/workflows/security.yml)
[![Issue Triage Bot](https://github.com/<OWNER>/<REPO>/actions/workflows/issue-triage.yml/badge.svg)](https://github.com/<OWNER>/<REPO>/actions/workflows/issue-triage.yml)
<!-- Si configuras CI y CD siguiendo las secciones dedicadas de esta guía: -->
[![CI - Integración Continua](https://github.com/<OWNER>/<REPO>/actions/workflows/ci.yml/badge.svg)](https://github.com/<OWNER>/<REPO>/actions/workflows/ci.yml)
[![CD - Despliegue Continuo](https://github.com/<OWNER>/<REPO>/actions/workflows/cd.yml/badge.svg)](https://github.com/<OWNER>/<REPO>/actions/workflows/cd.yml)
```

### Badges dinámicos con Shields.io:
```markdown
<!-- Estado de flujos -->
[![Security Scan](https://img.shields.io/github/actions/workflow/status/<OWNER>/<REPO>/security.yml?label=DevSecOps&logo=githubactions)](https://github.com/<OWNER>/<REPO>/actions/workflows/security.yml)
[![CI Status](https://img.shields.io/github/actions/workflow/status/<OWNER>/<REPO>/ci.yml?branch=main&label=CI&logo=githubactions)](https://github.com/<OWNER>/<REPO>/actions/workflows/ci.yml)
[![CD Status](https://img.shields.io/github/actions/workflow/status/<OWNER>/<REPO>/cd.yml?label=CD%20Deploy&logo=docker)](https://github.com/<OWNER>/<REPO>/actions/workflows/cd.yml)

<!-- Imágenes en Registries -->
[![GHCR Version](https://img.shields.io/badge/GHCR-ghcr.io%2F<OWNER>%2F<REPO>-blue?logo=github)](https://github.com/<OWNER>/<REPO>/pkgs/container/<REPO>)
[![Docker Pulls](https://img.shields.io/docker/pulls/<DOCKERHUB_USER>/app?logo=docker&logoColor=white)](https://hub.docker.com/r/<DOCKERHUB_USER>/app)
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
│   ├── issue-triage.yml           # GitHub Action: bot de triaje automático, validación y etiquetado
│   └── security.yml               # GitHub Action: DevSecOps universal (TruffleHog, Semgrep, Trivy, OWASP Noir, Strix)
├── dependabot.yml                 # Configuración de Dependabot: PRs automáticos de dependencias y seguridad
├── pull_request_template.md       # Plantilla predeterminada para Pull Requests
└── readme_plantillas.md          # Esta guía de uso, mantenimiento y plantillas de CI/CD
```

> [!TIP]
> **¿Por qué `security.yml` e `issue-triage.yml` vienen incluidos directamente y CI/CD en secciones dedicadas?**  
> La seguridad estática (secretos, SAST, SCA de contenedores) y el triaje de incidencias son **universales y agnósticos al lenguaje**, por lo que funcionan de serie en cualquier proyecto. En cambio, **CI y CD dependen 100% del stack tecnológico** (compilador, gestor de paquetes, runtime, framework y entorno de despliegue). A continuación encontrarás secciones dedicadas con plantillas completas listas para copiar y activar según tu tecnología.

---

## 1. Instrucciones de uso de las Plantillas de Issues y PRs

### Plantilla de Pull Requests: [`pull_request_template.md`](pull_request_template.md)
* **Propósito:** Asegurar que ningún cambio entre a `develop` o `main` sin contexto, tests ni trazabilidad con el SDD.
* **Cómo usarla:**
  - Al abrir un PR desde GitHub Web o con `gh pr create`, el archivo se carga automáticamente en la descripción.
  - **Obligatorio rellenar:**
    1. **Tipo de cambio:** Marcar con `[x]` si es `feat`, `fix`, `docs`, `refactor`, etc.
    2. **Descripción y motivación:** Explicar el qué y el porqué del cambio.
    3. **Enlace a feature/issue:** Referenciar `Closes #NN` o la carpeta `features/NNN-nombre-feature/`.
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

## 2. Workflows Incluidos de Serie

### Triaje de Incidencias: [`workflows/issue-triage.yml`](workflows/issue-triage.yml)
Se dispara al abrir o editar issues:
- Clasifica y etiqueta automáticamente según el prefijo en el título (`bug`, `enhancement`, `chore`, `documentation`).
- **Autocreación de etiquetas:** Si el repositorio es nuevo y carece de las etiquetas requeridas (`needs-triage`, `needs-info`, `chore`, etc.), el script las crea automáticamente con colores oficiales vía API de GitHub.
- Valida que no se dejen secciones vacías o con placeholders `<...>` y añade una advertencia amistosa al autor solicitando la información faltante.

### DevSecOps & Seguridad: [`workflows/security.yml`](workflows/security.yml)
- **Disparo:** `push` en ramas de trabajo, `pull_request` a `main`/`develop`, schedule semanal y manual (`workflow_dispatch`).
- **Niveles de análisis universales:**
  1. **TruffleHog:** Escaneo profundo de secretos, tokens y claves privadas en el historial de commits.
  2. **Semgrep (SAST):** Análisis estático universal de vulnerabilidades en el código, exportando informe SARIF a la pestaña *Security* de GitHub.
  3. **Trivy (SCA):** Escaneo de CVEs en dependencias (`package.json`, `pom.xml`, `requirements.txt`, etc.) y Dockerfiles.
  4. **OWASP Noir:** Mapeo de superficie de ataque y detección de endpoints HTTP.
  5. **Strix (Pentest IA Dinámico):** Ejecución bajo demanda (`workflow_dispatch` activando `run_strix`) mediante `strix-agent` en Python 3.12 para pruebas autónomas de penetración con LLMs.

---

## 3. Sección Dedicada: Integración Continua (CI)

Un flujo de CI robusto debe cumplir 3 fases esenciales:
1. **Linting y Formato:** Garantiza estándares de estilo y sintaxis sin ejecutar tests pesados.
2. **Suite de Tests:** Ejecuta pruebas unitarias y de integración, asegurando que el ciclo Red-Green-Refactor de TDD se respete.
3. **Empaquetado (Build):** Compila y genera el artefacto distribuible (JAR, dist, binario, wheel) y lo sube como artefacto de Actions.

Para activarlo en tu proyecto, crea el archivo `.github/workflows/ci.yml` copiando la plantilla correspondiente a tu stack:

### Opción A: Node.js / TypeScript (npm / pnpm / yarn)
```yaml
name: CI - Integración Continua (Node.js)

on:
  push:
    branches:
      - '**'
      - '!main'
      - '!develop'
  pull_request:
    branches: [ main, develop ]
  workflow_dispatch:

permissions:
  contents: read

jobs:
  lint-and-typecheck:
    name: Lint & Typecheck
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-node@v4
        with:
          node-version: '20'
          cache: 'npm'
      - run: npm ci
      - run: npm run lint --if-present
      - run: npx tsc --noEmit --if-present

  test:
    name: Tests Unitarios y Cobertura
    needs: lint-and-typecheck
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-node@v4
        with:
          node-version: '20'
          cache: 'npm'
      - run: npm ci
      - run: npm test -- --coverage

  build:
    name: Compilar y Empaquetar
    needs: test
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-node@v4
        with:
          node-version: '20'
          cache: 'npm'
      - run: npm ci
      - run: npm run build --if-present
      - uses: actions/upload-artifact@v4
        with:
          name: dist-package
          path: dist/
          retention-days: 1
          if-no-files-found: ignore
```

### Opción B: Java (Maven / Gradle + Spring Boot)
```yaml
name: CI - Integración Continua (Java)

on:
  push:
    branches:
      - '**'
      - '!main'
      - '!develop'
  pull_request:
    branches: [ main, develop ]
  workflow_dispatch:

permissions:
  contents: read

jobs:
  check-and-lint:
    name: Verificación y Estilo
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          distribution: 'temurin'
          java-version: '21'
          cache: 'maven'
      - name: Dar permisos de ejecución
        run: |
          if [ -f "./mvnw" ]; then chmod +x ./mvnw; fi
          if [ -f "./gradlew" ]; then chmod +x ./gradlew; fi
      - name: Validar formato / compilación
        run: |
          if [ -f "./mvnw" ]; then ./mvnw compile -DskipTests;
          elif [ -f "./gradlew" ]; then ./gradlew check -x test;
          else mvn compile -DskipTests; fi

  test:
    name: Tests Automatizados (TDD)
    needs: check-and-lint
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          distribution: 'temurin'
          java-version: '21'
          cache: 'maven'
      - name: Dar permisos y ejecutar tests
        run: |
          if [ -f "./mvnw" ]; then chmod +x ./mvnw && ./mvnw test;
          elif [ -f "./gradlew" ]; then chmod +x ./gradlew && ./gradlew test;
          else mvn test; fi

  build:
    name: Empaquetar JAR
    needs: test
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          distribution: 'temurin'
          java-version: '21'
      - name: Generar paquete
        run: |
          if [ -f "./mvnw" ]; then chmod +x ./mvnw && ./mvnw clean package -DskipTests;
          elif [ -f "./gradlew" ]; then chmod +x ./gradlew && ./gradlew bootJar -x test;
          else mvn clean package -DskipTests; fi
      - uses: actions/upload-artifact@v4
        with:
          name: app-package
          path: |
            target/*.jar
            build/libs/*.jar
          retention-days: 1
          if-no-files-found: ignore
```

### Opción C: Python (FastAPI / Django / pytest)
```yaml
name: CI - Integración Continua (Python)

on:
  push:
    branches:
      - '**'
      - '!main'
      - '!develop'
  pull_request:
    branches: [ main, develop ]
  workflow_dispatch:

permissions:
  contents: read

jobs:
  lint:
    name: Lint & Formato (Ruff / Flake8)
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-python@v5
        with:
          python-version: '3.11'
      - run: pip install ruff
      - run: ruff check .

  test:
    name: Tests Automatizados (pytest)
    needs: lint
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-python@v5
        with:
          python-version: '3.11'
          cache: 'pip'
      - run: pip install -r requirements.txt pytest pytest-cov
      - run: pytest --cov=. --cov-report=xml
```

---

## 4. Sección Dedicada: Despliegue Continuo (CD) y Contenedores

Un pipeline de CD estandarizado automatiza la creación de imágenes multi-arquitectura y la publicación en registros seguros tras la fusión en ramas protegidas.

### Estructura recomendada para `.github/workflows/cd.yml`:

```yaml
name: CD - Despliegue Continuo (Docker Hub & GHCR)

on:
  push:
    branches:
      - main
      - develop
    tags:
      - 'v*.*.*'
  workflow_dispatch:

permissions:
  contents: read
  packages: write  # Publicación en ghcr.io

jobs:
  build-and-push:
    name: Construir y Publicar Imagen Docker
    runs-on: ubuntu-latest
    env:
      DOCKERHUB_USER: ${{ secrets.DOCKERHUB_USERNAME }}
      DOCKERHUB_PASS: ${{ secrets.DOCKERHUB_TOKEN }}
    steps:
      - name: Checkout del repositorio
        uses: actions/checkout@v4

      - name: Configurar Docker QEMU (Multi-arquitectura)
        uses: docker/setup-qemu-action@v3

      - name: Configurar Docker Buildx
        uses: docker/setup-buildx-action@v3

      # Login condicional en Docker Hub si existen secretos
      - name: Iniciar sesión en Docker Hub
        if: env.DOCKERHUB_USER != ''
        uses: docker/login-action@v3
        with:
          username: ${{ env.DOCKERHUB_USER }}
          password: ${{ env.DOCKERHUB_PASS }}

      # Login predeterminado en GitHub Container Registry (ghcr.io)
      - name: Iniciar sesión en GitHub Container Registry (ghcr.io)
        uses: docker/login-action@v3
        with:
          registry: ghcr.io
          username: ${{ github.actor }}
          password: ${{ secrets.GITHUB_TOKEN }}

      - name: Normalizar nombres de imágenes
        id: prep
        run: |
          REPO_LC="${GITHUB_REPOSITORY,,}"
          if [ -n "$DOCKERHUB_USER" ]; then
            USER_LC="${DOCKERHUB_USER,,}"
            echo "images<<EOF" >> $GITHUB_OUTPUT
            echo "ghcr.io/${REPO_LC}" >> $GITHUB_OUTPUT
            echo "${USER_LC}/app" >> $GITHUB_OUTPUT
            echo "EOF" >> $GITHUB_OUTPUT
          else
            echo "images=ghcr.io/${REPO_LC}" >> $GITHUB_OUTPUT
          fi

      - name: Extraer metadatos y etiquetas para Docker
        id: meta
        uses: docker/metadata-action@v5
        with:
          images: ${{ steps.prep.outputs.images }}
          tags: |
            type=raw,value=latest,enable=${{ github.ref == 'refs/heads/main' }}
            type=raw,value=develop,enable=${{ github.ref == 'refs/heads/develop' }}
            type=semver,pattern={{version}}
            type=sha,format=short

      - name: Construir y Publicar Imagen en Registries
        uses: docker/build-push-action@v5
        with:
          context: .
          file: ./docker/Dockerfile
          push: true
          tags: ${{ steps.meta.outputs.tags }}
          labels: ${{ steps.meta.outputs.labels }}

  deploy:
    name: Despliegue en Entorno
    needs: build-and-push
    runs-on: ubuntu-latest
    steps:
      - name: Ejecutar paso de despliegue
        run: |
          echo "Imágenes publicadas en ghcr.io y Docker Hub."
          # Personaliza aquí el despliegue a tu entorno:
          # SSH: appleboy/ssh-action@master
          # Kubernetes: azure/k8s-deploy@v5
```

---

## 5. Acciones Manuales a Realizar en GitHub Web

Al trasladar esta plantilla a un repositorio nuevo en GitHub, configura:

### 1. Secretos del Repositorio (`Settings > Secrets and variables > Actions`)
| Secreto | Requerido por | Descripción |
| :--- | :--- | :--- |
| `DOCKERHUB_USERNAME` | `cd.yml` (al crear el workflow desde la plantilla) | (Opcional) Usuario en Docker Hub. Si no se define, se publica solo en GHCR. |
| `DOCKERHUB_TOKEN` | `cd.yml` (al crear el workflow desde la plantilla) | (Opcional) Personal Access Token de Docker Hub con permisos `Read & Write`. |
| `OPENAI_API_KEY` / `ANTHROPIC_API_KEY` | `security.yml` (Strix) | (Opcional) Clave para activar el análisis dinámico de pentesting con IA. |
| `STRIX_API_KEY` | `security.yml` (Strix) | (Opcional) Clave de la plataforma Strix si usas su servicio gestionado. |

### 2. Reglas de Protección de Ramas (`Settings > Branches`)
1. Ve a **Settings > Branches** y añade una regla para `main` (y otra para `develop` si existe).
2. Activa **Require a pull request before merging**.
3. Activa **Require status checks to pass before merging** y marca como obligatorios los checks del pipeline (`Escaneo de Secretos y SAST Universal`, `SCA de Dependencias y Vulnerabilidades`).

### 3. Opciones de Seguridad (`Settings > Code security and analysis`)
1. **Secret Scanning & Push Protection:** Activa ambas opciones para bloquear intentos de push con credenciales.
2. **Private Vulnerability Reporting:** En `Security > Advisories`, habilita los reportes privados confidenciales.

---

## 6. Actualizaciones Automáticas con Dependabot

El archivo [`.github/dependabot.yml`](dependabot.yml) revisa semanalmente si existen nuevas versiones de tus dependencias y de las GitHub Actions usadas en los flujos.

* **Revisión de GitHub Actions:** Se ejecuta semanalmente para mantener las acciones de CI/CD al día.
* **Dependencias de aplicación:** Por defecto viene configurado para `maven`. Si tu proyecto utiliza `npm`, `pip`, `docker` o `cargo`, ajusta el campo `package-ecosystem` como se indica en los comentarios del archivo.
