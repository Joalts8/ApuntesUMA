# Catálogo Global de Decisiones Aprobadas (Memoria del Proyecto)

_Este documento es el vademécum de decisiones técnicas, patrones de diseño y convenciones adoptadas y consolidadas en el proyecto. Sirve como referencia obligatoria tanto para desarrolladores como para la fábrica autónoma de software (agentes de IA) para actuar con autonomía sin necesidad de consultar decisiones recurrentes._

---

## ⚙️ Reglas de Consulta para la Fábrica y Agentes

Cuando surja una duda de diseño o implementación técnica durante una feature:
1. **Paso 1 (Prioritario):** Consulta este archivo (`docs/approved-decisions.md`). Si existe una decisión aprobada aplicable al caso, **aplícala automáticamente sin preguntar al usuario y NO la registres en `decisions.md`** (ya es conocida globalmente).
2. **Paso 2 (Fallback en features no consolidadas):** Si no figura aquí, revisa los archivos `docs/NNN-nombre-feature/decisions.md` de las features recientes aún no consolidadas. Si una feature previa ya resolvió ese escenario coherentemente, **reutiliza dicha decisión y NO la dupliques** en el `decisions.md` actual.
3. **Paso 3 (Nueva decisión inédita):**
   - **Nivel 2 (Menor / Local):** Si no compromete la arquitectura ni viola `constitution/tech-stack.md`, adóptala automáticamente y **regístrala como nueva en `docs/NNN-nombre-feature/decisions.md`** para su futura revisión periódica.
   - **Nivel 3 (Crítica / Bloqueante):** Si altera contratos públicos existentes, infraestructura o colisiona con la constitución, detén la fábrica y consulta al usuario; tras su aprobación, regístrala en `docs/NNN-nombre-feature/decisions.md`.

---

## 1. Manejo de Errores y Excepciones

| ID | Fecha | Feature Origen | Decisión Aprobada | Justificación / Regla para la Fábrica |
| :--- | :--- | :--- | :--- | :--- |
| `DEC-ERR-01` | YYYY-MM-DD | `001-nombre` | `<Ejemplo: Formato estándar RFC-7807 (Problem Details) para todas las respuestas de error en APIs REST>` | `<Garantiza interoperabilidad y estructura predecible: type, title, status, detail, instance>` |

---

## 2. Persistencia, Modelado y Base de Datos

| ID | Fecha | Feature Origen | Decisión Aprobada | Justificación / Regla para la Fábrica |
| :--- | :--- | :--- | :--- | :--- |
| `DEC-DAT-01` | YYYY-MM-DD | `001-nombre` | `<Ejemplo: Soft deletes obligatorios con columna deleted_at en tablas transaccionales>` | `<Preserva auditoría e historial sin pérdidas de datos accidentales>` |

---

## 3. Testing, Mocking y Fixtures

| ID | Fecha | Feature Origen | Decisión Aprobada | Justificación / Regla para la Fábrica |
| :--- | :--- | :--- | :--- | :--- |
| `DEC-TST-01` | YYYY-MM-DD | `001-nombre` | `<Ejemplo: Mocks solo para límites externos (I/O, APIs HTTP de terceros, emails); la lógica de negocio se testea con objetos reales>` | `<Maximiza la fidelidad de las pruebas unitarias y minimiza falsos positivos por mocks desfasados>` |

---

## 4. Seguridad, Autenticación y Autorización

| ID | Fecha | Feature Origen | Decisión Aprobada | Justificación / Regla para la Fábrica |
| :--- | :--- | :--- | :--- | :--- |
| `DEC-SEC-01` | YYYY-MM-DD | `001-nombre` | `<Ejemplo: Contraseñas hasheadas exclusivamente con Argon2id o BCrypt con factor de coste >= 12>` | `<Cumplimiento de estándares de seguridad DevSecOps y prevención de ataques de fuerza bruta>` |

---

## 5. APIs, DTOs y Contratos de Comunicación

| ID | Fecha | Feature Origen | Decisión Aprobada | Justificación / Regla para la Fábrica |
| :--- | :--- | :--- | :--- | :--- |
| `DEC-API-01` | YYYY-MM-DD | `001-nombre` | `<Ejemplo: Inmutabilidad estricta en DTOs de entrada y salida; validación de esquemas en el punto de entrada>` | `<Evita mutaciones de estado no deseadas entre capas del sistema>` |

---

## 6. Convenciones Generales de Código y Librerías Auxiliares

| ID | Fecha | Feature Origen | Decisión Aprobada | Justificación / Regla para la Fábrica |
| :--- | :--- | :--- | :--- | :--- |
| `DEC-GEN-01` | YYYY-MM-DD | `001-nombre` | `<Ejemplo: Fechas y tiempos siempre manipulados y almacenados en formato UTC ISO-8601>` | `<Previene discrepancias horarias entre servidor, base de datos y clientes>` |
