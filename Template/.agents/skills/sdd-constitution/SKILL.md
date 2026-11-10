---
name: sdd-constitution
description: Usa esta skill para la creación o modificación de la constitución de un proyecto utilizando SDD. El prompt que se dé será el contexto adicional que se sumará al indicado en la skill.
---

# Creación y Modificación de la Constitución

## Objetivo
Crear o actualizar los documentos de gobernanza del proyecto en `/constitution/` (`mission.md`, `roadmap.md` y `tech-stack.md`) a partir de las plantillas base en `./assets/`.

## Entradas
- Contexto extra dado en el prompt del usuario.
- Preguntas de clarificación (máximo 20) indicadas en el proceso.
- Plantillas base en `./assets/` (`mission.md`, `roadmap.md`, `tech-stack.md`).

## Salidas
- Archivos generados o actualizados en `constitution/`:
  - `constitution/mission.md`
  - `constitution/roadmap.md`
  - `constitution/tech-stack.md`
- Commit con el mensaje estándar: `docs(constitution): inicialización o actualización de la constitución del proyecto`.

## Proceso
1. Lee `AGENTS.md`, `README.md` y el código del proyecto (si ya existe).
2. Si los archivos en `constitution/` no existen aún, carga las plantillas base desde `./assets/`:
   - `./assets/mission.md`
   - `./assets/roadmap.md`
   - `./assets/tech-stack.md`
3. Conduce una entrevista de requisitos con el usuario (máximo 20 preguntas) para rellenar las secciones esenciales (stack, base de datos, estilo, límites duros y alcance del producto).
4. Si ya existe una constitución previa, revísala e indica si hay algo que cambiar, refinar o añadir según las nuevas directrices.
5. Aplica las reglas estándar de plantillas: sustituye todo lo que esté entre `<...>` con los datos acordados y borra las notas en _cursiva_.
6. Guarda los archivos en `constitution/` y realiza el commit individual correspondiente.

## Reglas
- No inventes tecnologías, límites ni reglas no acordadas. Si tienes propuestas técnicas, preséntalas y pregunta.
- Las plantillas base oficiales residen en `./assets/`. Nunca dejes plantillas en blanco con marcadores `<...>` en `constitution/`.
- Usa la skill sdd.
