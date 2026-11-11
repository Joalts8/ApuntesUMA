---
name: odd-feature
description: Ejecuta features estándar por el Carril Amarillo (Feature Lean) de ODD. Gestiona hasta 5 preguntas de clarificación, consulta la memoria técnica de decisiones, orquesta la redacción del documento unificado feature.md mediante odd-writer, implementa mediante TDD con resiliencia 3+1 e inicia la validación.
---

# Skill `/odd-feature`: Carril Amarillo (Feature Lean)

## Propósito
Desarrollar funcionalidades completas y estándar (50 a 500 líneas) con trazabilidad integral pero sin la dispersión burocrática de múltiples archivos especificación/plan/tareas.

---

## Procedimiento Operativo

### Paso 1: Clarificación Controlada (Máximo 5 Preguntas)
1. Analiza el ticket o solicitud.
2. Si existen ambigüedades funcionales críticas, formula un **máximo de 5 preguntas concisas** de opción múltiple o respuesta directa.
3. Si el alcance o requisitos superan claramente las 500 líneas o implican breaking changes, recomienda al usuario desviar al **Carril Rojo (Epic)**.

### Paso 2: Consulta de la Memoria de Decisiones
Antes de diseñar la solución técnica:
1. **1er Nivel:** Revisa `docs/approved-decisions.md`. Las decisiones existentes se aplican automáticamente (PROHIBIDO volver a registrarlas en la feature).
2. **2º Nivel:** Revisa `docs/` en busca de decisiones no revisadas en features previas relacionadas. Si existen, reutilízalas para mantener coherencia.
3. **3er Nivel (Decisiones inéditas):**
   - Menores/Locales: Se registran en `docs/NNN-nombre/decisions.md` utilizando `.agents/skills/odd-feature/assets/decisions.md`.
   - Críticas/Bloqueantes: Pausa la fábrica, solicita aprobación del usuario y registra tras el visto bueno.

### Paso 3: Documento Unificado (`feature.md`)
Delega en el subagente `odd-writer` la creación del documento unificado:
`features/NNN-nombre/feature.md`
siguiendo la plantilla base de `.agents/skills/odd-feature/assets/feature.md`.
- Vincula de forma estricta: cada Criterio de Aceptación EARS (`RF-XX`) debe mapearse con un Enfoque Técnico (`P-XX`) y tareas atómicas en cascada (`T-XX`).

### Paso 4: Implementación Guiada por Pruebas (TDD)
1. Procesa las tareas `T-XX` de forma secuencial:
   - **Rojo:** Test unitario o de integración fallando.
   - **Verde:** Código mínimo de producción para pasar el test.
   - **Refactor:** Limpieza y eliminación de duplicidades.
2. **Límite de Resiliencia (3+1):** Si el subagente implementador falla 3 veces consecutivas en una tarea, en el 4º intento debe detenerse, diagnosticar y notificar al agente principal o usuario.
3. Marca las tareas completadas en `feature.md` (`- [X]`).

### Paso 5: Traspaso a Validación
Una vez completadas todas las tareas, invoca automáticamente `/odd-validate`.
