# QA Note: NNN-<nombre-feature>

* **Fecha de validación:** `YYYY-MM-DD`
* **Veredicto:** `APROBADO PARA PRODUCCIÓN`

---

## 1. Trazabilidad de Requisitos

| Requisito EARS | Enfoque Técnico | Test Asociado | Estado |
| :--- | :--- | :--- | :--- |
| **RF-01** | P-01 | `FeatureTest#testRF01` | `PASSED` |
| **RF-02** | P-02 | `FeatureTest#testRF02` | `PASSED` |

---

## 2. Guía de Pruebas Manuales (Paso a Paso)
1. **Paso 1:** Arrancar la aplicación en entorno local (`./mvnw spring-boot:run` o equivalente).
2. **Paso 2:** Enviar una petición a `POST /api/v1/...` con el payload de prueba.
3. **Paso 3:** Verificar que la respuesta devuelve HTTP `200/201` y el objeto creado.
4. **Paso 4 (Caso borde):** Enviar payload con datos inválidos y confirmar HTTP `400 Bad Request`.
