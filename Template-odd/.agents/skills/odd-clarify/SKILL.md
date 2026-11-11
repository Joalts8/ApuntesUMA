---
name: odd-clarify
description: Actualiza y amplía especificaciones de features abiertas en ODD. Analiza el impacto en cascada, añade o modifica requisitos funcionales (RF) generando sus tareas correspondientes (T) sin invalidar las tareas ya completadas, delegando en odd-writer.
---

# Skill `/odd-clarify`: Clarificación y Evolución de Features Abiertas

## Propósito
Incorporar modificaciones, nuevos criterios o ajustes sobre una feature que se encuentra actualmente en desarrollo (`En curso` o `Propuesta`) manteniendo la coherencia de la trazabilidad en cascada sin corromper el trabajo previamente completado.

---

## Procedimiento Operativo

### Paso 1: Detección y Análisis de Impacto
1. Localiza el archivo `features/NNN-nombre/feature.md` de la feature abierta.
2. Identifica los nuevos requisitos o cambios solicitados por el usuario/ticket:
   - ¿Afecta a requisitos funcionales existentes (`RF-XX`) o introduce uno nuevo?
   - ¿Qué impacto tiene en el diseño técnico (`P-XX`)?
   - ¿Qué nuevas tareas (`T-XX`) se derivan?

### Paso 2: Protección de Tareas Completadas
* **Regla estricta:** Las tareas ya marcadas con `[X]` **no se eliminan ni se reinician**.
* Si un cambio requiere modificar código ya escrito en una tarea previa, se crea una tarea correctiva o evolutiva adicional (ej. `T-05: Adaptar DTO para nuevo campo según RF-03`) en lugar de deshacer tareas pasadas.

### Paso 3: Actualización en Cascada con `odd-writer`
Delega en el subagente `odd-writer` la actualización precisa del archivo `feature.md`:
1. Incorpora el nuevo `RF-XX`.
2. Añade el enfoque de diseño `P-XX`.
3. Anexa las nuevas tareas pendientes `- [ ] T-XX`.
4. Mantiene intactos el encabezado, el historial y las tareas ya finalizadas.

### Paso 4: Reanudación del Flujo
Informa al usuario del impacto incorporado y continúa la implementación de las nuevas tareas pendientes con TDD.
