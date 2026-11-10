---
name: sdd-validate
description: Usa esta skill para validar una spec en SDD revisando tests, requisitos funcionales/no funcionales y criterios de aceptación, soportando modo interactivo y modo automático con subagente dedicado para qa-note.
---

# Validación de una Spec

## Objetivo
Validación exhaustiva de una feature/spec revisando los tests, el cumplimiento de los requisitos funcionales y no funcionales (RF y RNF), y los criterios de aceptación, con trazabilidad rigurosa y reporte de QA.

## Entradas
- Número de la feature, nombre o ambas cosas.
- Información extra provista por el usuario o entorno.
- **Modo de ejecución:** `interactivo` (humano en chat) o `auto` (fábrica automática).

## Salidas
- **Comunicación del veredicto:** Dictamen claro (éxito o fallo), fallos detectados, métricas de cobertura técnica y funcional, y mejoras recomendadas.
- En caso de éxito (o cumplimiento >= 70%): Archivo `/docs/NNN-nombre-feature/qa-note.md`.
- En caso de fallo en la validación: Archivo auxiliar `/docs/NNN-nombre-feature/repair.md`.

## Proceso

1. **Revisión de Requisitos Funcionales (RF):**
   - Recorre `/features/NNN-nombre-feature/spec.md` requisito por requisito en formato EARS. Para cada RF indica qué tests lo cubren y el resultado de ejecutarlos.
   - Si algún RF no está cubierto o falla, regístralo detalladamente en `repair.md`, da la validación por fallida y continúa.

2. **Revisión de Requisitos No Funcionales y DevSecOps:**
   - Revisa seguridad y buenas prácticas: sin secretos expuestos, sin vulnerabilidades críticas en dependencias (Trivy/SCA) ni fallos en análisis estático (Semgrep/SAST).
   - Si no se cumplen, anótalos en `repair.md` y da por fallida la validación.

3. **Ejecución de Tests y Reglas de Infraestructura (BD vs APIs externas):**
   - **Bases de datos levantadas obligatorias:** En la validación automática de la fábrica, los contenedores y bases de datos deben estar obligatoriamente iniciados. Los tests de persistencia y base de datos deben ejecutarse y pasar; no se permite omitir tests de BD.
   - **Tests de APIs externas de terceros:** Si un test no puede conectarse a un servicio o API externa no simulada/de terceros, se clasificará como **"No comprobado / Pendiente de verificación externa"** (en lugar de darlo por verde o marcar toda la feature como rota); se reservará para que el revisor QA lo audite en la revisión manual y se reflejará explícitamente en `qa-note.md`.
   - Extrae métricas de cobertura: porcentaje de líneas, ramas y clases cubiertas. Si falla algún test unitario o de lógica interna, regístralo en `repair.md`.

4. **Evaluación de criterios de finalización y emisión de veredicto:**
   - ¿Se cumplen los criterios de aceptación de la spec?
   - Si no se cumplen, desmarca en `tasks.md` **únicamente** las tareas no completadas o que fallan (no corrijas ni reimplementes código en esta fase), documéntalas en `repair.md` y marca la validación como fallida.

5. **Gestión según el Modo de Ejecución:**

   ### A. Modo Humano (Interactivo)
   1. Comunica el veredicto en el chat con las métricas de cobertura (cobertura de RF y cobertura de código de tests).
   2. Actualiza `constitution/roadmap.md`: si la validación ha fallado, mueve la feature a "En curso" (indicando fallo de validación) salvo que esté en "En mejora"; si ha sido exitosa, a "Hecho" (o a "En validación" si quedan pendientes externos).
   3. **DETENTE:** Espera confirmación explícita del usuario en el chat antes de generar o tocar `/docs/NNN-nombre-feature/qa-note.md`.
   4. Tras recibir la confirmación expresa del usuario, redacta `/docs/NNN-nombre-feature/qa-note.md`.

   ### B. Modo Auto (Fábrica Automática)
   1. Comunica el veredicto y las métricas en el registro.
   2. **Si la validación ha fallado:** Genera automáticamente `/docs/NNN-nombre-feature/repair.md` con los fallos, desmarca tareas falladas en `tasks.md`, actualiza `constitution/roadmap.md` a "En curso" (indicando fallo en validación) y detiene el flujo alertando para corrección.
   3. **Si la validación es exitosa (o >= 70% con criterios base cumplidos):** Invoca directamente al subagente dedicado [`sdd-qa-writer`](../agents/sdd-qa-writer.md) (configurado con modelo de bajo coste como `flash` o `flash_lite`) cuya única responsabilidad es redactar y persistir `/docs/NNN-nombre-feature/qa-note.md`:
      - El subagente recibe la estructura de la validación ya realizada.
      - Genera el archivo `qa-note.md` conteniendo:
        1. Resumen de la validación y veredicto.
        2. Tabla de Trazabilidad de Requisitos Funcionales (EARS) y Cobertura de Tests.
        3. Métricas de Cobertura Técnica de Código.
        4. Casos marcados como "No comprobado" por dependencias de APIs externas para revisión manual.
        5. Instrucciones manuales de prueba simples y accionables.
      - Tras finalizar el subagente, mueve la feature a "Hecho" en `constitution/roadmap.md` (o a "En validación" si quedan pendientes externos).

## Reglas
- **Comunicación en el chat:** Siempre se informa del veredicto, de los fallos encontrados, del desglose de cobertura y de las mejoras sugeridas.
- **Instrucciones manuales simples:** La guía de pruebas manuales en `qa-note.md` debe ser lo más simple posible para una persona, sin jerga innecesaria pero cubriendo todos los casos clave.
- Si algo falla en algún paso del proceso, NO arregles nada todavía. Apúntalo en el archivo auxiliar `repair.md`, continúa el siguiente paso y repórtalo.
- Usa la skill sdd.
- Actúa como un QA profesional.
