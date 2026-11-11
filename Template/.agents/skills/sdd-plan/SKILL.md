---
name: sdd-plan
description: Esta skill se usará a la hora de crear el plan a seguir para una feature con el flujo SDD, tanto en modo interactivo con humanos como en modo automático para la fábrica.
---

# Creación de un plan para una feature

## Objetivo
Crear el plan técnico a seguir para implementar una feature en el ciclo SDD, validando el diseño conceptual antes de redactar el documento final.

## Entradas
- Número de la feature, nombre o ambas cosas.
- Información extra provista por el usuario o ticket.
- Archivo `spec.md` de la feature y reglas en `/constitution` (`tech-stack.md`, `mission.md`).
- Catálogo global de decisiones en `docs/approved-decisions.md` (si no existe, se inicializa desde `.agents/skills/sdd-decisions-review/assets/approved-decisions.md`).
- Registro de decisiones de features previas en `docs/NNN-nombre-feature/decisions.md`.
- Especificaciones previas del proyecto en `/features`.
- Documentación técnica y decisiones de arquitectura en `/docs` (incluyendo `docs/ADR/` y resúmenes `docs/NNN-nombre-feature/NN-task.md`).
- Código fuente implementado en `src/` y suite de pruebas asociadas.
- **Modo de ejecución:** `interactivo` (humano en chat) o `auto` (fábrica automática).

## Salidas
- Propuesta de diseño técnico previo.
- Archivo `features/NNN-nombre-feature/plan.md`.
- Archivo `docs/NNN-nombre-feature/decisions.md` (registrando únicamente las **nuevas decisiones** tomadas durante el plan).
- Commit individual siguiendo Conventional Commits en español (`docs(plan): plan técnico para feature NNN`).

## Proceso

### 1. Inspección de decisiones previas y componentes existentes
- Lee `spec.md` y los límites técnicos en `constitution/tech-stack.md`.
- **Cadena de consulta de decisiones (Fábrica Autónoma y Manual):**
  1. Consulta en primer lugar `docs/approved-decisions.md`. Si ya existe una decisión aprobada para el problema (ej. formato de errores, manejo de fechas, estrategia de mocks), aplícala automáticamente (Nivel 1 de autonomía). **No la registres en `decisions.md`** (ya es una decisión global conocida).
  2. Si no está en el catálogo global, revisa los archivos `docs/NNN-nombre-feature/decisions.md` de las features recientes aún no revisadas para reutilizar criterios consistentes. Si encuentras un criterio aplicable, reutilízalo y **no lo registres de nuevo en `decisions.md`**.
  3. **Solo registrar decisiones nuevas:** Si surge una decisión técnica inédita menor (Nivel 2), prepárala para registrarla en `docs/NNN-nombre-feature/decisions.md`. Si es una decisión crítica o de arquitectura mayor (Nivel 3), márcala para confirmación.
- Revisa el código existente en `src/`, las features previas en `features/` y la documentación técnica en `docs/` para no duplicar utilidades ni lógica.

### 2. Formulación del diseño técnico previo
Plantea un diseño técnico previo de alto nivel:
- Enfoque general y encaje con la arquitectura actual.
- Decisiones técnicas adoptadas (indicando cuáles provienen de la memoria del proyecto y cuáles son nuevas con sus alternativas descartadas).
- Componentes, archivos y funciones que se crearán o modificarán.
- Estrategia de tests automatizados (TDD: tests unitarios y de integración con BD levantada).

### 3. Validación del diseño según el Modo de Ejecución

#### A. Modo Humano (Interactivo)
- Plantea el diseño previo en el chat y solicita confirmación explícita del usuario.
- Ajusta detalles según el feedback recibido antes de redactar el plan formal.

#### B. Modo Auto (Fábrica Automática)
- Para evitar detener la cadena de automatización, invoca al subagente auditor [`sdd-plan-reviewer`](../agents/sdd-plan-reviewer.md) (configurado con modelo Flash y esfuerzo de razonamiento alto `high`):
  - El subagente revisor valida el diseño contra `constitution/tech-stack.md`, `mission.md` y `docs/approved-decisions.md`.
  - Verifica que no viole límites duros, no añada dependencias no autorizadas y respete TDD.
  - Si el subagente lo **aprueba**, la fábrica avanza inmediatamente al paso 4.
  - Si el subagente detecta **inconsistencias**, devuelve notas correctivas y se reformula el plan de forma autónoma. Solo si detecta una contradicción constitucional insalvable (Nivel 3 crítico), escala una alerta de detención al usuario.

### 4. Redacción formal de `plan.md` y creación de `decisions.md`
- Redacta `plan.md` utilizando `./assets/plan.md`.
- Si se tomaron decisiones técnicas nuevas, inicializa `docs/NNN-nombre-feature/decisions.md` a partir de `./assets/decisions.md`, documentando formalmente las decisiones inéditas acordadas. Si no hubo ninguna decisión nueva, no crees `decisions.md`.

### 5. Persistencia y commit
- Guarda en `/features/NNN-nombre-feature/plan.md` y, solo si hubo decisiones nuevas, en `docs/NNN-nombre-feature/decisions.md`.
- Realiza un commit individual siguiendo la norma del proyecto (`docs(plan): plan técnico de implementación feature NNN`).

## Reglas
- Si la spec no está aprobada o tiene dudas abiertas (`[NECESITA ACLARACIÓN]`), para y avisa antes de planificar.
- **Obligatorio consultar la memoria del proyecto:** Revisa siempre `docs/approved-decisions.md` y secundariamente los `decisions.md` no consolidados de otras features antes de inventar una solución nueva.
- **Registro de decisiones:** Toda decisión técnica **nueva** (no cubierta por el catálogo global ni por features previas) debe quedar reflejada en `docs/NNN-nombre-feature/decisions.md`. Si no surge ninguna decisión nueva, no se crea el archivo.
- No escribas código durante esta fase.
- Usa la skill sdd.
- Debe cubrir todos los requisitos funcionales (RF) y no funcionales (RNF) de la spec.
