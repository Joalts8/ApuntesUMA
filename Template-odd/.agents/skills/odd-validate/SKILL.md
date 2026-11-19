---
name: odd-validate
description: Valida features de ODD (carriles Amarillo y Rojo separable). Ejecuta tests, linter, matriz RF↔test y auditoría de código huérfano; en modo auto pasa la auditoría de odd-reviewer; delega en odd-writer (modelo bajo) el qa-note y el summary, actualiza roadmap y gestiona el bucle repair.md con resiliencia 3+1.
---

# Skill `/odd-validate`: Validación, Auditoría y Reparación

## Propósito
Verificar técnica y funcionalmente una feature, garantizar que no exista código huérfano, actualizar `constitution/roadmap.md` y cerrar con `qa-note.md` o reparar mediante `repair.md`.

> Para una feature del **Carril Rojo indivisible**, no uses esta skill: su validación es `/sdd-validate` (SDD puro), que también redacta su `qa-note.md` con modelo bajo.

---

## Procedimiento Operativo

### Paso 1: Verificaciones Técnicas y Funcionales
1. **Roadmap:** mueve la feature a `En validación` en `constitution/roadmap.md`.
2. **Suite de Pruebas:** ejecuta tests unitarios y de integración.
3. **Linter y formateo:** ejecuta ambos y registra el resultado.
4. **Matriz de Trazabilidad `RF`↔test:** recorre `feature.md` requisito por requisito y anota, por cada `RF-XX`, el test concreto que lo cubre y su resultado real. Un `RF` sin test es un fallo, no una omisión.
5. **APIs externas no disponibles:** si un test solo falla por no poder conectar con un servicio de terceros no simulado, no lo cuentes como verde: márcalo como **"No comprobado / Pendiente de verificación externa"** y llévalo al `qa-note.md`. No omitas nunca tests de BD locales: los contenedores deben estar levantados.
6. **Código huérfano:** comprueba que todo archivo o método público nuevo responda a un `RF-XX`. Lo que no, se justifica, se elimina o se incorpora vía `/odd-clarify`.

### Paso 2: Auditoría Cruzada con `odd-reviewer`
No firma la validación quien implementó, ni el mismo modelo.
* **Modo `auto` (obligatorio):** invoca `odd-reviewer` (modelo distinto al implementador) con `feature.md`, la matriz `RF`↔test, la lista de archivos nuevos y el resultado de tests/linter. Debe confirmar que cada `RF` tiene un test real y que no queda código huérfano.
  * `APROBADO` → continúa a la bifurcación.
  * `REQUIERE AJUSTES` o `RECHAZADO` → se trata como **validación fallida** (Paso 3.B) aunque los tests estén en verde.
* **Modo `gated` (opcional):** muestra la matriz y el veredicto; el usuario decide. Si hay un modelo distinto disponible, el dictamen del revisor se añade al informe.

### Paso 3: Bifurcación

#### A. Validación Exitosa
1. Delega en `odd-writer` (modelo bajo) la redacción de:
   * `docs/NNN-nombre/summary.md`, desde `.agents/skills/odd-validate/assets/summary.md`.
   * `docs/NNN-nombre/qa-note.md`, desde `.agents/skills/odd-validate/assets/qa-note.md`, incluyendo la matriz `RF`↔test, la cobertura y los casos "No comprobado".
2. Actualiza `feature.md` a `Estado: Completada`.
3. Mueve a `Hecho` en `constitution/roadmap.md` con el porcentaje de cobertura.
4. **Commit final** (`docs(feature): cierre de feature NNN`).
5. Comunica el veredicto y el enlace al `qa-note.md`.

#### B. Validación Fallida
1. `odd-writer` genera `docs/NNN-nombre/repair.md`, desde `.agents/skills/odd-validate/assets/repair.md`, con fallos, traza mínima, causa raíz y tareas `R-XX`.
2. `Estado: En reparación` en `feature.md` y `En reparación` en el roadmap.
3. **Bucle de Reparación TDD:** cada `R-XX` con test primero, commit por tarea y límite 3+1 (al 4º fallo, se detiene y se escala).
4. **Cierre limpio:** vuelve a validar (incluida la auditoría del Paso 2); si todo está en verde, elimina `repair.md`, deja `Estado: Completada`, redacta el `qa-note.md` definitivo con `odd-writer` desde su asset, mueve a `Hecho` y haz el commit final.

### Paso 4: Comunicación
Informa siempre del veredicto, de los `RF` no cubiertos y de los casos "No comprobado". El `qa-note.md` no sustituye a ese resumen: es su versión persistida.
