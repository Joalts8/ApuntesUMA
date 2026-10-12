# Plantilla SDD con agentes

> Plantilla genérica para documentar cualquier proyecto con desarrollo dirigido por especificación (SDD): primero se escribe la spec, luego el plan, luego las tareas, y solo entonces se toca el código.
>
> **Cómo usar esta plantilla:** copia esta carpeta a tu proyecto como `spec/`, rellena la `constitution/` una vez al arrancar y crea una carpeta por feature a partir de `features/NNN-nombre-feature/`. Sustituye todo lo que esté entre `<…>` y borra las notas en _cursiva_. No se aplica a agents.md.

## Estructura (Incompleta, no están todos las skills)

```
spec/
├── .github/                 ← configuración de GitHub (plantillas de PR, issues y bots)
│   ├── ISSUE_TEMPLATE/      ← plantillas para reportar bugs y proponer features
│   ├── workflows/           ← acciones automatizadas (bot de triaje y etiquetado)
│   └── pull_request_template.md ← plantilla automática de Pull Requests
├── .agents/                 ← comandos y skills para los agentes(Ver docs ya q algunos usan otras carpetas)
│   ├── commands/            ← comando custom (no vale en agy, el cual yo uso)
│   └── skills/
│       └── sdd-spec/        ← carpeta de la skill con su nombre, esta es para crear spec.md
|           ├──assets/       ← ubicación de la plantilla a usar   
│           └── SKILL.md     ← instrucciones de la skill(y ejemplo para crearlas)
├── constitution/            ← reglas estables del proyecto
│   ├── mission.md           ← qué construimos y para quién
│   ├── roadmap.md           ← orden de las features
│   └── tech-stack.md        ← tecnologías, convenciones y límites
├── docs/                    ← documentación del proyecto
│   ├── ADR/                 ← documentos de decisiones del proyecto
│   │   └── ADR-NN.md        ← plantilla de ADR
│   └── NNN-nombre-feature/  ← documentación activa de cada implementacion de la ia. Contexto para otra sesión
├── features/                ← carpetas donde se encontraran las feature
│   └── NNN-nombre-feature/  ← carpeta dedicada a cada feature con spec.md, plan.md y task.md
├── src/                     ← código fuente del proyecto
├── AGENTS.md                ← instrucciones para los agentes
├── LICENSE                  ← licencia de la plantilla( por la que se utilice)
├── .gitignore               ← archivos que no se suben al repo(este esta vacio porq usa el de la raiz)
├── .env                     ← variables de entornos a usar, NO se sube a github, sino .env.example en su lugar.
├── CONTRIBUTING.md          ← Explica cómo otras personas pueden contribuir 
├── CODE_OF_CONDUCT.md       ← Establece las normas de comportamiento y convivencia de la comunidad
├── SECURITY.md              ← Indica cómo reportar vulnerabilidades
└── README.md                ← resumen del proyecto para los usuarios.
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


A estas templates faltaría quitar/cambiar la `licencia`, editar el `readme.md` y `agents.md` y usar /sdd-constitution para crear la constitution.