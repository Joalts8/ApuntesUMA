# Informe de Reparación: NNN-<nombre-feature>

> [!CAUTION]
> **Documento de Reparación Activo:** La validación de esta feature ha fallado. El estado pasa a `En reparación`. El agente implementador debe subsanar los puntos abajo listados mediante TDD (límite 3+1 reintentos). Al quedar todo en verde, este archivo se eliminará automáticamente.

* **Fecha de fallo:** `YYYY-MM-DD HH:MM`
* **Causa principal:** `<Fallo de test / Linter / Criterio de aceptación no cubierto>`

---

## 1. Discrepancias y Tests Rotos

| ID Fallo | Tipo | Componente / Test Afectado | Descripción del Fallo / Traza Mínima |
| :--- | :--- | :--- | :--- |
| **F-01** | Test Rojo | `UsuarioServiceTest#testCrear` | Excepción esperada no coincide |
| **F-02** | RF No Cubierto | `RF-02` | Falta validación de email duplicado |

---

## 2. Diagnóstico de Causa Raíz
<Breve explicación técnica de por qué ocurrió el fallo sin divagar.>

---

## 3. Tareas de Reparación (TDD)

- [ ] **R-01 (TDD - Test):** Ajustar test unitario para reproducir fielmente el escenario del fallo F-01.
- [ ] **R-02 (TDD - Fix):** Modificar la lógica de negocio para que el test pase en verde.
- [ ] **R-03 (Re-validación):** Ejecutar `/odd-validate` para verificar el paso limpio y emitir `qa-note.md`.
