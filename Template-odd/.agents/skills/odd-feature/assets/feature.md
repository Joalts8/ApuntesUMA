# Feature NNN: <Título de la Feature>

**Estado:** `Propuesta` | `Aprobada` | `En curso` | `En reparación` | `Completada`  
**Antecedentes / Relaciones:** `<Ninguna | Feature NNN / fast-log previo>`  
**Origen:** `<Ticket Jira / GitHub Issue #123 / Petición>`  
**Fecha:** `YYYY-MM-DD`  

---

## 1. Contexto y Objetivos
<Descripción concisa del problema de negocio que resuelve esta funcionalidad y el valor aportado al usuario.>

---

## 2. Requisitos y Criterios de Aceptación (EARS)

* **RF-01:** Cuando `<evento o precondición>`, el sistema debe `<resultado esperado>`.
* **RF-02:** Si `<condición de error>`, el sistema debe `<comportamiento seguro o mensaje>`.
* **RNF-01 (Rendimiento / Seguridad):** El tiempo de respuesta no debe superar `<X ms>` y los datos sensibles deben estar cifrados.

---

## 3. Enfoque Técnico y Diseño Preliminar

> [!NOTE]
> Las decisiones técnicas globales provienen de `docs/approved-decisions.md`. Solo las decisiones inéditas locales se registrarán en `docs/NNN-<nombre>/decisions.md`.

* **P-01 (para RF-01):** <Estrategia técnica: endpoints, entidades, puertos o servicios a implementar o modificar.>
* **P-02 (para RF-02):** <Estrategia para el manejo de excepciones o validaciones.>

---

## 4. Plan de Implementación en Cascada (TDD)

- [ ] **T-01 (TDD - Rojo/Verde para P-01 / RF-01):** Crear test unitario/integración y escribir código mínimo necesario.
- [ ] **T-02 (TDD - Rojo/Verde para P-02 / RF-02):** Crear test para validación/error e implementar control.
- [ ] **T-03 (Refactor y Limpieza):** Extraer duplicidades, verificar nombres y asegurar coherencia arquitectónica.
- [ ] **T-04 (Validación Final):** Ejecutar suite completa con `/odd-validate`.
