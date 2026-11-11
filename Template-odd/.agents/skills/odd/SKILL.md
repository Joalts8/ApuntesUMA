---
name: odd
description: Orquestador y marco de normas transversales de Organic-Driven Development (ODD). Clasifica peticiones en Carril Verde, Amarillo o Rojo, gestiona transiciones automáticas en constitution/roadmap.md, audita decisiones y coordina la ejecución autónoma o paso a paso.
---

# Marco y Orquestador Organic-Driven Development (`/odd`)

## Propósito
Operar como el **conjunto maestro de normas transversales de ODD** y como **orquestador inteligente de desarrollo**. Puede invocarse directamente para clasificar y resolver una necesidad de principio a fin, o servir de marco regulatorio común para las skills individuales (`/odd-fast`, `/odd-feature`, `/odd-clarify`, `/odd-validate`).

---

## Modos de Trabajo con las Skills ODD

1. **Modo Integral (`/odd <petición>`):**
   - El orquestador clasifica la tarea, consulta la memoria de decisiones, genera la estructura necesaria, implementa con TDD y valida de principio a fin de forma autónoma (ideal para fábricas de software o flujos directos).
2. **Modo Paso a Paso (Invocación Directa de Skills):**
   - El usuario o agente puede invocar de forma independiente la skill adecuada en cada momento:
     - `/odd-fast`: Para hotfixes o tareas técnicas inmediatas (< 50 líneas).
     - `/odd-feature`: Para definir e implementar una feature estándar (50-500 líneas).
     - `/odd-clarify`: Para actualizar requisitos o corregir una feature abierta en desarrollo.
     - `/odd-validate`: Para auditar tests, verificar que no haya código no registrado y resolver el bucle `repair.md`.
     - `/odd-decisions-review`: Para consolidar decisiones periódicamente en `approved-decisions.md`.

---

## Normas Transversales ODD

### 1. Transición Automática en `constitution/roadmap.md`
El estado de las tareas se actualiza de forma automática en el roadmap del proyecto según el carril:
* **Carril Verde (Fast-Track):** **NO se registra en `roadmap.md`** para no contaminar la visión estratégica de producto con micro-arreglos; su trazabilidad histórica vive exclusivamente en `docs/fast/`.
* **Carril Amarillo (Feature Lean) y Carril Rojo (Epic):**
  - **Al nacer la feature:** Se registra en la sección `Siguiente` de `constitution/roadmap.md` y se inicializa su carpeta en `features/NNN-nombre/`.
  - **Al iniciar la implementación TDD:** Se mueve a `En curso`.
  - **Si se clarifican o amplían requisitos (`/odd-clarify`):** Se mueve a `En mejora`.
  - **Al finalizar las tareas y pasar a validación (`/odd-validate`):** Se mueve a `En validación`.
  - **Si la validación falla (se crea `repair.md`):** Se mueve a `En reparación`.
  - **Tras validar con éxito (todo en verde):** Se mueve a `Hecho`, anotando el porcentaje de cobertura de tests alcanzado.

### 2. Memoria Técnica de Decisiones (3 Niveles)
Antes de proponer o aplicar cualquier solución técnica:
1. **1er Nivel (Catálogo Global):** Consulta `docs/approved-decisions.md`. Si la regla existe, aplícala de inmediato y **prohibido volver a registrarla en la feature**.
2. **2º Nivel (Decisiones de Features Previas):** Consulta `docs/` en busca de decisiones no consolidadas de features antecedentes para mantener coherencia.
3. **3er Nivel (Decisiones Inéditas):**
   - *Menores / Locales:* Aplica la mejor práctica y regístrala en `docs/NNN-nombre/decisions.md` (o en la sección de decisiones de `docs/fast/`).
   - *Críticas / Bloqueantes:* Pausa la fábrica o pregunta al usuario en chat antes de implementar.

### 3. Reducción de Tokens y Delegación en Subagentes
* Toda la redacción y persistencia de archivos Markdown (`feature.md`, logs de `docs/fast/`, `summary.md`, `repair.md` y `qa-note.md`) **debe delegarse al subagente `odd-writer`** (`model: flash`, `effort: low`).

### 4. Desarrollo Guiado por Pruebas (TDD) y Resiliencia (Regla 3+1)
* Todo desarrollo aplica estrictamente el ciclo Red-Green-Refactor.
* Si un subagente o implementador falla 3 veces consecutivas al ejecutar o corregir tests, **en el 4º intento se detiene obligatoriamente** y solicita asistencia con el diagnóstico del fallo.

### 5. Control de Código No Registrado (Rogue Code)
* Durante la validación se audita que no se haya introducido código de producción que no responda a un Requisito Funcional (`RF-XX`) de la feature. Todo código huérfano debe ser justificado o eliminado.
