---
name: odd-decisions-review
description: Úsala para la revisión periódica de decisiones técnicas tomadas en ODD (tanto en docs/fast/ como en docs/NNN-nombre/decisions.md) y consolidar aquellas de impacto transversal en docs/approved-decisions.md.
---

# Skill `/odd-decisions-review`: Revisión Periódica de Decisiones ODD

## Propósito
Auditar periódicamente las decisiones técnicas inéditas registradas tanto en la bitácora ágil de `docs/fast/` como en las carpetas de feature `docs/NNN-nombre/decisions.md`, identificando patrones transversales para promoverlos al catálogo global `docs/approved-decisions.md`.

---

## Procedimiento Operativo

### Paso 1: Recopilación de Fuentes
1. Si `docs/approved-decisions.md` no existe en el proyecto, cópialo e inicialízalo desde `.agents/skills/odd-decisions-review/assets/approved-decisions.md`.
2. Lee `docs/approved-decisions.md` para conocer las reglas globales consolidadas.
3. Examina:
   - Archivos en `docs/fast/` que contengan decisiones técnicas registradas en su sección 3.
   - Archivos `docs/NNN-nombre/decisions.md` de las features implementadas.

### Paso 2: Análisis y Detección de Patrones
1. Identifica decisiones que se hayan repetido en más de una feature o fix rápido.
2. Detecta posibles discrepancias o contradicciones entre convenciones adoptadas en diferentes carriles.
3. Clasifica las candidatas a consolidación en las categorías del catálogo: *Errores*, *Persistencia*, *Testing/TDD*, *Seguridad*, *APIs/DTOs*, *Convenciones Generales*.

### Paso 3: Propuesta al Usuario
Muestra en el chat un resumen estructurado con:
- Decisiones puramente locales (permanecen solo en su feature o fix).
- **Decisiones candidatas a promoción global:** especificando ID, categoría, regla prescriptiva y qué tareas las originaron.
- Contradicciones detectadas (si las hay) solicitando la decisión del usuario.

### Paso 4: Actualización y Registro
1. Tras la aprobación del usuario, actualiza `docs/approved-decisions.md`.
2. Realiza un commit con el mensaje estándar: `docs(decisions): consolidación periódica de decisiones aprobadas ODD`.
