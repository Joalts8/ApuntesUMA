---
name: adr
description: Crea un Architectural Decision Record (ADR) en docs/ADR cuando se toma una decisión arquitectónica o técnica de alto impacto (Carril Rojo de ODD).
---

# Creación de Architectural Decision Record (ADR)

## Propósito
Documentar formalmente una decisión de arquitectura o diseño técnico relevante en el proyecto (especialmente en el Carril Rojo / Epic de ODD), garantizando trazabilidad y contexto histórico.

---

## Entradas y Salidas
* **Entradas:** Contexto del problema, alternativas evaluadas, justificación de la decisión y consecuencias estimadas.
* **Salida:** Archivo `docs/ADR/ADR-NN.md` (donde `NN` es el número correlativo de dos dígitos, ej. `01`, `02`).

---

## Proceso Operativo
1. Examina la carpeta `docs/ADR/` para identificar el último número asignado y calcular el siguiente correlativo.
2. Carga la plantilla base en `.agents/skills/adr/assets/ADR-NN.md`.
3. Recopila la justificación y alternativas con el usuario.
4. Redacta el ADR con estado `Propuesto` o `Aceptado`.
5. Tras el visto bueno del usuario, persiste el archivo en `docs/ADR/ADR-NN.md`.
6. Realiza un commit con el mensaje: `docs(adr): registro de decisión arquitectónica ADR-NN`.
