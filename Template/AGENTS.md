# AGENTS.md — <proyecto>

<Qué es, en 2-3 frases. Arquitectura, tecnologías..>

## Estructura del proyecto
- `[carpeta]/` — [qué contiene]
- `[carpeta]/` — [qué contiene]
- `[carpeta]/` — [qué contiene]

## Comandos 
Opcional si hay tech-Stack junto a alguna cosa mas de ese archivo.
- Ejecutar: `<comando>`
- Tests: `<comando>`
- Lint/formato: `<comando>`

## Estilo y convenciones
<Versión del lenguaje, convenciones de nombres y código, idioma del código y mensajes de commit.>
- Código y nombres: <Clases en PascalCase, métodos/variables en camelCase, constantes en UPPER_CASE.>
- **Mensajes de commit:** <Obligatorio seguir el estándar de **Conventional Commits** con el **mensaje en español** (ejemplos: `feat: carpeta creada`, `fix: corrección de validación de token`, `test: pruebas unitarias de filtro de seguridad`, `docs: actualización de especificación`).>
- **Documentación de código:** <Comentar obligatoriamente con **Javadoc** o similares en otros lenguajes (como **Doxygen**) (clases, interfaces, métodos de servicio, mappers, parámetros `@param`, retornos `@return` y excepciones `@throws`). En clases de donde se utilicen anotaciones (como Lombok con `@Data`, `@AllArgsConstructor`, `@NoArgsConstructor`, etc.), **prevalecen las anotaciones frente al código boilerplate hecho a mano**; se documentará la clase a nivel global y sus campos clave, sin requerir documentar en getters/setters/constructores autogenerados por anotaciones.

## Flujo de trabajo
- Antes de una tarea no trivial, propón un plan y espera mi OK.
- **Architectural Decision Records (ADRs):** Siempre que se deba tomar una decisión técnica o de diseño de arquitectura significativa (elección de librerías, cambios en el modelo de persistencia o seguridad, patrones de comunicación, etc.), es obligatorio documentarla creando un nuevo ADR en `docs/ADR/` siguiendo la plantilla `docs/ADR/ADR-NN.md`. El plan técnico (`plan.md`) debe referenciar el ADR correspondiente.

## Al terminar cualquier tarea
- <Verificación obligatoria, p. ej. ejecutar los tests.>
- Verificar que los tests unitarios y de integración pasen. Los tests de integración reales se omiten limpiamente si la BD no está levantada, no se puede conectar a una API, etc; y no penalizan la tasa de éxito ni el qa-note.

## Creación de Pull Requests e Issues
- **Pull Requests:** Al preparar o crear un PR (con `gh pr create` o como propuesta), lee obligatoriamente `.github/pull_request_template.md` y completa todas sus secciones (descripción, tipo de cambio, issue relacionado, cambios detallados y checklist). Sustituye todos los campos `<...>` y no dejes secciones vacías.
- **Issues:** Al proponer o abrir un issue, revisa `.github/ISSUE_TEMPLATE/` (`bug_report.md` o `feature_request.md`) y utiliza el formato correspondiente, incluyendo el prefijo en el título (`[Bug]: ` o `[Feature]: `) y completando todos los apartados requeridos.

## Documentación
- Referencias a más reglas, contexto, documentación y especificaciones.
