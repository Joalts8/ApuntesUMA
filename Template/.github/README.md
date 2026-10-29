# Guía de Plantillas y Flujos de GitHub

Este directorio contiene la configuración de GitHub del repositorio: plantillas estandarizadas para Pull Requests e Issues, enlaces de contacto y flujos de automatización para GitHub Web, GitHub CLI (`gh`), GitHub Actions y asistentes de IA.

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
│   └── issue-triage.yml           # GitHub Action: bot de triaje automático y etiquetado
├── pull_request_template.md       # Plantilla predeterminada para Pull Requests
└── README.md                      # Esta guía de uso y mantenimiento
```

---

## 1. Cómo personalizar o añadir plantillas

### Para Pull Requests:
Edita directamente [`pull_request_template.md`](pull_request_template.md). GitHub utiliza automáticamente este archivo como descripción inicial cuando cualquier colaborador abre un nuevo PR en el repositorio.

### Para Issues:
Crea o edita archivos Markdown dentro de [`ISSUE_TEMPLATE/`](ISSUE_TEMPLATE/). 
Para que GitHub Web y GitHub CLI muestren el selector con título, descripción y etiquetas preasignadas, cada plantilla debe incluir cabeceras frontmatter en YAML al inicio:

```markdown
---
name: Nombre descriptivo (ej. Bug report)
about: Breve descripción del propósito de la plantilla
title: "[Bug]: "
labels: bug
assignees: ''
---

[Cuerpo de la plantilla]
```

### Configuración adicional (`config.yml`):
En [`ISSUE_TEMPLATE/config.yml`](ISSUE_TEMPLATE/config.yml) se define:
- `blank_issues_enabled: false`: Obliga a seleccionar una plantilla estructurada y evita issues vacíos.
- `contact_links`: Enlaces directos a reportes de seguridad privados (`SECURITY.md`) y foros de discusión (`GitHub Discussions`).

---

## 2. Cómo usar las plantillas

### Desde la Web de GitHub
- **Pull Requests:** Al pulsar en *New Pull Request*, GitHub autocompleta la descripción con el contenido de `pull_request_template.md`.
- **Issues:** Al pulsar en *New Issue*, GitHub muestra el selector con las opciones configuradas (`Bug report`, `Feature request` o enlaces de soporte).

### Desde GitHub CLI (`gh`)
- **Crear PR:**
  ```bash
  # Abre el editor predeterminado con la plantilla precargada:
  gh pr create
  ```
- **Crear Issue:**
  ```bash
  # Permite elegir interactivamente la plantilla deseada:
  gh issue create
  ```

### Desde Asistentes de IA y Agentes (Reglas en `AGENTS.md`)
Los agentes de IA que operan en este proyecto (o mediante herramientas MCP) siguen las siguientes directrices al interactuar con `.github/`:
- **Pull Requests:** Leen `pull_request_template.md` y rellenan obligatoriamente todas las secciones sin dejar etiquetas `<...>` vacías.
- **Issues:** Utilizan el formato correspondiente de `ISSUE_TEMPLATE/` respetando siempre los prefijos en el título (`[Bug]: `, `[Feature]: `, `[Chore]: ` o `[Docs]: `).

---

## 3. Automatizaciones (GitHub Actions)

El repositorio incluye el flujo [`workflows/issue-triage.yml`](workflows/issue-triage.yml) que se ejecuta al abrir o editar un issue:
- **Etiquetado automático:** Asigna etiquetas como `bug`, `enhancement`, `chore`, `documentation` y `needs-triage` según el prefijo del título.
- **Validación de completitud:** Comprueba que no se hayan dejado secciones obligatorias vacías ni con los textos de ejemplo `<...>`.
- **Comentarios y advertencias:** Alerta amablemente al autor si faltan pasos para reproducir o detalles requeridos para agilizar el triaje.