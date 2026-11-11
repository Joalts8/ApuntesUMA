---
name: odd-validate
description: Ejecuta la validación técnica y funcional de features en ODD. Si supera las pruebas, genera summary.md y qa-note.md mediante odd-writer. Si falla, genera automáticamente repair.md, pasa a estado 'En reparación', ejecuta el bucle de reparación TDD (3+1) y limpia el reporte transitorio tras el éxito.
---

# Skill `/odd-validate`: Validación y Bucle Automático de Reparación

## Propósito
Asegurar la calidad técnica y funcional de una feature desarrollada en Carril Amarillo o Rojo, auditando la ejecución de tests, linters y cobertura, y gestionando de forma autónoma el ciclo de recuperación en caso de anomalías.

---

## Procedimiento Operativo

### Paso 1: Ejecución de la Suite de Verificación
1. Ejecuta la suite de pruebas unitarias y de integración (`mvn test`, `pytest`, `npm test` o equivalente).
2. Ejecuta el análisis estático de código y linter (`checkstyle`, `eslint`, `ruff`).
3. Comprueba que todos los criterios de aceptación EARS de la feature se encuentren cubiertos por al menos un test.

---

### Paso 2: Bifurcación de Resultados

#### Escenario A: Validación Superada con Éxito (Todo en Verde)
1. Invoca al subagente `odd-writer` para redactar:
   - `docs/NNN-nombre/summary.md`: Resumen ejecutivo de la implementación.
   - `docs/NNN-nombre/qa-note.md`: Guía de pruebas manuales y trazabilidad de requisitos.
2. Actualiza el estado en `features/NNN-nombre/feature.md` a `Completada`.
3. Notifica al usuario el éxito de la entrega.

#### Escenario B: Validación Fallida (Tests Rotos o Requisitos Incumplidos)
1. **Generación de `repair.md`:** Delega en `odd-writer` la redacción de `docs/NNN-nombre/repair.md` a partir de la plantilla `.agents/skills/odd-validate/assets/repair.md`:
   - Lista de tests fallidos y excepciones mínimas.
   - Causa raíz diagnosticada.
   - Tareas de reparación asignadas (`R-01`, `R-02`...).
2. **Cambio de Estado:** Actualiza el estado en `feature.md` a `En reparación`.
3. **Bucle de Reparación TDD:**
   - El agente implementador ejecuta las tareas de reparación `R-XX` mediante TDD.
   - Aplica el **límite de resiliencia 3+1**: si tras 3 intentos una tarea no pasa el test, en el 4º intento se detiene y solicita asistencia al usuario.
4. **Cierre Limpio de Reparación:**
   - Una vez la suite completa está en verde, se actualiza `summary.md`.
   - **Se elimina el archivo transitorio `repair.md`** para no dejar artefactos temporales en el repositorio.
   - Se genera la `qa-note.md` definitiva y se marca la feature como `Completada`.
