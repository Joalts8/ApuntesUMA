# Plantilla SDD con agentes

> Plantilla genérica para documentar cualquier proyecto con desarrollo dirigido por especificación (SDD): primero se escribe la spec, luego el plan, luego las tareas, y solo entonces se toca el código.
>
> **Cómo usar esta plantilla:** copia esta carpeta a tu proyecto como `spec/`, rellena la `constitution/` una vez al arrancar y crea una carpeta por feature a partir de `features/NNN-nombre-feature/`. Sustituye todo lo que esté entre `<…>` y borra las notas en _cursiva_. No se aplica a agents.md.

## Estructura

```
spec/
├── .opencode/               ← comandos y skills para los agentes
│   ├── commands/
│   │   └── web.md           ← comando custom(inutil pero mini ejemplo con el enlace a la doc)
│   └── skills/
│       └── nombre/
│           ├── SKILL.md     ← instrucciones de la skill
│           └── spec-template.md ← plantilla para las especificaciones(ver en /features)
├── constitution/            ← reglas estables del proyecto
│   ├── mission.md           ← qué construimos y para quién
│   ├── roadmap.md           ← orden de las features
│   └── tech-stack.md        ← tecnologías, convenciones y límites
├── features/                ← una carpeta por feature
│   └── NNN-nombre-feature/  ← plantilla para crear una feature con SDD
├── src/                     ← código fuente del proyecto
│   └── .keep                ← mantiene la carpeta en el repositorio(ignorar)
├── AGENTS.md                ← instrucciones para los agentes
├── LICENSE                  ← licencia de la plantilla(eliminar en el proyecto)
└── prompts.md               ← prompts reutilizables para sdd, automatizado en la skill
```

_La constitución y cada feature pueden ser un único archivo si el proyecto/feature es pequeño._

## Flujo para una feature nueva

1. Crear `features/NNN-nombre-feature/` con el siguiente número libre (`001`, `002`, …).
2. Escribir `spec.md`: qué hace, por qué y criterios de aceptación medibles.
3. Escribir `plan.md`: enfoque técnico y decisiones, respetando `constitution/tech-stack.md`.
4. Desglosar en `tasks.md` y marcar el progreso.
5. Implementar y validar (build/tests/lint o lo que defina la constitución).
6. Actualizar `constitution/roadmap.md` (mover la feature a "Hecho").

> La constitución manda: si una feature choca con `mission.md` o `tech-stack.md`, se replantea la feature, no la constitución.

**Creada por `[Brais Moure](https://github.com/mouredev)` en el curso de programacion con agentes y curso de SDD.**
Yo unicamente he creado una template de proyecto basado en la suya y realizando pequeños cambios como estas 2 lineas y juntando las templates de ambos cursos.
Al crear un proyecto con estas plantillas omite este readme y license