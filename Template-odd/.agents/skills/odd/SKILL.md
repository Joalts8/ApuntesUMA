---
name: odd
description: Orquestador de Organic-Driven Development (ODD). Recibe un issue o prompt, elige carril (Verde, Amarillo o Rojo), redacta y revisa los artefactos, orquesta la implementación TDD con subagentes, valida y emite qa-note. Soporta modo auto (fábrica) y modo gated (aprobación humana entre fases).
---

# Orquestador Organic-Driven Development (`/odd`)

## Propósito
Punto de entrada del flujo ODD: fija el orden de los pasos y las puertas de revisión. Las normas estables viven en `AGENTS.md` y en `constitution/`; las plantillas, en los `assets/` de cada skill.

`/odd` **no redacta ningún artefacto**: cada paso invoca la subskill correspondiente (`odd-fast`, `odd-feature`, `odd-clarify`, `odd-validate`) y es esa subskill la que usa su plantilla de `assets/`.

---

## Modo de Ejecución

Se fija al arrancar: parámetro (`auto` / `gated`) o una sola pregunta. Sin respuesta explícita, `gated`.

| Aspecto | `gated` (persona) | `auto` (fábrica) |
| :--- | :--- | :--- |
| **Redacción de `.md`** | `odd-writer` o el propio agente | `odd-writer` obligatorio (modelo bajo) |
| **Puerta de revisión** | OK explícito del usuario en el chat | `odd-reviewer` con **modelo distinto** al implementador |
| **Decisión crítica (Nivel 3)** | Pregunta en chat y espera | `Estado: Bloqueada` + sección 6 de `feature.md` y detención |
| **Auditoría de validación** | Recibe la matriz `RF`↔test y da el veredicto | `odd-reviewer` (modelo distinto) audita matriz y código huérfano |
| **Fallback** | — | Si el CLI no permite modelo distinto para el revisor, cambia el modelo del worker antes de revisar; si tampoco, corre en `gated` |

La revisión nunca la hace el mismo modelo, en el mismo hilo, que va a implementar después: eso es una auto-aprobación, no una puerta.

---

## Bucle de Trabajo

### Paso 1: Entrada y Clasificación
De un issue, ticket o prompt extrae: descripción, requisitos, historias de usuario y criterios de aceptación. Clasifica:

| Carril | Criterio | Artefactos |
| :--- | :--- | :--- |
| 🟢 Verde | < 50 líneas, bug / chore / ajuste | `docs/fast/<YYYYMMDD>-<tipo>-<nombre>.md` |
| 🟡 Amarillo | 50-500 líneas, feature estándar | `features/NNN-nombre/feature.md` + `docs/NNN-nombre/` |
| 🔴 Rojo | > 500 líneas, breaking change o cambio troncálico | `docs/ADR/NN-*.md` + features derivadas |

Si duda entre dos carriles, elige el mayor.

### Paso 2: Artefactos y Puerta de Revisión
* **Verde → invoca la skill `odd-fast`:** no hay documento que revisar, así que no hay puerta. Va directo a TDD.
* **Amarillo → invoca la skill `odd-feature`:**
  1. Clarificación (máx. 5 preguntas; en `auto` se aplican defaults y se registran en `decisions.md`).
  2. `odd-writer` redacta `feature.md` desde `.agents/skills/odd-feature/assets/feature.md` (Contexto, Historias de Usuario, `RF-XX` EARS, `P-XX`, `T-XX`).
  3. **Puerta:** `odd-reviewer` (auto) o OK del usuario (gated). Se marca `Estado: Aprobada`.

  > Sin `Aprobada` no se escribe ni un test. `REQUIERE AJUSTES` → reescribe y repite (límite 3+1); `RECHAZADO` → para.
* **Rojo:**
  - **Separable:** invoca `/adr` con la decisión arquitectónica (misma política de puerta: OK humano en `gated`, `odd-reviewer` en `auto`) y descompón en N features Amarillas. Cada una pasa por `odd-feature` y su propia puerta.
  - **Indivisible:** se ejecuta **SDD puro** para esa feature (`/sdd-spec` → `/sdd-plan` → `/sdd-task` → `/sdd-implement`), con las puertas de SDD. No se duplica el flujo en ODD.

### Paso 3: Implementación y Validación
1. `odd-feature` lanza **un subagente por tarea** `T-XX`: en paralelo las independientes, secuenciales las bloqueadas. Commit por tarea y regla 3+1.
2. Al quedar todas en verde, invoca `odd-validate`: tests, linter, matriz `RF`↔test y auditoría de código huérfano.
3. En `auto`, `odd-reviewer` (modelo distinto) audita la matriz y el código huérfano **antes** de cerrar. `REQUIERE AJUSTES` equivale a validación fallida aunque los tests estén en verde.
4. Si pasa, `odd-writer` redacta `qa-note.md` con modelo bajo. Si falla, bucle `repair.md` y se vuelve a validar.
5. Rojo separable: se valida cada feature Amarilla con `odd-validate`. Rojo indivisible: `/sdd-validate`.

### Paso 4: Cierre
Commit final, `Estado: Completada` y roadmap a `Hecho` con el porcentaje de cobertura.

### Paso 5: Clarificación (posterior al bucle)
No forma parte del flujo inicial. Se entra de dos maneras:
* **Manual:** `/odd-clarify <feature>`.
* **Desde issue/prompt:** si lo pedido encaja con una feature **abierta** (`Estado` distinto de `Completada`), se usa `/odd-clarify`.
* Feature **cerrada:** < 50 líneas → Carril Verde con `Relacionado con: Feature NNN`; funcionalidad relevante → nueva feature Amarilla con `Antecedentes: Feature NNN`; cambio troncálico → Rojo con ADR.

Toda clarificación reabre la puerta del Paso 2 (`Estado: En mejora` → revisión → TDD).

---

## Normas Transversales

### 1. Transición Automática en `constitution/roadmap.md`
* **Verde:** **NO** se registra en `roadmap.md`; su trazabilidad vive en `docs/fast/`.
* **Amarillo y Rojo:** al aprobarse la feature → `Siguiente`; al empezar TDD → `En curso`; al clarificar → `En mejora`; al terminar tareas → `En validación`; si falla → `En reparación`; al pasar → `Hecho` con cobertura.

### 2. Memoria Técnica de Decisiones (3 Niveles)
1. **Catálogo Global** (`docs/approved-decisions.md`): se aplica de inmediato y **prohibido** volver a registrarla. Si el archivo no existe, se inicializa desde `.agents/skills/odd-decisions-review/assets/approved-decisions.md`.
2. **Features previas** (`docs/NNN/decisions.md` no consolidados): se reutiliza el criterio sin duplicarlo.
3. **Inéditas:** *menores* → se aplican y se registran en `docs/NNN/decisions.md`; *críticas* → se para (chat en `gated`, `Estado: Bloqueada` en `auto`).

> La consolidación periódica (`/odd-decisions-review`) está **fuera del bucle**: se ejecuta aparte, por ejemplo cada quincena.

### 3. Delegación de Redacción
Toda redacción y persistencia de Markdown (`feature.md`, `docs/fast/`, `summary.md`, `repair.md`, `qa-note.md`) se delega en `odd-writer` con modelo de bajo consumo (o en el propio agente si es `gated` y no hay subagentes). Si el CLI no soporta subagentes, se cambia el modelo del worker para esa redacción.

### 4. TDD y Resiliencia 3+1
Ciclo Red-Green-Refactor obligatorio en todo carril. Tras 3 fallos consecutivos de tests, en el 4º intento se detiene, se diagnostica y se escala.

### 5. Control de Código No Registrado (Rogue Code)
En la validación se audita que ningún código de producción quede sin `RF-XX` que lo respalde. Se justifica o se elimina.

### 6. Commits
Conventional Commits en español. Un commit por tarea completada y uno de cierre de feature.
