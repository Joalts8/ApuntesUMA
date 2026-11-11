---
name: odd
description: Orquestador principal de Organic-Driven Development (ODD). Clasifica cualquier petición o ticket de Jira/GitHub/Linear en Carril Verde (Fast), Amarillo (Lean) o Rojo (Epic), comprueba antecedentes y enruta el flujo de desarrollo de forma autónoma.
---

# Skill `/odd`: Orquestador Organic-Driven Development

## Propósito
Recibir una necesidad de desarrollo (procedente de un prompt libre, un issue de GitHub, un ticket de Jira o Linear), clasificarla según su naturaleza e impacto en el carril correspondiente y coordinar la ejecución del flujo con cero burocracia innecesaria.

---

## Procedimiento Operativo

### Paso 1: Recepción e Inspección
1. Inspecciona la petición del usuario y busca si hace referencia a código o features existentes:
   - Revisa `features/` para detectar si el trabajo amplía una feature abierta.
   - Revisa `docs/fast/` y `docs/` para ver si hay antecedentes relacionados.
2. Si la petición está vinculada a una feature **aún abierta**, desvía el flujo automáticamente a `/odd-clarify`.

### Paso 2: Clasificación de Carril

| Criterio | Carril Asignado | Acción de Orquestación |
| :--- | :--- | :--- |
| **< 50 líneas, bugfix, typo, chore o refactor menor** | 🟢 **Carril Verde (Fast-Track)** | Invoca `/odd-fast`. Sin preguntas de clarificación. TDD directo. |
| **50 - 500 líneas, feature estándar, nueva entidad o API** | 🟡 **Carril Amarillo (Feature Lean)** | Invoca `/odd-feature`. Máx 5 preguntas. Documento unificado `feature.md`. |
| **> 500 líneas, cambio arquitectural, breaking change o nueva BD** | 🔴 **Carril Rojo (Epic)** | Requiere ADR (`/adr`), separación formal y auditoría exhaustiva. |

### Paso 3: Comunicación al Usuario
Presenta al usuario un mensaje conciso informando del carril seleccionado y los antecedentes detectados antes de proceder a la ejecución.
