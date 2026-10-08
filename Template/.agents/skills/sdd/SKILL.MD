---
name: sdd
description: Úsala siempre que trabajes con Spec-Driven Development en este proyecto
(docs/constitution.md o cualquier archivo dentro de specs/) - redactar, revisar o cambiar
specs, planes y tareas, o implementar y validar tareas de una spec.
---
# Spec-Driven Development (SDD)
## Flujo
Constitución → Spec → Clarificación(aplicable tras plan y tareas) → Plan → Tareas → Implementación → Validación →
Cambio.

## Normas
- Lee los archivos en `constitution/` (si no lo has hecho ya) y cada spec en `features/NNN-nombre/spec.md` antes de empezar a trabajar en una feature.
- Si necesitas aclarar algo, en `docs/NNN-feature/` estará la documentación de la implementación de la feature, separadas por tareas. Si no está, pregunta al usuario antes de asumir nada.
- Nunca pases a la siguiente fase sin la aprobación explícita del usuario.
- La spec manda: si algo no está en la spec, no se implementa. Si falta una decisión, para y pregunta.
- Un cambio de requisitos se hace primero en la spec, luego en el plan y las tareas, y por último en el código.
- Cada spec vive en su carpeta: `features/NNN-nombre/` con `spec.md`, `plan.md` y `tasks.md`.
- Tras cada implementación, redacta en `docs/NNN-feature/NN-task.md` un resumen de que se hecho en esa tarea principal, incluyendo decisiones de diseño y problemas encontrados junto a la solución aplicada. Debe tener un apartado para cada subtarea de la tarea principal, además del global de la tarea principal. Debe ser un resumen que sirva como contexto a otro agente, asique debe tener suficiente información, pero sin sobrecargar el contexto.
- Tras acabatr una tarea, marca la tarea como completada en `tasks.md`. Esto aplica a subtareas también, y debe hacerse en el orden de dependencia de las tareas.
- Tras completar, mover la feature a "Hecho" en `constitution/roadmap.md`.
- Para realizar cambios en la spec, plan o tareas, se debe mover la feature a "En revisión" / "En mejora" en `constitution/roadmap.md`.
-Las plantillas funcionan asi: Sustituye todo lo que esté entre `<…>` y borra las notas en _cursiva_.


## Plan (plan.md)
Archivos y responsabilidades · Funciones puras (con "hoy" como parámetro) · Algoritmo en
pseudocódigo · Interfaz · Decisiones justificadas con su alternativa descartada. Indica qué RF cubre cada parte.

## Tareas (tasks.md)
Máximo 20-30 min por tarea, en orden de dependencia. Si salen más de 10, propón dividir
la spec.
