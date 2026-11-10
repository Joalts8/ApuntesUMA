---
name: sdd-implement
description: Skill que se usará cuando se quiera implementar una feature usando SDD a partir de sus tareas desglosadas.
---

# Implementación de una Spec

## Objetivo
Implementación de las tareas de una feature en el ciclo SDD mediante agentes y subagentes siguiendo TDD.

## Entradas
- Número de la feature, nombre o ambas cosas.
- Información extra.
- Catálogo global de decisiones en `docs/approved-decisions.md` (si no existe, se inicializa desde `.agents/skills/sdd-decisions-review/assets/approved-decisions.md`).
- Registro de decisiones de la feature en `docs/NNN-nombre-feature/decisions.md` (y de otras features no consolidadas).
- Archivo auxiliar `/docs/NNN-nombre-feature/repair.md` (si existe tras una validación anterior fallida).

## Salidas
- Resumen de cada implementación en `docs/NNN-nombre-feature/NN-task.md`.
- Registro de decisiones actualizado en `docs/NNN-nombre-feature/decisions.md` (si surgieron decisiones nuevas durante el desarrollo).
- Resumen global de la implementación (paso 5 del proceso).

## Proceso
1. Mueve la feature a "En curso" en `constitution/roadmap.md` si no lo está ya (si está en "En mejora" no se mueve: se implementa desde ahí) y revisa las tareas en `tasks.md` para saber si hay alguna bloqueada por otra y verificar el orden de dependencias. Si existe `/docs/NNN-nombre-feature/repair.md`, revísalo prioritariamente para incorporar la corrección de dichos problemas en el trabajo a realizar.
2. Identifica las tareas ejecutables de implementación. Si detectas tareas de validación final o verificación de criterios de aceptación de la spec, resérvalas para la fase posterior de validación (`sdd-validate`) y NO las lances en un subagente.
3. Invoca los subagentes para las tareas de implementación que se pueden realizar con las siguientes indicaciones:
    0. Ten en cuenta la skill sdd.
    1. PROHIBICIÓN: No invoques ni llames a otros subagentes bajo ninguna circunstancia. Realiza todo el trabajo asignado tú directamente.
    2. **Consulta y registro de decisiones (Autonomía de la Fábrica):**
       - Antes de adoptar un criterio técnico o patrón en el código, revisa en primer lugar `docs/approved-decisions.md`. Si existe una regla aprobada, aplícala sin consultar y **NO la registres** en `decisions.md`.
       - Si no está en el catálogo global, consulta los `decisions.md` de otras features recientes no consolidadas para reutilizar el criterio; **NO lo dupliques** en el `decisions.md` actual.
       - **Solo registrar decisiones nuevas:** Si surge una decisión nueva e inédita de diseño o implementación local durante el desarrollo, documéntala en `/docs/NNN-nombre-feature/decisions.md` con su contexto, justificación y alcance.
    3. **Metodología TDD (Test-Driven Development) obligatoria:** Aplica estrictamente el ciclo Red-Green-Refactor para cada tarea o funcionalidad:
       - **Rojo (Red):** Escribe primero los tests automatizados (unitarios y de integración cuando aplique) que verifiquen el comportamiento y requisitos asignados. Comprueba que fallan inicialmente.
       - **Verde (Green):** Escribe el código de producción mínimo y estrictamente necesario hasta que todos los tests pasen con éxito.
       - **Refactorización (Refactor):** Limpia, optimiza y estructura el código sin añadir funcionalidad nueva, verificando que los tests permanezcan en verde.
       - *Nota de infraestructura:* Las bases de datos deben estar iniciadas obligatoriamente para ejecutar los tests de persistencia. Únicamente los tests dependientes de APIs externas no simuladas/de terceros pueden omitirse limpiamente (`skip`) y reportarse como "No comprobado" para revisión de QA.
    4. Envía el resultado de la ejecución de los tests al agente principal una vez que estén en verde.
    5. **Control de fallos y reintentos (Regla de 3+1 intentos):** Si al implementar y ejecutar los tests fallas 3 veces consecutivas y en el 4º intento tampoco logras que pasen los tests (3 reintentos fallidos tras el primer intento), **DETÉN TU EJECUCIÓN INMEDIATAMENTE**. No entres en bucles infinitos de prueba y error. Notifica de inmediato al agente principal reportando:
       - Qué falla exactamente (error técnico, trazas y qué test concreto no pasa).
       - Qué soluciones se intentaron en los 3 intentos previos y por qué no funcionaron.
       - Valoración clara: indica si es un problema técnico que tú y el agente principal pueden solucionar juntos (revisando mocks, lógica interna o enfoque de código), o si se requiere asistencia o aclaración explícita del usuario por ambigüedades en la spec/plan o dependencias externas bloqueadas.
    6. Realiza commits siguiendo la norma de commits del proyecto indicando lo que se ha hecho cada vez que sea necesario.
    7. Marca la tarea (o subtareas si no se completó toda la tarea) como hecha en `tasks.md` e indica qué RF cubre.
    8. Redacta la documentación necesaria en `/docs/NNN-nombre-feature/NN-task.md` (incluyendo decisiones de diseño, problemas encontrados, soluciones adoptadas y qué fallos de `repair.md` han sido subsanados).
    9. Avisa de tareas incompletas o errores al agente principal.
4. Con las tareas desbloqueadas, vuelve a invocar a los subagentes necesarios con las indicaciones anteriores.
5. Finalmente, resume las tareas completas e incompletas, los errores encontrados y si es necesaria la intervención del usuario. Si existía `/docs/NNN-nombre-feature/repair.md` y se han solventado sus incidencias reflejándolas en `/docs/`, elimina obligatoriamente el archivo `repair.md`.

## Reglas
- Para cada tarea principal de desarrollo/implementación debes llamar a un subagente que la realice.
- **Implementación obligatoria con TDD (Test-Driven Development):** Prohibido escribir código de producción sin haber escrito previamente el test que verifique dicho comportamiento (ciclo Red-Green-Refactor).
- **Prohibido el anidamiento de subagentes:** Los subagentes NO deben llamar ni invocar a otros subagentes. Solo el agente principal orquesta y lanza subagentes.
- **Límite de fallos en subagentes (Regla 3+1):** Los subagentes no deben reintentar indefinidamente. Si fallan 3 veces y en la 4ª tampoco pasa el test, deben detenerse y alertar al agente principal detallando el error, lo intentado y si pueden solucionarlo entre ambos o se debe escalar al usuario.
- **Exclusión de la verificación de criterios de aceptación en subagentes:** La verificación y validación de los criterios de aceptación de la spec NO se harán en ningún subagente ni durante la fase de implementación. Aunque figuren como tarea o subtarea en `tasks.md`, dicha comprobación se reserva exclusivamente para la fase posterior de validación (`sdd-validate`), orquestada por el agente principal.
- **Eliminación de `repair.md`:** Cuando se ejecute una reimplementación para solucionar los fallos detectados en una validación previa, el agente DEBE eliminar `/docs/NNN-nombre-feature/repair.md` al finalizar, ya que tanto los problemas como las soluciones adoptadas quedan documentados permanentemente en `/docs/NNN-nombre-feature/NN-task.md`.
- Si una tarea es dependiente de otra, espera a que se complete antes de lanzar el subagente de la tarea bloqueada.
- Si no se indica otra cosa en `AGENTS.md`, se supondrá que la norma de los commits será Conventional Commits en español.
- Usa la skill sdd.

## Conventional Commits en español

Varios tipos. Elige el que corresponda, no mezcles:

| Prefijo | Cuándo |
|---|---|
| feat | Una nueva característica o funcionalidad para el usuario |
| fix | Corrección de un error o bug |
| docs | Cambios exclusivos en la documentación |
| style | Cambios de formato, espaciado o estilo que no afectan la lógica del código |
| refactor | Modificación de código que no corrige un error ni añade nueva funcionalidad |
| perf | Mejora de rendimiento del código |
| test | Añadir o corregir pruebas |
| build | Cambios en el sistema de construcción o dependencias externas |
| ci | Cambios en scripts o flujos de integración continua |
| chore | Mantenimiento o tareas que no modifican código fuente ni tests |
| ops | Operaciones de infraestructura, despliegue, backups o monitorización |

Plantilla:
> `<tipo>(<alcance>)[!]: <descripción concisa y clara en español>`
>
> `[cuerpo opcional con detalles adicionales del cambio]`
>
> `[BREAKING CHANGE: explicación del cambio incompatible]`

Elementos opcionales:
- **Scope `(<alcance>)`:** Se coloca entre paréntesis inmediatamente después del tipo para delimitar el módulo, componente o capa afectada (por ejemplo: `fix(incidencias):`, `test(db):`).
- **`!` de Breaking Change:** Se añade justo antes de los dos puntos (ejemplo: `feat!:`) para avisar visual y rápidamente de un cambio que rompe la compatibilidad hacia atrás.
- **Pie `BREAKING CHANGE:`:** Si se rompe compatibilidad, se añade al final del mensaje explicando exactamente qué funcionalidad cambia y cómo migrar o adaptarse.

Ejemplos bien escritos:
> `feat: completada la tarea 3 de la feature 5, creación de la función de login con usuario y contraseña, y tests unitarios para la misma.`
>
> `feat(auth): integración de filtro JWT y validación de tokens en endpoints protegidos`
>
> `refactor(api)!: modificación de endpoints REST de incidencias para requerir paginación obligatoria`
> `BREAKING CHANGE: Las peticiones a /api/incidents ahora requieren page y size en la query.`
