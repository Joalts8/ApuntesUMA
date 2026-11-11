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
* **Objetivo:** Máxima seguridad y control en cambios troncales o de alta criticidad.
* **Proceso:**
  1. Separación documental formal (`spec.md`, `plan.md`, `tasks.md`).
  2. Registro de ADR obligatorio en `docs/ADR/NN-titulo.md`.
  3. Hasta 10 preguntas de clarificación exhaustivas.
  4. Revisión técnica estricta con `odd-reviewer`.

---

## 5. Automatización de Flujos Especiales

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

## 6. Comandos y Skills ODD Disponibles

| Skill / Comando | Descripción |
| :--- | :--- |
| **`/odd`** | Orquestador principal: recibe el ticket/prompt, clasifica el carril y arranca el flujo. |
| **`/odd-fast`** | Ejecuta el Carril Verde para fixes inmediatos con TDD y log en `docs/fast/`. |
| **`/odd-feature`** | Ejecuta el Carril Amarillo: genera `feature.md`, TDD, `summary.md` y `qa-note.md`. |
| **`/odd-clarify`** | Clarifica o amplía una feature abierta aplicando impacto en cascada. |
| **`/odd-validate`** | Ejecuta suite de validación; emite `qa-note.md` si pasa, o inicia bucle `repair.md` si falla. |
| **`/odd-decisions-review`** | Consolida periódicamente decisiones de features en `docs/approved-decisions.md`. |
| **`/adr`** | Genera un Architectural Decision Record formal para cambios del Carril Rojo. |
