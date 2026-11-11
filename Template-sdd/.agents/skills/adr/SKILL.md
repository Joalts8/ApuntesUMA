---
name: adr
description: Crea un Architectural Decision Record (ADR) en docs/ADR cuando se toma una decisión arquitectónica o técnica relevante.
---

# Creación de Architectural Decision Record (ADR)

## Objetivo
Documentar formalmente una decisión de arquitectura o diseño técnico relevante adoptada en el proyecto, garantizando trazabilidad y contexto histórico de decisiones técnicas. No pertenece formalmente al flujo SDD, pero se utiliza siempre que sea necesario (por ejemplo, referenciado en planes técnicos).

## Entradas
- Título o tema de la decisión arquitectónica.
- Contexto, motivación y limitaciones o retos técnicos.
- Alternativas u opciones consideradas.
- Decisión adoptada y justificación técnica.
- Consecuencias esperadas (positivas, negativas y neutrales/compromisos).

## Salidas
- Archivo `docs/ADR/ADR-NN.md` (donde `NN` es el número correlativo de dos dígitos, ej. `01`, `02`, etc.).

## Proceso
1. Examina la carpeta `docs/ADR/` para identificar el último número de ADR asignado y determinar el siguiente número secuencial de dos o más dígitos (ej. `01`, `02`, ...).
2. Lee la plantilla base ubicada en el archivo relativo `./assets/ADR-NN.md`.
3. Recopila o solicita la información técnica necesaria (contexto, opciones evaluadas, justificación de la decisión y consecuencias).
4. Redacta el ADR rellenando la plantilla:
   - Título en voz activa descriptivo.
   - Estado: `propuesto`, `aceptado`, `rechazado` o `reemplazado por [ADR-XXXX]`.
   - Fecha actual en formato `YYYY-MM-DD`.
   - Contexto del problema.
   - Opciones analizadas.
   - Decisión tomada y razones clave.
   - Consecuencias (positivas, negativas y neutrales/trade-offs).
5. Muestra una propuesta al usuario para su revisión y aprobación.
6. Una vez aprobado por el usuario, guarda el archivo en `docs/ADR/ADR-NN.md` (reemplazando `NN` por el número asignado).
7. Realiza un commit siguiendo la convención de commits del proyecto (Conventional Commits en español, ej. `docs(adr): registro de decisión de arquitectura ADR-NN`).

## Reglas
- La plantilla vive en `./assets/ADR-NN.md` de la skill; nunca vuelvas a dejar la plantilla en blanco dentro de `docs/ADR/`.
- No tomes decisiones arquitectónicas por tu cuenta sin consultar o confirmar con el usuario.
- Sustituye todos los marcadores entre `<...>` o `[...]` y no dejes campos vacíos o de ejemplo.