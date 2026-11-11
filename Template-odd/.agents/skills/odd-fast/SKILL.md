---
name: odd-fast
description: Ejecuta tareas por el Carril Verde (Fast-Track) de ODD. Aplica TDD directo (Red-Green-Refactor) para cambios < 50 líneas (fixes, chores, refactors), gestiona resiliencia 3+1 y persiste la bitácora ágil en docs/fast/ delegando en el subagente odd-writer.
---

# Skill `/odd-fast`: Carril Verde (Fast-Track)

## Propósito
Resolver cambios pequeños, correcciones inmediatas y tareas de mantenimiento técnico (< 50 líneas) con máxima agilidad, calidad guiada por pruebas y sin generar burocracia documental innecesaria.

---

## Procedimiento Operativo

### Paso 1: Verificación de Precondiciones
1. Confirma que el cambio no introduce dependencias prohibidas ni viola `constitution/tech-stack.md`.
2. Consulta `docs/approved-decisions.md` para aplicar las convenciones globales existentes sin pedir confirmación.

### Paso 2: Ciclo TDD Estricto (Red-Green-Refactor)
1. **Fase Roja:** Escribe o adapta el test unitario/integración que reproduce el bug o define el comportamiento deseado. Ejecuta el test y confirma que falla.
2. **Fase Verde:** Escribe el código mínimo y necesario para que el test pase.
3. **Fase Refactor:** Limpia el código manteniendo todos los tests en verde.

### Paso 3: Resiliencia y Control de Fallos (Regla 3+1)
* Si los tests o linters fallan durante la ejecución, se autorizan hasta **3 intentos de corrección autónoma**.
* Si al **4º intento consecutivo** los tests continúan en rojo, el agente debe detenerse, diagnosticar el error y alertar al usuario con la traza técnica solicitando asistencia.
* Queda terminantemente prohibido hacer commit o dar por finalizada la tarea con tests fallidos.

### Paso 4: Registro Ágil de Bitácora
1. Una vez los tests están en verde, delega en el subagente `odd-writer` la creación del registro en:
   `docs/fast/<YYYYMMDD>-<tipo>-<nombre>.md`
   utilizando la plantilla base `.agents/skills/odd-fast/assets/fast-log.md`.
2. Si el cambio estaba relacionado con una feature previa, incluye el vínculo en el encabezado (`Relacionado con: Feature NNN`).
3. **No se genera `qa-note.md`:** El test unitario automatizado es la validación suficiente para este carril.
