# AGENTS.md — <proyecto>

<Qué es, en 2-3 frases. Arquitectura, tecnologías..>

## Estructura del proyecto
- `src/` — Código fuente de la aplicación
- `features/` — Carpetas de features con su documento unificado `feature.md`
- `docs/` — Documentación viva, memoria de decisiones (`approved-decisions.md`), `fast/` y entregables de feature
- `[carpeta]/` — [qué contiene]

## Comandos
Opcional si hay tech-stack junto a alguna cosa más de ese archivo.
- Ejecutar: `<comando>`
- Tests: `<comando>`
- Lint/formato: `<comando>`

## Estilo y convenciones
<Versión del lenguaje, convenciones de nombres y código, idioma del código y mensajes de commit.>
- **Código y nombres:** <Clases en PascalCase, métodos/variables en camelCase, constantes en UPPER_CASE.>
- **Mensajes de commit:** <Obligatorio seguir el estándar de **Conventional Commits** con el **mensaje en español** (ejemplos: `feat: endpoint de autenticación`, `fix: corrección de cálculo de stock`, `test: pruebas unitarias de servicio de pedidos`, `docs: actualización de decisiones`).>
- **Documentación de código:** <Comentar con formato estándar estilo **Javadoc** / **Doxygen** (o **TSDoc** / **JSDoc** en TypeScript/JavaScript) para clases, interfaces, servicios y métodos clave (`@param`, `@return`, `@throws`). En clases donde se utilicen anotaciones o decoradores (Lombok `@Data`, decoradores TypeScript, etc.), **prevalecen las anotaciones frente al boilerplate**; documentar la clase a nivel global y sus campos clave sin redundancia en getters/setters.>

## Flujo de trabajo Organic-Driven Development (ODD)

### 1. Clasificación por Carriles y Modo de Operación
* **Carril Verde (Fast-Track, < 50 líneas):** Bugs, chores o ajustes menores. TDD directo (test primero), 0 preguntas, sin `qa-note.md`. Registro ágil en `docs/fast/<YYYYMMDD>-<tipo>-<nombre>.md`.
* **Carril Amarillo (Feature Lean, 50 - 500 líneas):** Documento unificado en `features/NNN-nombre/feature.md` con trazabilidad en cascada (`RF-XX` → `P-XX` → `T-XX`). Máximo 5 preguntas de clarificación en modo interactivo (en fábrica autónoma se aplican defaults). TDD secuencial, `summary.md` y `qa-note.md`.
* **Carril Rojo (Epic / Arquitectura, > 500 líneas o breaking changes):** Descomposición orgánica en N features Lean de Carril Amarillo, o formalización mediante `/adr` (reutilizado desde `Template-sdd`).

### 2. Memoria de Decisiones (Compatible con Usuario y Fábrica Autónoma)
Antes de diseñar o implementar cualquier cambio:
1. **1er Nivel (Catálogo Global):** Consulta `docs/approved-decisions.md`. Si la regla existe, **se aplica de inmediato sin preguntar y queda prohibido duplicarla en la feature**.
2. **2º Nivel (Decisiones de Features Previas):** Consulta `docs/` en busca de decisiones no consolidadas de features antecedentes para mantener coherencia.
3. **3er Nivel (Decisiones Inéditas):**
   - *Menores / Locales:* El agente o fábrica aplica la mejor práctica y la registra en `docs/NNN-nombre/decisions.md` (o en la sección de decisiones del log en `docs/fast/`).
   - *Críticas / Arquitectónicas:* En modo usuario, se pregunta en chat. En modo fábrica autónoma, **se detiene la fábrica** y se emite una alerta bloqueante para revisión humana.

### 3. Reducción de Costes y Delegación en Subagentes
* La redacción y persistencia de todos los artefactos Markdown (`feature.md`, logs de `docs/fast/`, `summary.md`, `repair.md` y `qa-note.md`) **debe delegarse al subagente `odd-writer`** (`model: flash`, `effort: low`).
* El agente principal o coordinador debe reservar su capacidad de razonamiento para análisis arquitectónico, lógica de negocio y tests.

### 4. Desarrollo Guiado por Pruebas (TDD) y Resiliencia (Regla 3+1)
* Toda tarea de código debe seguir el ciclo **Red-Green-Refactor** (el test unitario o de integración se escribe y falla antes de modificar producción).
* **Límite 3+1:** Si un subagente o implementador falla 3 veces consecutivas al ejecutar o corregir tests, en el **4º intento debe detenerse obligatoriamente**, redactar un diagnóstico de causa raíz y solicitar asistencia técnica al usuario o agente coordinador.

### 5. Validación y Bucle Automático de Reparación (`repair.md`)
* Al terminar una feature, se ejecuta la validación (`/odd-validate`).
* **Si pasa:** Se generan `docs/NNN-nombre/summary.md` y `qa-note.md` y se marca la feature como `Completada`.
* **Si falla:** Se genera transitoriamente `docs/NNN-nombre/repair.md` con los fallos y tareas `R-XX`, el estado pasa a `En reparación` y se corrigen los fallos con TDD (máx 3+1). Al volver a verde, **se elimina `repair.md`** y se emite la `qa-note.md` definitiva.

## Al terminar cualquier tarea
- Verificar obligatoriamente que todos los tests unitarios y de integración pasen en verde.
- En ejecuciones locales y fábrica autónoma, los servicios y bases de datos locales deben estar levantados para probar persistencia real. Solo APIs de terceros no simulables pueden marcarse como "No comprobado" en `qa-note.md` para verificación manual.

## Creación de Pull Requests e Issues
- Reutilizar las plantillas estandarizadas de `Template-sdd`:
  - **Pull Requests:** Seguir `.github/pull_request_template.md`.
  - **Issues:** Utilizar `.github/ISSUE_TEMPLATE/` (`bug_report.md` o `feature_request.md`).

## Documentación
- Referencias a la constitución del proyecto (`constitution/`), memoria global (`docs/approved-decisions.md`) y bitácoras ágiles (`docs/fast/`).
