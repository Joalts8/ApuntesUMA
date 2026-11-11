# Directrices del Agente para Organic-Driven Development (ODD)

Este documento define el protocolo operativo para cualquier agente de IA o fábrica de software autónoma que trabaje sobre este proyecto utilizando la metodología **Organic-Driven Development (ODD)**.

---

## 1. Principios Operativos Fundamentales

1. **Eficiencia y Cero Burocracia:** No crees documentos ni estructuras complejas cuando una tarea puede resolverse con código limpio, un test y un registro ágil.
2. **Desarrollo Guiado por Pruebas (TDD Obligatorio):**
   - Escribe siempre el test unitario o de integración antes del código de producción (**Fase Roja**).
   - Implementa el código estrictamente necesario para que pase (**Fase Verde**).
   - Refactoriza para mantener legibilidad y calidad (**Fase Refactor**).
3. **Resiliencia y Límite de Fallos (Regla 3+1):**
   - Si un agente o subagente falla al implementar o pasar tests consecutivamente, se le permiten hasta **3 intentos de corrección autónoma**.
   - Si tras el **4º intento** el test continúa fallando, debe detenerse inmediatamente, diagnosticar la causa raíz y notificar al agente principal o usuario solicitando asistencia.
4. **Economía de Tokens y Especialización:**
   - La redacción y persistencia de documentación en Markdown (`feature.md`, logs de `docs/fast/`, `summary.md`, `repair.md` y `qa-note.md`) **debe delegarse al subagente `odd-writer`** (`model: flash`, `effort: low`).
   - El agente principal debe reservar su razonamiento para análisis arquitectónico, diseño de interfaces y lógica de negocio.

---

## 2. Clasificación Automática de Carriles

Ante cualquier ticket (Jira, GitHub Issues, Linear) o prompt del usuario, evalúa el impacto y selecciona el carril:

| Criterio | Carril Verde (Fast-Track) | Carril Amarillo (Feature Lean) | Carril Rojo (Epic / Arquitectura) |
| :--- | :--- | :--- | :--- |
| **Líneas de código** | < 50 líneas | 50 - 500 líneas | > 500 líneas o transversal |
| **Alcance** | Hotfix, chore, typo, ajuste menor | Feature estándar, nueva API, UI | Cambio de paradigma, nueva BD, breaking changes |
| **Clarificación** | 0 preguntas | Máximo 5 preguntas clave | Hasta 10 preguntas exhaustivas |
| **Documento** | `docs/fast/<YYYYMMDD>-<tipo>-<nombre>.md` | `features/NNN-nombre/feature.md` unificado | Separación formal `spec.md`, `plan.md`, `tasks.md` + ADR |
| **QA / Validación** | Solo test unitario verde | `summary.md` + `qa-note.md` | `summary.md` + `qa-note.md` exhaustivo + ADR |

---

## 3. Árbol de Búsqueda y Memoria de Decisiones

Antes de plantear una solución técnica o tomar una decisión de diseño:

```
Surge una decisión técnica en el desarrollo
                 │
                 ▼
¿Existe criterio en docs/approved-decisions.md?
   ├── SÍ ──► Aplica la decisión inmediatamente.
   │          (PROHIBIDO volver a registrarla en decisions.md).
   │
   └── NO ──► ¿Existe en docs/NNN-feature/decisions.md no revisadas?
                ├── SÍ ──► Reutiliza el criterio previo para mantener coherencia.
                │          (No duplicar el registro).
                │
                └── NO ──► Decisión inédita:
                             ├── Menor/Local: Aplica mejor práctica y registra en docs/NNN/decisions.md.
                             └── Crítica/Arquitectónica: Detén la fábrica, consulta al usuario y registra tras aprobación.
```

---

## 4. Gestión de Tareas Relacionadas con Features Previas

* **Feature previa en estado ABIERTA:** Invoca `/odd-clarify` para actualizar su `feature.md`. Aplica impacto en cascada sobre los Requisitos Funcionales (`RF-XX`), Enfoque (`P-XX`) y Tareas (`T-XX`) sin modificar tareas ya marcadas como completadas `[X]`.
* **Feature previa en estado CERRADA:**
  - Si es un ajuste menor (< 50 líneas): Tramítalo por **Carril Verde**, indicando en el log de `docs/fast/`: `Relacionado con: Feature NNN`.
  - Si es una funcionalidad nueva: Tramítalo por **Carril Amarillo**, creando una nueva carpeta secuencial e indicando en `feature.md`: `Antecedentes: Feature NNN`. Consulta las decisiones tomadas en dicha feature antecedente.

---

## 5. Protocolo de Validación y Reparación (`repair.md`)

Al concluir la implementación en Carril Amarillo o Rojo, ejecuta `/odd-validate`:
1. **Si todos los tests y linters pasan (Éxito):**
   - Delega a `odd-writer` la redacción de `docs/NNN-nombre/summary.md` y `docs/NNN-nombre/qa-note.md`.
   - Marca la feature como `Completada`.
2. **Si se detectan fallos o incumplimientos (Fallo):**
   - Delega a `odd-writer` la generación de `docs/NNN-nombre/repair.md` con los fallos exactos y tareas `R-XX`.
   - Cambia el estado en `feature.md` a `En reparación`.
   - Ejecuta las correcciones mediante TDD respetando el límite 3+1.
   - Una vez en verde, actualiza `summary.md`, **elimina `repair.md`** y re-valida para emitir la `qa-note.md` definitiva.
