---
name: sdd
description: Úsala siempre que trabajes con Spec-Driven Development en este proyecto (constitution/ o cualquier archivo dentro de features/) - redactar, revisar o cambiar specs, planes y tareas, o implementar y validar tareas de una spec tanto en modo humano como en fábrica automática.
---
# Spec-Driven Development (SDD)
## Flujo
Constitución → Spec (o desde Ticket/Issue) → Clarificación → Plan (con diseño previo validado) → Tareas → Implementación (TDD) → Validación (QA) → Cambio.

## Normas
- Lee los archivos en `/constitution` y cada spec en `/features/NNN-nombre-feature/spec.md` antes de empezar a trabajar en una feature, si no la has leído ya y las tienes en el contexto. Si `/constitution` no está creada, inicialízala con `/sdd-constitution`.
- **Origen de la spec:** Una spec puede originarse a partir de una idea del usuario o directamente desde un ticket/issue (Jira, GitHub Projects, Linear, etc.) indicado en el prompt. Si hay ticket, úsalo como punto de partida y enlázalo en la spec.
- **División de specs sobredimensionadas:** Si durante la definición se detecta que una feature abarca demasiados flujos independientes, más de 8-10 RFs principales o anticipa más de 10 tareas técnicas, divídela en specs más pequeñas y atómicas acordadas con el usuario o el agente revisor.
- **Revisión previa para el plan:** Antes de redactar cualquier plan, es obligatorio revisar lo ya existente: el código en `src/`, las specs en `/features` y el contexto histórico/ADRs en `/docs` para reutilizar componentes y mantener coherencia arquitectónica.
- **Diseño previo obligatorio en el plan:** Antes de redactar y guardar `plan.md`, se debe validar un diseño conceptual previo (mediante aprobación del usuario en modo interactivo, o mediante un subagente revisor en modo fábrica automática).
- **Gestión de decisiones y Fábrica Autónoma (Cadena de búsqueda y Niveles):**
  - Toda feature que tome **decisiones nuevas** crea y mantiene su registro en `docs/NNN-nombre-feature/decisions.md` (las decisiones conocidas previamente no se registran ni obligan a crear el archivo).
  - Si `docs/approved-decisions.md` no existe aún en el proyecto, se inicializa a partir de `.agents/skills/sdd-decisions-review/assets/approved-decisions.md`.
  - Cuando surja una decisión técnica o de diseño (en modo fábrica o manual):
    1. **Nivel 1 (Automático sin preguntar):** Consulta primero `docs/approved-decisions.md`. Si ya está aprobada, aplícala directamente y **no la registres** en `decisions.md`.
    2. **Nivel 1.5 (Reutilización de features no consolidadas):** Si no está en el catálogo global, revisa los `decisions.md` de las features recientes aún no revisadas para replicar la misma solución y mantener coherencia. **No la dupliques** en el `decisions.md` actual.
    3. **Nivel 2 (Automático con registro de novedad):** Si es una decisión menor o local inédita dentro del stack, aplícala y regístrala en `docs/NNN-nombre-feature/decisions.md`.
    4. **Nivel 3 (Intervención humana obligatoria):** Si es una decisión crítica, arquitectónica, bloqueante o contradice la constitución, detén la fábrica, presenta opciones y pide aprobación del usuario antes de registrarla e implementarla.
- **Revisión periódica de decisiones:** Periódicamente (al cerrar un ciclo o hito de features) se ejecutará la skill `sdd-decisions-review` para evaluar los `decisions.md` recientes y consolidar las decisiones transversales en `docs/approved-decisions.md`.
- Si necesitas aclarar algo, en `/docs/NNN-nombre-feature/` estará la documentación de la implementación de la feature, separadas por tareas. Si no está, pregunta al usuario antes de asumir nada.
- En `docs/NNN-nombre-feature/repair.md` se encuentran los problemas encontrados durante la validación de la feature. Si existe, debes tenerlo en cuenta antes de implementar de nuevo la feature para subsanar los fallos; una vez resueltos y reflejados en el resumen de implementación en `/docs/NNN-nombre-feature/NN-task.md`, se debe eliminar obligatoriamente `repair.md`.
- Tras la creación o modificación aprobada de `spec.md`, `plan.md` o `tasks.md`, es obligatorio realizar un commit individual siguiendo la regla establecida en `AGENTS.md` (Conventional Commits en español, ver skill `sdd-implement`).
- En modo interactivo, nunca pases a la siguiente fase sin la aprobación explícita del usuario. En modo fábrica automática, la validación entre fases la realizan subagentes dedicados de revisión.
- Un cambio de requisitos se hace primero en la spec, luego en el plan y las tareas, y por último en el código.
- Cada spec vive en su carpeta: `/features/NNN-nombre-feature/` con `spec.md`, `plan.md` y `tasks.md`.
- La spec manda: si algo no está en la spec, no se implementa. 
- Si falta una decisión o necesitas más información, para y pregunta. 
- Al crear una feature, se añade a `/constitution/roadmap.md` (si no está) en la sección "Siguiente" y se crea la carpeta `/features/NNN-nombre-feature/` con `spec.md`, `plan.md` y `tasks.md`.
- Si se pide implementar algo fuera de la spec, se actualiza `spec.md`, `plan.md` y `tasks.md` antes de implementarlo.
- Al comenzar una implementación, se moverá la feature a "En curso" en `constitution/roadmap.md`.
- **Implementación con TDD (Test-Driven Development):** Todo desarrollo se realiza aplicando el ciclo Red-Green-Refactor (primero el test que falla, luego el código mínimo para pasar y finalmente refactorizar en verde).
- **Ejecución de tests en fábrica automática (BD vs APIs externas):** Las bases de datos y contenedores locales deben estar levantados para validar la persistencia. Si un test falla por no poder conectarse a una API externa de terceros, se marcará como "No comprobado / Pendiente de verificación externa" para que sea auditado en la revisión manual de QA sin romper en falso la suite local.
- **Límite de fallos en subagentes (Regla 3+1):** Si un subagente falla al implementar y ejecutar los tests 3 veces consecutivas y en el 4º intento tampoco lo logra, debe detenerse inmediatamente y avisar al agente principal, explicando qué falla, qué se intentó y si pueden resolverlo juntos o requieren asistencia del usuario.
- Tras cada implementación, redacta en `/docs/NNN-nombre-feature/NN-task.md` un resumen de qué se ha hecho en esa tarea/tareas principales, incluyendo decisiones de diseño y problemas encontrados junto a la solución aplicada. Debe tener un apartado para cada subtarea de la tarea principal, además del global de la tarea principal.
- Tras acabar una tarea, marca la tarea como completada en `tasks.md`. Esto aplica a subtareas también, y debe hacerse en el orden de dependencia de las tareas.
- Tras cada implementación deben pasar los tests de código, y debe verse reflejada la cobertura de tests de cada feature en `/constitution/roadmap.md`.
- Al acabar la implementación de la feature, mover la feature a "En validación" en `constitution/roadmap.md`.
- Si falla la validación, se debe mover la feature a "En curso" en `/constitution/roadmap.md` y arreglar lo que haya fallado documentado en `repair.md`.
- Tras completar la implementación y validación, mover la feature a "Hecho" en `/constitution/roadmap.md`.
- El archivo `/docs/NNN-nombre-feature/qa-note.md` dará un resumen de la validación de la feature y de cómo debe probarse. Debe contener instrucciones para que cualquier persona pueda probar la feature manualmente, siendo lo más simples dentro de lo posible (sin pasos innecesarios ni jerga compleja), asegurando siempre cubrir todo lo necesario por probar (criterios de aceptación de la spec, casos límite, pruebas no comprobadas por APIs externas y errores probados).
- Durante la fase de validación, el veredicto y los hallazgos deben comunicarse obligatoriamente por el chat o registro.
- Para realizar cambios en la spec, plan o tareas, se debe mover la feature a "En mejora" en `/constitution/roadmap.md`. Si está aquí, no se mueve a "En curso", se implementa y se valida, y tras ello se mueve a "Hecho" en `/constitution/roadmap.md`.
- Evita que se dupliquen specs en `roadmap.md`.
- Las plantillas funcionan así: Sustituye todo lo que esté entre `<…>` y borra las notas en _cursiva_.
