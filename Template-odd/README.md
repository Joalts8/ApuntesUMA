# Plantilla Organic-Driven Development (ODD) con Agentes

> Plantilla ligera y adaptativa basada en **Organic-Driven Development (ODD)**: elimina la burocracia documental innecesaria, sustituye los artefactos pesados por trazabilidad en cascada y clasifica cualquier cambio en 3 carriles de flujo continuo (Verde, Amarillo y Rojo).

---

## 1. Filosofía ODD vs SDD Tradicional

En el desarrollo tradicional dirigido por especificaciones (**SDD**, disponible en [`Template-sdd`](../Template-sdd)):
* Toda tarea, grande o pequeña, pasa por la separación estricta en 4 documentos: `spec.md`, `plan.md`, `tasks.md` y `qa-note.md`.
* Esto aporta máxima gobernanza en sistemas críticos, pero genera sobrecarga y fricción ("burocracia de tokens") en correcciones pequeñas, refactors o features estándar.

En **Organic-Driven Development (ODD)**:
1. **Clasificación Automática por Carriles:** El sistema analiza el ticket o prompt y asigna el carril adecuado (Fast-Track, Feature Lean o Epic).
2. **Cero Burocracia en Tareas Menores (Carril Verde):** Bugs y ajustes (<50 líneas) se resuelven directamente con TDD y un registro ágil en `docs/fast/`.
3. **Documento Unificado y Trazabilidad en Cascada (Carril Amarillo):** Las features habituales combinan spec, plan y tareas en un único archivo (`feature.md`), donde cada requisito funcional genera directamente su enfoque técnico y sus tareas (`RF-XX` → `P-XX` → `T-XX`).
4. **Resiliencia Automática y Subagentes de Bajo Consumo:** 
   - La redacción pesada de documentación se delega en subagentes ultra-ligeros (`odd-writer`) que usan modelos Flash en esfuerzo bajo (`effort: low`) para no malgastar cuota de razonamiento.
   - Si la validación falla, se activa automáticamente el bucle de reparación (`repair.md`) con límite de reintentos 3+1.
5. **Memoria de Decisiones Evolutiva:** Reutilización automática de decisiones aprobadas globales y de features previas relacionadas.

---

## 2. Reutilización desde `Template-sdd` (Sin Redundancias)

Para evitar duplicar código y configuraciones estándar en el repositorio, **`Template-odd` no incluye archivos genéricos ni carpetas vacías**. Al inicializar un proyecto real basado en ODD, debes reutilizar de [`Template-sdd`](../Template-sdd) los siguientes componentes:

| Componente en `Template-sdd` | Propósito y Reutilización |
| :--- | :--- |
| **`.github/workflows/`** | Copiar `security.yml` (DevSecOps con Trivy, TruffleHog, Semgrep y Strix) e `issue-triage.yml`. Configurar `ci.yml` y `cd.yml` según la guía. |
| **`.github/dependabot.yml`** | Configuración de actualizaciones automáticas de dependencias de Git y Actions. |
| **`.github/ISSUE_TEMPLATE/`** | Plantillas para bugs, features y tareas técnicas (compatibles 100% con los carriles ODD). |
| **`.github/pull_request_template.md`** | Plantilla de Pull Requests estandarizada. |
| **`docker/Dockerfile`** | Dockerfile de referencia multi-etapa y hardening para producción. |
| **Skill `/sdd-constitution`** | Reutilizada de `Template-sdd` para inicializar la constitución (`mission.md`, `tech-stack.md` y `roadmap.md`) al arrancar el proyecto. |
| **Skill `/adr`** | Reutilizada de `Template-sdd` para registrar formalmente decisiones arquitectónicas (`docs/ADR/`) en el Carril Rojo. |
| **Skill `/sdd-status`** | Reutilizada de `Template-sdd` para consultar el estado global de features y fases del proyecto. |
| **Skills SDD Clásicas (`/sdd-spec`, `/sdd-plan`, `/sdd-task`, `/sdd-implement`, `/sdd-rev`)** | Se usan **exactamente igual y sin cambios** respecto a `Template-sdd`, únicamente cuando un Epic del Carril Rojo se aborda de forma monolítica con separación formal de archivos en lugar de descomposición orgánica. |
| **Archivos Raíz** | `.gitignore`, `.env.example`, `LICENSE`, `SECURITY.md`, `CONTRIBUTING.md` y `CODE_OF_CONDUCT.md`. |

---

## 3. Estructura de un Proyecto ODD en Producción

Cuando un proyecto ODD está en funcionamiento, su estructura de directorios queda conformada de la siguiente manera:

```text
<raíz-del-proyecto>/
├── .github/                 ← [Reutilizado de Template-sdd] Workflows, plantillas de issues y PRs
├── .agents/                 ← Configuración de agentes ODD
│   ├── agents/              ← Subagentes especializados de bajo consumo
│   │   ├── odd-writer.md    ← Redactor Flash (effort: low) para feature.md, summary.md, repair.md y qa-note.md
│   │   └── odd-reviewer.md  ← Auditor de alineación con constitución y memoria técnica
│   └── skills/              ← Habilidades ODD (odd, odd-fast, odd-feature, odd-clarify, odd-validate, odd-decisions-review)
├── constitution/            ← Reglas estables del proyecto (mission.md, tech-stack.md, roadmap.md)
├── docs/                    ← Documentación viva y memoria técnica
│   ├── approved-decisions.md ← Catálogo global de decisiones consolidadas
│   ├── fast/                ← Bitácora ágil del Carril Verde (YYYYMMDD-<tipo>-<nombre>.md)
│   ├── ADR/                 ← Architectural Decision Records (solo para decisiones críticas del Carril Rojo)
│   └── NNN-nombre-feature/  ← Entregables de feature: decisions.md, summary.md, qa-note.md
├── features/                ← Especificaciones activas
│   └── NNN-nombre-feature/  ← Carpeta de feature con su documento unificado 'feature.md'
└── src/                     ← Código fuente del proyecto
```

---

## 4. Los 3 Carriles de Trabajo

```
Ticket / Petición del Usuario
              │
              ▼
   ¿Qué tipo de trabajo es?
              │
  ┌───────────┼───────────┐
  ▼           ▼           ▼
VERDE     AMARILLO      ROJO
(Fast)     (Lean)      (Epic)
```

### 🟢 Carril Verde: Fast-Track (Hotfixes, Chores, Ajustes < 50 líneas)
* **Objetivo:** Resolver rápido sin burocracia documental.
* **Proceso:**
  1. No hay preguntas de clarificación previas.
  2. Implementación directa con **TDD (Red-Green-Refactor)**: test primero, código después.
  3. Resiliencia: Límite de 3 reintentos + 1 diagnóstico antes de escalar al usuario.
  4. Documentación: Se genera un archivo conciso en `docs/fast/<YYYYMMDD>-<tipo>-<nombre>.md` mediante el subagente `odd-writer`.
  5. **Sin `qa-note.md`:** El test unitario es la garantía.

### 🟡 Carril Amarillo: Feature Lean (Funcionalidades Típicas / Estándar)
* **Objetivo:** Trazabilidad completa sin fragmentar en múltiples archivos.
* **Proceso:**
  1. **Documento Único (`features/NNN-nombre/feature.md`):** Combina Contexto, Requisitos EARS (`RF-XX`), Enfoque Técnico (`P-XX`) y Tareas atómicas (`T-XX`).
  2. **Preguntas de Clarificación:** Máximo 5 preguntas clave antes de implementar.
  3. **Consulta de Memoria de Decisiones:**
     - Nivel 1: `docs/approved-decisions.md` (no se vuelve a registrar).
     - Nivel 2: `docs/NNN/decisions.md` de features anteriores relacionadas.
     - Nivel 3: Decisiones nuevas se registran en `docs/NNN-nombre/decisions.md`.
  4. **Implementación TDD:** Desarrollo iterativo tarea a tarea.
  5. **Entregables:** `docs/NNN-nombre/summary.md` y `docs/NNN-nombre/qa-note.md` (redactados por `odd-writer`).

### 🔴 Carril Rojo: Epic / Cambio Arquitectónico Complejo
* **Objetivo:** Máxima seguridad y control en cambios troncales, breaking changes o migraciones de alto impacto (> 500 líneas).
* **Gestión de la Arquitectura:** Se formaliza obligatoriamente mediante un **Architectural Decision Record** en `docs/ADR/NN-titulo.md` (utilizando la skill `/adr` de `Template-sdd`).
* **¿Cómo se implementa en ODD?**
  Existen dos estrategias complementarias según la preferencia del equipo:
  1. **Descomposición Orgánica (Recomendada en ODD):** El cambio grande se define a alto nivel en el ADR y se descompone en **N features estándar (Carril Amarillo)**. Cada feature se ejecuta de forma independiente con su `feature.md`, TDD y trazabilidad en cascada, reduciendo el riesgo de cambios gigantes.
  2. **Ejecución Formal SDD:** Si el cambio debe entregarse como un bloque indivisible y requiere separación documental estricta, se ejecutan las skills especializadas de `Template-sdd` (`/sdd-spec`, `/sdd-plan`, `/sdd-task`, `/sdd-implement`, `/sdd-validate`).

---

## 5. Compatibilidad Dual: Usuario Interactivo vs Fábrica Autónoma

ODD está diseñado para operar con la misma lógica tanto si lo pilota un desarrollador en su editor como si corre en una fábrica de software autónoma (CI/CD nocturno o workers desatendidos):

| Aspecto | Modo Usuario Interactivo | Modo Fábrica Autónoma (Desatendido) |
| :--- | :--- | :--- |
| **Entrada de trabajo** | Prompt libre en chat o comando `/odd` | Ticket de Jira / GitHub Issue / Linear leído por API |
| **Clarificación** | Pregunta hasta 5 dudas al usuario en chat | Asume valores predeterminados (defaults) y convenciones globales |
| **Decisiones Nivel 1 y 2** | Se aplican en automático sin preguntar | Se aplican en automático sin preguntar |
| **Decisiones Nivel 3 (menores)** | Propone al usuario y registra en `decisions.md` | Aplica la mejor práctica y registra en `decisions.md` para auditoría |
| **Decisiones Nivel 3 (críticas)** | Consulta al usuario antes de proceder | **Pausa la fábrica y emite alerta bloqueante** para revisión humana |
| **Resiliencia en Tests (3+1)** | Pide ayuda al usuario si falla 4 veces | **Detiene el worker y emite diagnóstico** con traza del error |
| **Validación y Reparación** | Avisa al usuario del progreso y emite QA | Bucle automático: `repair.md` → TDD → clean → commit |

---

## 6. Automatización de Flujos Especiales

### A) Evolutivos sobre Features Existentes
¿Qué ocurre cuando una nueva tarea está relacionada con una feature ya construida?
* **Si la feature aún está ABIERTA:** Se ejecuta `/odd-clarify` sobre su `feature.md`. Se añade el nuevo requisito funcional y la cascada genera automáticamente las tareas adicionales sin reiniciar ni tocar lo ya completado.
* **Si la feature ya está CERRADA:**
  - *Ajuste menor (< 50 líneas):* Va por **Carril Verde (Fast-Track)**. Se documenta en `docs/fast/` indicando `Relacionado con: Feature NNN`.
  - *Nueva funcionalidad relevante:* Va por **Carril Amarillo (Feature Lean)**. Se crea una nueva carpeta `features/MMM-nueva-feature/` indicando `Antecedentes: Feature NNN`. El agente lee automáticamente `docs/NNN/decisions.md` para mantener los mismos patrones técnicos.
  - *Breaking change o refactor troncal:* Va por **Carril Rojo (Epic)** con ADR.

### B) Ciclo Automático de Reparación (`repair.md`)
Si la validación de una feature falla (tests rotos, linter, criterios de aceptación insatisfechos):
1. **Generación de `repair.md`:** El validador crea automáticamente `docs/NNN-nombre/repair.md` indicando:
   - Tests o criterios fallidos.
   - Causa raíz diagnosticada.
   - Tareas correctivas (`R-01`, `R-02`...).
2. **Cambio de Estado:** En `feature.md`, el estado pasa a `En reparación`.
3. **Corrección TDD (Límite 3+1):** El agente implementador ejecuta las tareas de reparación con límite de 3 reintentos automáticos.
4. **Cierre Limpio:** Al superar todos los tests en verde, se actualiza `summary.md`, se elimina `repair.md` (no se deja basura transitoria) y se emite la `qa-note.md` definitiva.

### C) Subagentes Redactores de Bajo Consumo (`odd-writer`)
Para evitar el consumo excesivo de tokens y preservar la ventana de contexto del agente principal:
* La redacción y actualización de archivos Markdown (`feature.md`, `summary.md`, `repair.md`, `qa-note.md`, y bitácoras en `docs/fast/`) se delega en `.agents/agents/odd-writer.md`.
* Corre con el modelo `flash` y parámetro `effort: low`, optimizando costes y garantizando rapidez.

---

## 7. Comandos y Skills ODD Disponibles

| Skill / Comando | Ubicación | Descripción |
| :--- | :--- | :--- |
| **`/odd`** | `.agents/skills/odd/` | Orquestador principal: recibe el ticket/prompt, clasifica el carril y arranca el flujo. |
| **`/odd-fast`** | `.agents/skills/odd-fast/` | Ejecuta el Carril Verde para fixes inmediatos con TDD y log en `docs/fast/`. |
| **`/odd-feature`** | `.agents/skills/odd-feature/` | Ejecuta el Carril Amarillo: genera `feature.md`, TDD, `summary.md` y `qa-note.md`. |
| **`/odd-clarify`** | `.agents/skills/odd-clarify/` | Clarifica o amplía una feature abierta aplicando impacto en cascada. |
| **`/odd-validate`** | `.agents/skills/odd-validate/` | Ejecuta suite de validación; emite `qa-note.md` si pasa, o inicia bucle `repair.md` si falla. |
| **`/odd-decisions-review`** | `.agents/skills/odd-decisions-review/` | Consolida periódicamente decisiones de features en `docs/approved-decisions.md`. |
| **`/adr`** | `Template-sdd/.agents/skills/adr/` | [Reutilizada de SDD] Genera un Architectural Decision Record para cambios del Carril Rojo. |
