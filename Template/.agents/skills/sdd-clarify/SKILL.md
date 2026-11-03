---
name: sdd-clarif
description: Utiliza esta skill cuando debas revisar los documentos de una feature usando sdd (spec, plan o tasks) para detectar y resolver ambigüedades o incoherencias.
---

# Clarificación de una Spec

## Objetivo
Validación y resolución de dudas, ambigüedades o incoherencias en `spec.md`, `plan.md` o `tasks.md`.

## Entradas
- Número de la feature, nombre o ambas cosas.
- Archivo de la feature a revisar (si no se indica ninguno, suponer que es `spec.md`).

## Salidas
- Lista numerada con los puntos detectados.
- Pregunta al usuario sobre si le parece bien y qué cambios desea aplicar.
- En caso de aprobación: archivo actualizado y commit correspondiente.

## Proceso
1. Revisa el documento indicado y genera una lista estructurada con los siguientes elementos según aplique:
   **Para `spec.md`:**
   - Ambigüedades restantes (requisitos no verificables).
   - Contradicciones entre requisitos.
   - Casos límite no cubiertos.
   - Conflictos con `/constitution`.
   **Para `tasks.md`:**
   - Tareas faltantes.
   - Tareas contradictorias.
   - Tareas bloqueantes no indicadas.
   - Conflictos con `/constitution`, `spec.md` o `plan.md`.
   **Para `plan.md`:**
   - Enfoque erróneo o técnicamente inviable.
   - Decisiones contradictorias.
   - Riesgos no tenidos en cuenta.
   - Estrategia de tests errónea o incompleta.
   - Conflictos con `/constitution` o `spec.md`.
2. Muestra la lista en el chat y pregunta al usuario si le parece bien y si desea que se apliquen las correcciones propuestas.
3. Espera la confirmación y directrices explícitas del usuario.
4. Si el usuario aprueba los cambios, aplícalos directamente en el archivo correspondiente (`spec.md`, `plan.md` o `tasks.md`).
5. Tras aplicar las modificaciones aprobadas, realiza un commit individual siguiendo la norma establecida.

## Reglas
- Usa la skill sdd.
- Actúa como un QA profesional.
- No modifiques ningún archivo de forma unilateral: detecta primero y espera siempre la aprobación explícita del usuario antes de tocar el documento.
- Tras aplicar los cambios aceptados, es obligatorio realizar el commit correspondiente según la norma de `sdd`.