---
name: sdd-rev
description: Revisa el código del proyecto, constitution/, features/ y docs/ para comprobar alineación con la misión, tech-stack y que todo esté debidamente registrado.
---

# Revisión de Consistencia SDD (sdd-rev)

## Objetivo
Auditar y verificar que todo el código fuente implementado, las especificaciones en `features/` y la documentación generada en `docs/` estén sincronizados, sean fieles a la constitución del proyecto (`mission.md`, `tech-stack.md` y `roadmap.md`), trazables y debidamente registrados según la metodología Spec-Driven Development (SDD).

## Entradas
- Opcionalmente: identificador o nombre de una feature específica, o bien revisión global del proyecto.
- Directorio `constitution/`:
  - `mission.md` (objetivo del producto, principios rectores y qué NO es).
  - `tech-stack.md` (tecnologías oficiales, arquitectura, convenciones y límites duros).
  - `roadmap.md` (estado de features y métricas de cobertura).
- Archivo `AGENTS.md` (estándares, convenciones de commit y flujos de trabajo).
- Código fuente en `src/` (y tests asociados).
- Directorio de features: `features/NNN-nombre-feature/` (`spec.md`, `plan.md`, `tasks.md`).
- Directorio de documentación: `docs/NNN-nombre-feature/` (`decisions.md`, `NN-task.md`, `qa-note.md`, `repair.md` si aplica), catálogo global `docs/approved-decisions.md` y `docs/ADR/`.

## Salidas
- Informe detallado de revisión en el chat indicando:
   - Alineación con `mission.md` y `tech-stack.md` (ausencia de desvíos de alcance o violaciones técnicas).
   - Estado de trazabilidad (Código ↔ Tests ↔ Tareas ↔ Specs).
   - Tareas o funcionalidades implementadas en el código no registradas en `features/` o `docs/`.
   - Specs o tareas marcadas como hechas sin documentación correspondiente en `docs/NNN-nombre-feature/`.
   - Documentación de tareas (`NN-task.md`) faltante o desactualizada respecto al código implementado.
   - Veredicto final: `Conforme` o `Discrepancias detectadas` (con lista de acciones correctoras necesarias).

## Proceso
1. **Verificación de Alineación Constitucional (`constitution/`):**
   - **Misión y Alcance (`mission.md`):** Comprueba que las specs y funcionalidades implementadas encajen con la razón de ser del proyecto, respeten los principios rectores y no invadan el apartado "Qué NO es" (evitando feature creep).
   - **Tech Stack y Convenciones (`tech-stack.md`):** Verifica que el código implementado y las dependencias respeten las tecnologías aprobadas, convenciones de nombres, estructura de módulos y, especialmente, no vulneren ningún "Límite duro" (zonas congeladas, antipatrones o dependencias no autorizadas).

2. **Inspección de Features y Roadmap:**
   - Comprueba cada carpeta en `features/` contra `constitution/roadmap.md`.
   - Verifica que cada feature contenga `spec.md`, `plan.md` y `tasks.md`.
   - Revisa si el estado de las tareas en `tasks.md` (completadas vs pendientes) coincide con el estado reflejado en el roadmap.

3. **Revisión de Documentación (`docs/`):**
   - Si la feature tiene `docs/NNN-nombre-feature/decisions.md`, comprueba que registre las nuevas decisiones adoptadas y su estado de revisión periódica. Su ausencia no es un fallo si no se tomaron decisiones nuevas.
   - Para cada tarea completada en `tasks.md`, comprueba que exista su archivo resumen en `docs/NNN-nombre-feature/NN-task.md`.
   - Comprueba que los resúmenes en `docs/` contengan las decisiones de diseño, problemas y soluciones implementadas.
   - Si la feature figura como validada o finalizada, verifica la existencia y completitud de `docs/NNN-nombre-feature/qa-note.md`.
   - Si existe `docs/NNN-nombre-feature/repair.md`, advierte de que la feature tiene fallos pendientes de subsanar o no se ha limpiado tras la reimplementación.

4. **Revisión de Código (`src/`) y Tests:**
   - Analiza el código fuente y las pruebas existentes.
   - Detecta si hay clases, endpoints, componentes o lógica implementada que no esté referenciada en ninguna spec o tarea.
   - Verifica que los tests cubran los requisitos y que las métricas de cobertura coincidan con lo reportado en `constitution/roadmap.md`.
   - Detecta si existen ADRs requeridos por decisiones técnicas de arquitectura que no hayan sido creados en `docs/ADR/`.

5. **Emisión del Informe:**
   - Presenta un balance claro con los hallazgos:
      - Grado de cumplimiento constitucional (`mission.md` y `tech-stack.md`).
      - Elementos conformes.
      - Elementos no registrados o huérfanos (código sin spec/tarea, tareas sin docs).
      - Desalineaciones con `roadmap.md`.
   - Proporciona las acciones sugeridas para corregir las discrepancias antes de dar por cerrada la fase o el ciclo.

## Reglas
- No modifiques archivos automáticamente durante la revisión; solo informa y audita.
- Usa la skill `sdd`.
- Ninguna feature o código puede contravenir `mission.md` ni violar los límites duros de `tech-stack.md`.
- Todo código debe originarse en una spec (`features/NNN-nombre-feature/spec.md`) y estar desglosado en `plan.md` y `tasks.md`.
- Toda tarea completada debe tener su correspondiente resumen en `docs/NNN-nombre-feature/NN-task.md`.
