---
name: odd-clarify
description: Clarifica y amplía features abiertas de ODD desde un issue o prompt. Aplica impacto en cascada (HU → RF → P → T) sin reiniciar tareas completadas, reabre la puerta de revisión y reanuda el TDD.
---

# Skill `/odd-clarify`: Clarificación y Evolución de Features Abiertas

## Propósito
Incorporar cambios sobre una feature con `Estado` distinto de `Completada`, manteniendo la trazabilidad en cascada sin corromper el trabajo ya hecho.

---

## Cuándo se entra
* **Manual:** `/odd-clarify <feature>`.
* **Desde issue o prompt:** si lo pedido encaja con una feature **abierta**, entra aquí en vez de crear una nueva.
* Si la feature está **cerrada**, no uses esta skill: < 50 líneas → `/odd-fast` con `Relacionado con: Feature NNN`; funcionalidad relevante → nueva feature Amarilla con `Antecedentes: Feature NNN`; cambio troncálico → Rojo con `/adr`.

---

## Procedimiento Operativo

### Paso 1: Análisis de Impacto
1. Localiza `features/NNN-nombre/feature.md`.
2. Determina qué cambia: ¿un `RF-XX` existente, uno nuevo, una `HU-XX` nueva? ¿Qué `P-XX` y `T-XX` se derivan?
3. Comprueba que el cambio no empuja la feature al Carril Rojo (breaking change o > 500 líneas): si es así, para y propón `/adr`.

### Paso 2: Protección de lo Completado
* Las tareas `[X]` **no se eliminan ni se reinician**.
* Si el cambio exige tocar código ya entregado, se añade una tarea evolutiva (`T-05: Adaptar DTO para nuevo campo según RF-03`), nunca se reabre la anterior.

### Paso 3: Actualización en Cascada con `odd-writer`
Delega la actualización de `feature.md`: nuevo `HU-XX`/`RF-XX`, su `P-XX`, y las `T-XX` pendientes. Mantén intactos encabezado, historial y tareas cerradas.

### Paso 4: Puerta de Revisión
`Estado: En mejora` en `feature.md` y `En mejora` en `constitution/roadmap.md`. Después, la misma puerta que en `/odd-feature`:
* `gated` → OK explícito del usuario.
* `auto` → dictamen de `odd-reviewer` (modelo distinto), límite 3+1.

Sin aprobación no se implementa el cambio.

### Paso 5: Reanudación
Marca `Estado: En curso` y continúa con las tareas nuevas con el mismo esquema que `/odd-feature`: un subagente por tarea, TDD, commit por tarea, 3+1. Al terminar, `/odd-validate` y commit final.
