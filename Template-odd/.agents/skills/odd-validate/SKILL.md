---
name: odd-validate
description: Ejecuta la validación técnica y funcional de features en ODD. Audita que no haya código de producción no registrado, actualiza el roadmap, emite summary.md y qa-note.md mediante odd-writer, o gestiona el bucle de reparación transitorio repair.md con resiliencia 3+1.
---

# Skill `/odd-validate`: Validación, Auditoría de Código y Reparación

## Propósito
Asegurar la calidad técnica y funcional de una feature, verificar que no existan implementaciones huérfanas o no registradas en la especificación, actualizar automáticamente `constitution/roadmap.md` y resolver de forma autónoma cualquier fallo detectado mediante el bucle `repair.md`.

---

## Procedimiento Operativo

### Paso 1: Transición en Roadmap y Verificaciones Técnicas
1. **Roadmap:** Mueve la feature a `En validación` en `constitution/roadmap.md`.
2. **Suite de Pruebas:** Ejecuta la batería de tests unitarios y de integración (`mvn test`, `pytest`, `npm test`...).
3. **Análisis Estático y Linter:** Ejecuta linter y formateador de código.
4. **Auditoría de Implementaciones No Registradas:**
   - Comprueba que todos los archivos nuevos o métodos expuestos respondan directamente a un Criterio de Aceptación EARS (`RF-XX`) de `feature.md`.
   - Si se detecta código de producción huérfano (no contemplado en la spec), el validador exige su justificación, eliminación o incorporación formal vía `/odd-clarify`.

---

### Paso 2: Bifurcación de Resultados

#### Escenario A: Validación Exitosa (Todo en Verde y Sin Código Huérfano)
1. Invoca al subagente `odd-writer` para redactar:
   - `docs/NNN-nombre/summary.md`: Resumen ejecutivo de la implementación.
   - `docs/NNN-nombre/qa-note.md`: Guía de pruebas manuales y matriz de trazabilidad.
2. Actualiza el estado en `features/NNN-nombre/feature.md` a `Completada`.
3. **Roadmap:** Mueve la feature a `Hecho` en `constitution/roadmap.md`, indicando el porcentaje de cobertura de tests.
4. Notifica al usuario el veredicto favorable.

#### Escenario B: Validación Fallida (Tests Rotos, Linters o Código Huérfano)
1. **Generación de `repair.md`:** Invoca a `odd-writer` para generar `docs/NNN-nombre/repair.md` detallando:
   - Tests o criterios fallidos y traza mínima.
   - Diagnóstico de causa raíz.
   - Tareas concretas de reparación (`R-01`, `R-02`...).
2. **Roadmap:** Mueve la feature a `En reparación` en `constitution/roadmap.md`.
3. Actualiza el estado en `feature.md` a `En reparación`.
4. **Bucle de Reparación TDD:**
   - El agente implementador ejecuta las tareas `R-XX` mediante TDD.
   - Límite de resiliencia 3+1: tras 3 fallos consecutivos, al 4º se detiene y avisa.
5. **Cierre Limpio:**
   - Al volver a estar todo en verde, actualiza `summary.md`.
   - **Elimina obligatoriamente `repair.md`** del repositorio.
   - Mueve la feature a `Hecho` en `constitution/roadmap.md` y emite la `qa-note.md` definitiva.
