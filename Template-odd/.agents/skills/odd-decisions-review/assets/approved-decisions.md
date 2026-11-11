# Catálogo Global de Decisiones Técnicas Aprobadas

> [!IMPORTANT]
> **Memoria Técnica del Proyecto:** Este documento recopila las decisiones transversales consolidadas y aprobadas para todo el proyecto.
> - **Cualquier agente o fábrica autónoma DEBE consultar este catálogo ANTES de diseñar o implementar.**
> - Si una convención o patrón está definido aquí, **se aplica automáticamente sin pedir confirmación ni duplicar el registro en la feature.**
> - Las decisiones inéditas surgidas en features o fast-tracks se consolidan periódicamente aquí mediante la skill `/odd-decisions-review`.

---

## 1. Gestión de Errores y Excepciones
* **D-GLOBAL-01:** Utilizar excepciones de dominio específicas derivadas de una clase base común. Los controladores deben capturarlas de forma centralizada con un manejador global que devuelva el formato RFC 7807 (Problem Details).

## 2. Persistencia y Modelado de Datos
* **D-GLOBAL-02:** Los nombres de tablas y columnas se definirán en formato `snake_case` en base de datos, mapeados a `camelCase` en las entidades del código. Cada entidad de negocio debe incorporar auditoría básica de creación y modificación.

## 3. Pruebas y Dobles de Test (Mocks)
* **D-GLOBAL-03 (TDD Estricto):** Todo cambio o nueva funcionalidad se construye siguiendo el ciclo Red-Green-Refactor. Los tests unitarios deben aislar dependencias externas mediante mocks/stubs; los tests de integración deben validar contra instancias reales o contenedores de prueba (Testcontainers).

## 4. Seguridad y Autenticación
* **D-GLOBAL-04:** Ningún secreto, token o clave de API puede estar versionado en el código fuente ni en variables de configuración estáticas. Deben inyectarse mediante variables de entorno en tiempo de ejecución.

## 5. Diseño de APIs y Contratos (DTOs)
* **D-GLOBAL-05:** Los endpoints deben seguir las convenciones RESTful con sustantivos en plural (`/api/v1/usuarios`). Queda terminantemente prohibido exponer entidades de base de datos directamente en las respuestas HTTP; es obligatorio el uso de DTOs dedicados para request y response.

## 6. Convenciones Generales de Código
* **D-GLOBAL-06:** El idioma oficial para el código fuente (clases, métodos, variables y comentarios de código) es el inglés. La documentación funcional y de usuario se redacta en español.
