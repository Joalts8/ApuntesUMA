---
name: sdd-decisions-review
description: Úsala para la revisión periódica (al cerrar un ciclo o hito de features) de las decisiones registradas en docs/NNN-nombre-feature/decisions.md y consolidar las de impacto transversal en docs/approved-decisions.md.
---

# Revisión Periódica de Decisiones (sdd-decisions-review)

## Objetivo
Auditar las decisiones técnicas tomadas en las features desarrolladas en los últimos ciclos, detectar patrones o convenciones recurrentes, resolver posibles contradicciones entre features y promover aquellas decisiones con impacto transversal al catálogo global `docs/approved-decisions.md` para que la fábrica autónoma y los desarrolladores las reutilicen automáticamente.

## Entradas
- Catálogo global actual en `docs/approved-decisions.md` (si no existe, se inicializa a partir de la plantilla en `./assets/approved-decisions.md`).
- Archivos `docs/NNN-nombre-feature/decisions.md` de las features completadas o en curso.
- `constitution/tech-stack.md` (para validar alineación con los límites técnicos del proyecto).

## Salidas
- Propuesta en el chat con la lista de decisiones candidatas a consolidación global.
- Inicialización o actualización de `docs/approved-decisions.md` tras aprobación explícita del usuario.
- Actualización del checklist de revisión en cada `docs/NNN-nombre-feature/decisions.md` auditado.
- Commit siguiendo las normas del proyecto (`docs(decisions): consolidación periódica de decisiones aprobadas`).

## Proceso
1. **Recopilación y lectura:**
   - Si `docs/approved-decisions.md` no existe en el proyecto, cópialo e inicialízalo desde `./assets/approved-decisions.md`.
   - Lee `docs/approved-decisions.md` para conocer las decisiones que ya forman parte de la memoria del proyecto.
   - Recorre todos los directorios en `docs/NNN-nombre-feature/` buscando archivos `decisions.md` que tengan pendiente la casilla `[ ] Pendiente de revisión periódica`.
2. **Análisis y clasificación:**
   - Identifica decisiones marcadas con alcance transversal o aquellas que aparezcan repetidas o implícitas en más de una feature.
   - Detecta si hay inconsistencias o contradicciones entre decisiones de distintas features (por ejemplo, si una feature usó una convención de fechas y otra usó una distinta).
   - Clasifica las candidatas en las categorías estándar: *Manejo de Errores*, *Persistencia*, *Testing/Mocks*, *Seguridad*, *APIs/DTOs*, *Convenciones Generales*.
3. **Presentación de la propuesta al usuario:**
   - Muestra en el chat un resumen estructurado indicando:
     - Decisiones con alcance local exclusivo (se mantienen en la feature sin cambios).
     - **Propuesta de decisiones a consolidar globalmente:** indicando ID asignado, categoría, regla aplicable y qué features originaron la necesidad.
     - Contradicciones detectadas (si las hay) pidiendo al usuario que elija la opción definitiva.
   - Espera la confirmación y directrices explícitas del usuario.
4. **Consolidación y actualización:**
   - Una vez recibida la aprobación del usuario, incorpora las nuevas entradas en la tabla correspondiente de `docs/approved-decisions.md`.
   - Marca la casilla `[x] Revisado y evaluado para consolidación global` en los archivos `docs/NNN-nombre-feature/decisions.md` revisados.
   - Realiza un commit con el mensaje estándar: `docs(decisions): consolidación periódica de decisiones aprobadas`.

## Reglas
- No modifiques `docs/approved-decisions.md` de forma unilateral sin confirmación explícita del usuario.
- Si una decisión colisiona con `constitution/tech-stack.md`, prevalece siempre la constitución: la decisión no se consolida y se alerta al usuario de la discrepancia.
- Asegura que cada decisión promovida esté redactada de forma prescriptiva y clara, para que cualquier agente o la fábrica autónoma sepa exactamente qué regla aplicar en el futuro sin ambigüedades.
- Usa la skill sdd.
