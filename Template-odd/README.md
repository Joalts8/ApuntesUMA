# Plantilla Organic-Driven Development (ODD) con Agentes

> Plantilla ligera y adaptativa basada en **Organic-Driven Development (ODD)**: elimina la burocracia documental innecesaria, sustituye los artefactos pesados por trazabilidad en cascada y clasifica cualquier cambio en 3 carriles de flujo continuo (Verde, Amarillo y Rojo).

> [!NOTE]
> **Este `README.md` es documentación de la plantilla y NO se copia al proyecto nuevo.** El proyecto que crees a partir de `Template-odd` debe tener su propio `README.md` con la descripción real del producto. Las rutas, numeraciones de feature y cifras que aparecen aquí son ilustrativas.

---

## 1. Filosofía ODD vs SDD Tradicional

En el desarrollo tradicional dirigido por especificaciones (**SDD**, disponible en [`Template-sdd`](../Template-sdd)):
* Toda tarea, grande o pequeña, pasa por la separación estricta en 4 documentos: `spec.md`, `plan.md`, `tasks.md` y `qa-note.md`.
* Esto aporta máxima gobernanza en sistemas críticos, pero genera sobrecarga y fricción ("burocracia de tokens") en correcciones pequeñas, refactors o features estándar.

En **Organic-Driven Development (ODD)**:
1. **Clasificación Automática por Carriles:** El sistema analiza el ticket o prompt y asigna el carril adecuado (Fast-Track, Feature Lean o Epic).
2. **Cero Burocracia en Tareas Menores (Carril Verde):** Bugs y ajustes (<50 líneas) se resuelven directamente con TDD y un registro ágil en `docs/fast/`.
3. **Documento Unificado y Trazabilidad en Cascada (Carril Amarillo):** Las features habituales combinan spec, plan y tareas en un único archivo (`feature.md`), con historias de usuario y trazabilidad completa (`HU-XX` → `RF-XX` → `P-XX` → `T-XX`).
4. **Resiliencia Automática y Subagentes de Bajo Consumo:** 
   - La redacción pesada de documentación se delega en subagentes ultra-ligeros (`odd-writer`) que usan modelos Flash en esfuerzo bajo (`effort: low`) para no malgastar cuota de razonamiento.
   - La revisión crítica (diseño y validación) la ejerce `odd-reviewer`, siempre con un modelo distinto al que implementa: mismo modelo y mismo hilo no es una revisión.
   - Si la validación falla, se activa automáticamente el bucle de reparación (`repair.md`) con límite de reintentos 3+1.
5. **Memoria de Decisiones Evolutiva:** Reutilización automática de decisiones aprobadas globales y de features previas relacionadas.

---

## 2. Reutilización desde `Template-sdd` (Sin Redundancias)

Para evitar duplicar código y configuraciones estándar en el repositorio, **`Template-odd` no incluye archivos genéricos ni carpetas vacías**. Al inicializar un proyecto real basado en ODD, debes reutilizar de [`Template-sdd`](../Template-sdd) los siguientes componentes:

| Componente en `Template-sdd` | Propósito y Reutilización |
| :--- | :--- |
| **`.github/workflows/`** | Copiar `security.yml` (DevSecOps con Trivy, TruffleHog, Semgrep y Strix) e `issue-triage.yml`. Configurar `ci.yml` y `cd.yml` según la guía. |
| **`.github/dependabot.yml`** | Configuración de actualizaciones automáticas de dependencias de Git y Actions. |
| **`.github/ISSUE_TEMPLATE/`** | Plantillas para bugs, features y tareas técnicas (compatibles 100% con los carriles ODD). |
| **`.github/pull_request_template.md`** | Plantilla de Pull Requests estandarizada. |
| **`docker/Dockerfile`** | Dockerfile de referencia multi-etapa y hardening para producción. |
| **Skill `sdd-constitution`** | Reutilizada de `Template-sdd` para inicializar la constitución (`mission.md`, `tech-stack.md` y `roadmap.md`) al arrancar el proyecto desde cero. |
| **Skill `adr`** | Reutilizada de `Template-sdd` para registrar formalmente Architectural Decision Records (`docs/ADR/`) en el Carril Rojo. |
| **Skills SDD Monolíticas (`/sdd-spec`, `/sdd-plan`, `/sdd-task`, `/sdd-implement`, `/sdd-validate`)** | Se usan tal cual desde `Template-sdd` únicamente si un Epic del Carril Rojo es indivisible y se aborda de forma monolítica con separación formal de archivos (no se requiere `/sdd-rev` pues `/odd-validate` asume la auditoría de código huérfano; tampoco `/sdd-status` ya que `/odd` gestiona el estado automáticamente en `roadmap.md`). **Nota sobre la regla 'Usa la skill sdd':** No es necesario modificar las skills de SDD; esa directriz queda cubierta automáticamente porque `Template-odd/AGENTS.md` y `/odd` ya incorporan las normas transversales de TDD, resiliencia 3+1 y memoria de decisiones, o el agente lee `sdd/SKILL.md` como biblioteca de referencia documental. |
| **Skills de Modificación SDD (`/sdd-clarify`, `/sdd-change`)** | Reutilizadas de `Template-sdd` cuando se requiere responder dudas o propagar cambios de requisitos sobre features que usan archivos separados (`spec.md`, `plan.md`, `tasks.md`), dado que `/odd-clarify` opera exclusivamente sobre el formato unificado `feature.md`. |
| **Archivos Raíz** | `.gitignore`, `.env.example`, `LICENSE`, `SECURITY.md`, `CONTRIBUTING.md` y `CODE_OF_CONDUCT.md`. |

---

## 3. Estructura de un Proyecto ODD en Producción

Cuando un proyecto ODD está en funcionamiento, su estructura de directorios queda conformada de la siguiente manera:

```text
<raíz-del-proyecto>/
├── .github/                 ← [Reutilizado de Template-sdd] Workflows, plantillas de issues y PRs
├── .agents/                 ← Configuración de agentes ODD
│   ├── agents/              ← Subagentes especializados de bajo consumo
│   │   ├── odd-writer.md    ← Redactor Flash (effort: low) para feature.md, summary.md, repair.md y qa-note.md
│   │   └── odd-reviewer.md  ← Auditor de diseño (puerta) y de validación (matriz RF↔test y código huérfano)
│   └── skills/              ← Habilidades ODD (odd, odd-fast, odd-feature, odd-clarify, odd-validate, odd-decisions-review)
├── constitution/            ← Reglas estables del proyecto (mission.md, tech-stack.md, roadmap.md)
├── docs/                    ← Documentación viva y memoria técnica
│   ├── approved-decisions.md ← Catálogo global de decisiones consolidadas
│   ├── fast/                ← Bitácora ágil del Carril Verde (YYYYMMDD-<tipo>-<nombre>.md)
│   ├── ADR/                 ← Architectural Decision Records (solo para decisiones críticas del Carril Rojo)
│   └── NNN-nombre-feature/  ← Entregables de feature: decisions.md, summary.md, qa-note.md
├── features/                ← Especificaciones activas
│   └── NNN-nombre-feature/  ← Carpeta de feature con su documento unificado 'feature.md'
└── src/                     ← Código fuente del proyecto
```

> [!IMPORTANT]
> **Los subagentes están pensados para Antigravity CLI y hay que adaptarlos si usas otro CLI:**
> - `.agents/agents/odd-writer.md` y `.agents/agents/odd-reviewer.md` usan campos propios de Antigravity/Gemini: `model: flash`, `effort: low|high` y `tools.read_tools/write_tools/subagent_tools`. Claude Code, Cursor, Codex u OpenCode no interpretan esos campos: tradúcelos a su modelo y permisos equivalentes (en OpenCode, por ejemplo, `model: <proveedor>/<modelo>` y reglas `tools`/`permissions` en la definición del agente).
> - La estrategia de bajo consumo se mantiene en cualquier CLI, pero debes apuntar `odd-writer` al modelo rápido/barato de tu proveedor y `odd-reviewer` al de mayor razonamiento.
> - Las skills `odd-*`, los `assets/` y los documentos de ODD no dependen del CLI: se reutilizan tal cual; solo cambia cómo los carga e invoca cada herramienta.
> - Como este README no se copia al proyecto, la regla de adaptación también vive en `AGENTS.md` y en la sección **Portabilidad** de `odd-writer.md` y `odd-reviewer.md`, que sí se copian.

> [!NOTE]
> **Cómo se invocan las skills y por qué `/odd` puede usar las subskills:**
> - Una skill no es una función: el agente **lee su `SKILL.md` y lo sigue**. `/odd` funciona porque invoca las subskills en el sentido de *cargar y ejecutar su procedimiento*, no de llamar a un API.
> - En **OpenCode** se invocan con `@` (`@odd`, `@odd-feature`); en **Claude Code, Cursor o Antigravity** con `/` (`/odd`, `/odd-feature`); en lenguaje natural basta con pedirlo por su nombre.
> - Si tu CLI solo permite cargar una skill por turno, invoca tú la subskill (`@odd-feature`) o pídele al agente que lea la ruta (`@.agents/skills/odd-feature/SKILL.md`).
> - Las plantillas viven en los `assets/` de cada skill. Ninguna skill redacta a mano: `odd-writer` instancia la plantilla, sustituye todos los `<…>` y elimina las notas de ejemplo.

---

## 4. Los 3 Carriles de Trabajo

```
Ticket / Petición del Usuario
              │
              ▼
   ¿Qué tipo de trabajo es?
              │
  ┌───────────┼───────────┐
  ▼           ▼           ▼
VERDE     AMARILLO      ROJO
(Fast)     (Lean)      (Epic)
```

### 🟢 Carril Verde: Fast-Track (Hotfixes, Chores, Ajustes < 50 líneas)
* **Objetivo:** Resolver rápido sin burocracia documental.
* **Proceso:**
  1. No hay preguntas de clarificación previas.
  2. Implementación directa con **TDD (Red-Green-Refactor)**: test primero, código después.
  3. Resiliencia: Límite de 3 reintentos + 1 diagnóstico antes de escalar al usuario.
  4. Documentación: Se genera un archivo conciso en `docs/fast/<YYYYMMDD>-<tipo>-<nombre>.md` mediante el subagente `odd-writer`.
  5. **Sin `qa-note.md`:** El test unitario es la garantía.
  6. Commit único al cerrar. **No se registra en `roadmap.md`.**

### 🟡 Carril Amarillo: Feature Lean (Funcionalidades Típicas / Estándar)
* **Objetivo:** Trazabilidad completa sin fragmentar en múltiples archivos.
* **Proceso:**
  1. **Documento Único (`features/NNN-nombre/feature.md`):** Combina Contexto, Historias de Usuario (`HU-XX`), Requisitos EARS (`RF-XX`), Enfoque Técnico (`P-XX`) y Tareas atómicas (`T-XX`).
  2. **Preguntas de Clarificación:** Máximo 5 preguntas clave antes de implementar (en fábrica se aplican defaults y se registran).
  3. **Puerta de Revisión:** El `feature.md` se revisa antes de escribir código: OK explícito del usuario (modo `gated`) o dictamen del subagente `odd-reviewer` (modo `auto`). Sin `Estado: Aprobada` no hay TDD.
  4. **Consulta de Memoria de Decisiones:**
     - Nivel 1: `docs/approved-decisions.md` (no se vuelve a registrar).
     - Nivel 2: `docs/NNN/decisions.md` de features anteriores relacionadas.
     - Nivel 3: Decisiones nuevas se registran en `docs/NNN-nombre/decisions.md`.
  5. **Implementación TDD:** Un subagente por tarea principal (en paralelo las independientes), commit por tarea y resiliencia 3+1.
  6. **Entregables:** `docs/NNN-nombre/summary.md` y `docs/NNN-nombre/qa-note.md` (redactados por `odd-writer` con modelo bajo).

### 🔴 Carril Rojo: Epic / Cambio Arquitectónico Complejo
* **Objetivo:** Máxima seguridad y control en cambios troncales, breaking changes o migraciones de alto impacto (> 500 líneas).
* **Gestión de la Arquitectura:** Se formaliza obligatoriamente mediante un **Architectural Decision Record** en `docs/ADR/NN-titulo.md` (utilizando la skill `/adr` de `Template-sdd`).
* **¿Cómo se implementa en ODD?**
  Existen dos estrategias complementarias según la preferencia del equipo:
  1. **Descomposición Orgánica (Recomendada en ODD):** El cambio grande se define a alto nivel en el ADR y se descompone en **N features estándar (Carril Amarillo)**. Cada feature se ejecuta de forma independiente con su `feature.md`, TDD, `qa-note.md` y validación (`/odd-validate`), reduciendo el riesgo de cambios gigantes.
  2. **Ejecución Formal SDD (solo si no es separable):** Si el cambio debe entregarse como un bloque indivisible y requiere separación documental estricta, se ejecutan las skills especializadas de `Template-sdd` (`/sdd-spec`, `/sdd-plan`, `/sdd-task`, `/sdd-implement`, `/sdd-validate`). En este caso se aplican las puertas de SDD y su validación (`/sdd-validate`, con `sdd-qa-writer`) también emite el `qa-note.md`.

---

### Bucle de Trabajo (5 pasos)

```text
Issue / prompt
   │
   1. Clasificar (Verde / Amarillo / Rojo)
   │
   2. Crear los .md del carril  ──►  PUERTA (odd-reviewer en auto / OK humano en gated)
   │                                  └── Rojo indivisible: SDD puro
   3. TDD con subagente por tarea + commit por tarea
   │      └── /odd-validate ──► qa-note.md (odd-writer, modelo bajo)
   │
   4. Commit final
   │
   5. Clarificación (fuera del bucle, solo si cambian los requisitos)
```

* **Paso 5:** `/odd-clarify` se invoca manualmente o cuando un issue/prompt afecta a una feature abierta. Si la feature ya está cerrada, se resuelve con Carril Verde, nueva feature Amarilla o Rojo con ADR.
* **Revisión de decisiones:** `/odd-decisions-review` **no pertenece al bucle**. Se ejecuta aparte, por ejemplo cada quincena.

### Recetas de Uso Real (ejemplos ilustrativos)

> Los nombres, números de feature (`012`, `016`...), ADR y tamaños son ejemplos para mostrar el flujo. Adáptalos al proyecto real.

En los dos modos el esqueleto es el mismo. Cambian solo tres cosas: preguntas → defaults, OK humano → `odd-reviewer`, alerta → `Estado: Bloqueada`.

#### 🟢 Verde — «el IVA del carrito resta de más» (≈8 líneas)
1. `@odd` clasifica **Verde** (no hay preguntas de clarificación).
2. `odd-fast`: test que reproduce el bug (rojo) → fix mínimo (verde) → refactor.
3. `odd-writer` crea `docs/fast/20261119-fix-iva-carrito.md` desde `odd-fast/assets/fast-log.md`.
4. Commit único. Sin roadmap, sin `qa-note.md`, sin puerta.

**Fábrica:** idéntico. Única diferencia: si se agota el 3+1, se detiene con diagnóstico en vez de pedir ayuda.

#### 🟡 Amarillo — «exportar pedidos a CSV» (≈200 líneas)
1. `@odd` clasifica **Amarillo**.
2. Clarificación: hasta 5 preguntas (columnas, encoding, filtro de fechas) y consulta de decisiones Nivel 1 y 2.
3. `odd-writer` crea `features/012-export-csv/feature.md` desde `odd-feature/assets/feature.md`: `HU-01..03`, `RF-01..06`, `P-01..03`, `T-01..05`.
4. **Puerta:** te enseña el resumen y das el OK → `Estado: Aprobada` + roadmap en `Siguiente`.
5. `Estado: En curso` + roadmap `En curso`: subagente por tarea (`T-01` y `T-02` en paralelo, `T-03` espera a `T-01`), commit por tarea.
6. `@odd-validate`: tests, linter, matriz `RF`↔test y código huérfano → `qa-note.md` y `summary.md` con `odd-writer` → `Estado: Completada` + roadmap `Hecho` (cobertura) → commit final.

**Fábrica:** las preguntas se sustituyen por defaults registrados en `decisions.md`; la puerta y la auditoría de validación las da `odd-reviewer` con otro modelo. Si surge una decisión crítica (p. ej. «¿exportamos datos personales?»), la feature pasa a `Estado: Bloqueada` con el diagnóstico en la sección 6 de `feature.md` y el worker se detiene.

#### 🔴 Rojo separable — «pasar a multi-tenant» (≈2000 líneas)
1. `@odd` clasifica **Rojo**.
2. `@adr` → `docs/ADR/007-multi-tenant.md`, con la misma política de puerta que cualquier otra fase.
3. Se descompone en features Amarillas (`013`, `014`, `015`).
4. Cada una ejecuta la receta Amarilla completa, con su `feature.md`, su puerta y su `qa-note.md`.

**Fábrica:** el ADR se audita con `odd-reviewer`; a partir de ahí, cada feature sigue el bucle Amarillo sin cambios.

#### 🔴 Rojo indivisible — «rotar el esquema de BD en caliente, sin trocear»
ODD solo clasifica y cede el mando; **no entra en `odd-feature` ni en `odd-validate`**:
1. `/sdd-spec` (entrevista + aprobación) → `/sdd-plan` (diseño previo + `sdd-plan-reviewer` en fábrica) → `/sdd-task` → `/sdd-implement` → `/sdd-validate`.
2. El `qa-note.md` lo redacta `sdd-qa-writer` con modelo bajo.
3. Vive en `features/NNN` con `spec.md`, `plan.md` y `tasks.md`. No se mezclan los dos formatos en la misma feature.

**Fábrica:** idéntico; las puertas son las de SDD y no las de ODD.

#### 🟡 Clarificación con feature ABIERTA — feature `012` en `En curso` + issue «añadir filtro por fecha»
1. `@odd-clarify 012` (o `@odd`, que enruta el issue a la feature abierta).
2. Impacto en cascada: nuevo `HU-04` → `RF-07` → `P-04` → `T-06`. Las tareas `[X]` no se tocan.
3. `Estado: En mejora` + roadmap `En mejora` → **puerta** → `Estado: En curso`.
4. Subagente para `T-06`, commit, y `@odd-validate` **revalida todo** (también los `RF` anteriores) → `qa-note.md` actualizado.

**Fábrica:** el enrutado a clarify es automático cuando el issue encaja con una feature abierta; la puerta la da `odd-reviewer`. Si el cambio empuja la feature por encima de 500 líneas o rompe compatibilidad, se replantea como Rojo.

#### ⚫ Clarificación con feature CERRADA — feature `010` en `Completada`
| Lo que llega | Carril | Resultado |
| :--- | :--- | :--- |
| «El CSV sale con BOM y rompe Excel» (≈8 líneas) | Verde | `odd-fast` con `Relacionado con: Feature 010` en el log |
| «Exportar también a XLSX» (≈150 líneas) | Amarillo nuevo | `features/016-export-xlsx` con `Antecedentes: Feature 010`; lee `docs/010/decisions.md` |
| «Cambiar el motor de export a streaming» (breaking) | Rojo | ADR + descomposición en features |

**Fábrica:** sin preguntas; si el caso baila entre dos carriles, toma el mayor y lo registra como decisión.

### Plantillas por Artefacto

| Artefacto | Plantilla |
| :--- | :--- |
| `features/NNN/feature.md` | `odd-feature/assets/feature.md` |
| `docs/NNN/decisions.md` | `odd-feature/assets/decisions.md` |
| `docs/fast/*.md` | `odd-fast/assets/fast-log.md` |
| `docs/NNN/summary.md` · `qa-note.md` · `repair.md` | `odd-validate/assets/*` |
| `docs/approved-decisions.md` | `odd-decisions-review/assets/approved-decisions.md` |
| ADR, `spec.md`, `plan.md`, `tasks.md` | assets de `Template-sdd` |

---

## 5. Modos de Ejecución: `gated` vs `auto`

ODD tiene un único flujo con dos políticas de puerta. Se elige al arrancar (`/odd <petición> --auto` o una sola pregunta) y sin respuesta explícita se usa `gated`:

| Aspecto | Modo `gated` (persona) | Modo `auto` (fábrica autónoma) |
| :--- | :--- | :--- |
| **Entrada de trabajo** | Prompt libre en chat o comando `/odd` | Ticket de Jira / GitHub Issue / Linear leído por API |
| **Clarificación** | Pregunta hasta 5 dudas al usuario en chat | Asume defaults y los registra en `decisions.md` |
| **Redacción de `.md`** | `odd-writer` o el propio agente | `odd-writer` obligatorio (modelo bajo) |
| **Puerta de revisión** | OK explícito del usuario | `odd-reviewer` con **modelo distinto** al implementador |
| **Auditoría de validación** | Recibe la matriz `RF`↔test y da el veredicto | `odd-reviewer` (modelo distinto) audita matriz y código huérfano antes de emitir el `qa-note.md` |
| **Si no hay modelo distinto** | — | Cambia el modelo del worker antes de revisar; si tampoco es posible, corre en `gated` |
| **Decisiones Nivel 1 y 2** | Se aplican en automático sin preguntar | Se aplican en automático sin preguntar |
| **Decisiones Nivel 3 (menores)** | Propone al usuario y registra en `decisions.md` | Aplica la mejor práctica y registra en `decisions.md` para auditoría |
| **Decisiones Nivel 3 (críticas)** | Consulta al usuario antes de proceder | `Estado: Bloqueada` + sección 6 de `feature.md` y detiene el worker |
| **Resiliencia en Tests (3+1)** | Pide ayuda al usuario si falla 4 veces | Detiene el worker y emite diagnóstico con traza del error |
| **Validación y Reparación** | Avisa del progreso y emite `qa-note.md` | Bucle automático: `repair.md` → TDD → verde → `qa-note.md` → commit |

---

## 6. Automatización de Flujos Especiales

### A) Evolutivos sobre Features Existentes
¿Qué ocurre cuando una nueva tarea está relacionada con una feature ya construida?
* **Si la feature aún está ABIERTA:** Se ejecuta `/odd-clarify` sobre su `feature.md` (invocación manual o enrutado automático cuando el issue encaja con una feature abierta). Se añade el nuevo requisito funcional y la cascada genera automáticamente las tareas adicionales sin reiniciar ni tocar lo ya completado. El estado pasa a `En mejora` y el cambio vuelve a pasar por la puerta de revisión antes de implementarse.
* **Si la feature ya está CERRADA:**
  - *Ajuste menor (< 50 líneas):* Va por **Carril Verde (Fast-Track)**. Se documenta en `docs/fast/` indicando `Relacionado con: Feature NNN`.
  - *Nueva funcionalidad relevante:* Va por **Carril Amarillo (Feature Lean)**. Se crea una nueva carpeta `features/MMM-nueva-feature/` indicando `Antecedentes: Feature NNN`. El agente lee automáticamente `docs/NNN/decisions.md` para mantener los mismos patrones técnicos.
  - *Breaking change o refactor troncal:* Va por **Carril Rojo (Epic)** con ADR.

### B) Ciclo Automático de Reparación (`repair.md`)
Si la validación de una feature falla (tests rotos, linter, criterios de aceptación insatisfechos):
1. **Generación de `repair.md`:** El validador crea automáticamente `docs/NNN-nombre/repair.md` indicando:
   - Tests o criterios fallidos.
   - Causa raíz diagnosticada.
   - Tareas correctivas (`R-01`, `R-02`...).
2. **Cambio de Estado:** En `feature.md`, el estado pasa a `En reparación`.
3. **Corrección TDD (Límite 3+1):** El agente implementador ejecuta las tareas de reparación con límite de 3 reintentos automáticos.
4. **Cierre Limpio:** Al superar todos los tests en verde, se actualiza `summary.md`, se elimina `repair.md` (no se deja basura transitoria) y se emite la `qa-note.md` definitiva.
5. **Auditoría de cierre (modo `auto`):** Antes de emitir el `qa-note.md`, `odd-reviewer` (modelo distinto) revisa la matriz `RF`↔test y el código huérfano. Su `REQUIERE AJUSTES` equivale a validación fallida aunque los tests estén en verde.

### C) Subagentes de Redacción (`odd-writer`) y Auditoría (`odd-reviewer`)
Para evitar el consumo excesivo de tokens y preservar la ventana de contexto del agente principal:
* La redacción y actualización de archivos Markdown (`feature.md`, `summary.md`, `repair.md`, `qa-note.md`, y bitácoras en `docs/fast/`) se delega en `.agents/agents/odd-writer.md`.
* Corre con el modelo `flash` y parámetro `effort: low`, optimizando costes y garantizando rapidez. Si el CLI no soporta subagentes, se cambia el modelo del worker durante la redacción.
* `odd-writer` es de solo escritura de plantillas: no decide, no inventa requisitos ni rellena huecos. Si un `RF` no tiene test, lo escribe como no cubierto.
* `odd-reviewer` es su contraparte de solo lectura: audita con un modelo distinto y nunca redacta artefactos.

### D) Revisión de Decisiones (fuera del bucle)
`/odd-decisions-review` no forma parte del flujo de una feature. Se ejecuta aparte, con la cadencia que decida el equipo (por ejemplo, cada quincena), para consolidar en `docs/approved-decisions.md` los patrones que se repiten entre features y bitácoras.

---

## 7. Comandos y Skills ODD Disponibles

| Skill / Comando | Ubicación | Descripción |
| :--- | :--- | :--- |
| **`odd`** | `.agents/skills/odd/` | Orquestador principal: recibe el ticket/prompt, clasifica el carril, fija el modo (`gated`/`auto`) e invoca las subskills del bucle con sus puertas. No redacta artefactos. |
| **`odd-fast`** | `.agents/skills/odd-fast/` | Ejecuta el Carril Verde para fixes inmediatos con TDD, log en `docs/fast/` y commit único. |
| **`odd-feature`** | `.agents/skills/odd-feature/` | Ejecuta el Carril Amarillo: redacta `feature.md` con historias de usuario, pasa la puerta, implementa con un subagente por tarea y commits. |
| **`odd-clarify`** | `.agents/skills/odd-clarify/` | Clarifica o amplía una feature abierta (manual o desde issue/prompt) con impacto en cascada y nueva puerta de revisión. |
| **`odd-validate`** | `.agents/skills/odd-validate/` | Valida tests, linter, matriz RF↔test y código huérfano; audita con `odd-reviewer` en `auto` y emite `qa-note.md` con modelo bajo o inicia `repair.md`. |
| **`odd-decisions-review`** | `.agents/skills/odd-decisions-review/` | **Fuera del bucle** (p. ej. quincenal): consolida decisiones de features en `docs/approved-decisions.md`. |
| **`adr`** | `Template-sdd/.agents/skills/adr/` | [Reutilizada de SDD] Genera un Architectural Decision Record para cambios del Carril Rojo. |
