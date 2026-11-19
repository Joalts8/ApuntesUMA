---
name: odd-feature
description: Ejecuta features estándar por el Carril Amarillo (Feature Lean) de ODD. Recibe el issue o prompt, clarifica, redacta y revisa feature.md (puerta con odd-reviewer o con el usuario), implementa con TDD lanzando un subagente por tarea con commits y resiliencia 3+1, y traspasa a validación.
---

# Skill `/odd-feature`: Carril Amarillo (Feature Lean)

## Propósito
Desarrollar funcionalidades estándar (50 a 500 líneas) con trazabilidad completa en un único documento y sin burocracia de tres archivos.

---

## Procedimiento Operativo

### Paso 1: Clarificación Controlada (Máximo 5 Preguntas)
1. Analiza el issue o solicitud y extrae descripción, requisitos, historias de usuario y criterios de aceptación.
2. Si hay ambigüedades funcionales críticas, formula **máximo 5 preguntas** concisas. En modo `auto`, no preguntes: aplica el default más razonable y regístralo como decisión menor.
3. Si el alcance supera las 500 líneas o implica breaking changes, detente y propón **Carril Rojo**.

### Paso 2: Consulta de la Memoria de Decisiones
1. **Nivel 1:** `docs/approved-decisions.md` (si no existe, inicialízalo desde `.agents/skills/odd-decisions-review/assets/approved-decisions.md`). Se aplica sin preguntar y **no se duplica** en la feature.
2. **Nivel 2:** decisiones no consolidadas de `docs/NNN/decisions.md` de features previas.
3. **Nivel 3:** inéditas menores → `docs/NNN-nombre/decisions.md`, desde `.agents/skills/odd-feature/assets/decisions.md`; críticas → para y escala (chat o `Estado: Bloqueada`).

### Paso 3: Documento Unificado `feature.md`
Delega en `odd-writer` la creación de `features/NNN-nombre/feature.md` desde `.agents/skills/odd-feature/assets/feature.md`, con trazabilidad estricta:
* Cada Historia de Usuario (`HU-XX`) se desglosa en uno o más requisitos.
* Cada `RF-XX` (EARS) se mapea a un enfoque `P-XX` y a una o más tareas `T-XX`.
* `Estado: Propuesta`.

### Paso 4: Puerta de Revisión (Obligatoria)
* **Modo `gated`:** muestra el resumen del documento y espera el OK explícito del usuario.
* **Modo `auto`:** invoca a `odd-reviewer` (modelo distinto al implementador) y aplica su dictamen:
  * `APROBADO` → continúa.
  * `REQUIERE AJUSTES` → corrige y repite el dictamen (límite 3+1; al 4º, `Estado: Bloqueada` con el diagnóstico en la sección 6).
  * `RECHAZADO` → detente y escala.
* Si el OK llega, actualiza a `Estado: Aprobada` y registra la feature en `constitution/roadmap.md` en `Siguiente`. **Sin `Aprobada` no se escribe código ni tests.**

### Paso 5: Arranque de Implementación
1. Mueve la feature de `Siguiente` a `En curso` en `constitution/roadmap.md`.
2. Actualiza `feature.md` a `Estado: En curso`.

### Paso 6: Implementación TDD con Subagentes
1. Identifica las tareas `T-XX` ejecutables y su orden de dependencia. Las de validación final se reservan para `/odd-validate`.
2. Lanza **un subagente por tarea principal**: en paralelo las independientes, en secuencia las bloqueadas. Cada subagente recibe:
   * La regla **TDD** (Rojo → Verde → Refactor) y la **prohibición de invocar otros subagentes**.
   * La memoria de decisiones (Nivel 1 y 2 ya resueltas; las inéditas locales se registran).
   * La regla **3+1**: a los 3 fallos consecutivos de tests, se detiene y reporta qué falla, qué intentó y si necesita al orquestador o al usuario.
   * La prohibición de verificar criterios de aceptación: eso es de `/odd-validate`.
3. Al volver en verde una tarea: márcala `- [X]` en `feature.md` y haz **commit** (`feat(...): tarea T-XX de feature NNN`).
4. Si un subagente queda bloqueado, espera a que se resuelva su dependencia antes de lanzar el siguiente.

### Paso 7: Traspaso a Validación
Con todas las tareas en verde, invoca `/odd-validate`. No dejes tareas a medias: si quedan, informa y no cierres la feature.
