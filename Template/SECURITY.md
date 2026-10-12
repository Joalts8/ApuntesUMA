# Política de Seguridad

_Esta plantilla establece la política de divulgación responsable de vulnerabilidades, las versiones que reciben soporte de seguridad y las pautas para reportar fallos de forma segura y privada. Sustituye todo lo que esté entre `<...>` y borra las notas en cursiva antes de publicar._

La seguridad de `<Nombre del Proyecto>` es una prioridad fundamental. Agradecemos los esfuerzos de investigadores y de la comunidad para reportar vulnerabilidades de manera responsable.

---

## Versiones con soporte de seguridad

_Define qué versiones o ramas del proyecto reciben parches de seguridad activos._

Actualmente, las siguientes versiones del proyecto reciben actualizaciones y correcciones de seguridad:

| Versión | ¿Soporte activo? | Notas |
| :--- | :---: | :--- |
| `<v2.x / main>` | ✅ Sí | `<Rama principal de desarrollo / última release estable>` |
| `<v1.x>` | ⚠️ Solo críticas | `<Soporte extendido hasta YYYY-MM-DD / solo parches críticos>` |
| `< < v1.0>` | ❌ No | `<Versión obsoleta / sin soporte>` |

---

## Cómo reportar una vulnerabilidad

> [!CAUTION]
> **Por favor, NO reportes vulnerabilidades de seguridad a través de Issues públicos ni foros abiertos de discusión.** La divulgación pública prematura expone a los usuarios antes de que exista un parche disponible.

### Método preferido: Divulgación Privada en GitHub (Recomendado)
_Si el repositorio tiene habilitado GitHub Private Vulnerability Reporting:_

1. Ve a la pestaña **Security** del repositorio en GitHub.
2. Selecciona **Advisories** (Avisos de seguridad).
3. Haz clic en **Report a vulnerability** (Reportar una vulnerabilidad) para abrir un borrador confidencial accesible únicamente por los mantenedores.

### Método alternativo: Correo Electrónico
Si no puedes usar GitHub Advisories o prefieres el correo electrónico, envía tu reporte a:

- **Correo de seguridad:** `<correo dedicado, ej: security@tudominio.com o correo-del-mantenedor@dominio.com>`
- **Clave PGP / Encriptación (opcional):** `<Enlace a clave pública PGP o fingerprint si aplica>`

---

## Información a incluir en el reporte

Para ayudarnos a clasificar, reproducir y solucionar el problema rápidamente, por favor incluye la mayor cantidad de información posible:

1. **Tipo de vulnerabilidad:** `<ej. Inyección SQL, Cross-Site Scripting (XSS), Escalada de privilegios, Fuga de información confidencial, Denegación de servicio (DoS)>`.
2. **Componente o ruta afectada:** `<archivo, endpoint, función o dependencia involucrada>`.
3. **Versión afectada:** `<versión exacta o commit hash donde se reproduce el fallo>`.
4. **Instrucciones paso a paso para reproducir el fallo:**
   - Pasos detallados o script de prueba de concepto (*PoC*).
   - Datos o cargas útiles (*payloads*) de ejemplo.
5. **Impacto potencial:** Qué podría lograr un atacante si explota esta vulnerabilidad.
6. **Mitigación o parche sugerido (opcional):** Si tienes una propuesta de solución o parche.

---

## Nuestro proceso y tiempos de respuesta

_Ajusta los plazos a la disponibilidad real de tu equipo o proyecto._

Cuando recibamos un reporte de seguridad, el equipo seguirá este procedimiento:

1. **Acuse de recibo:** Confirmaremos la recepción de tu mensaje en un plazo máximo de `<24-48 horas>` laborables.
2. **Evaluación y confirmación:** Evaluaremos la gravedad del problema, verificaremos la reproducibilidad y te informaremos en un plazo estimado de `<3 a 7 días>`.
3. **Desarrollo del parche:** Trabajaremos en una solución en una rama privada. Te mantendremos informado sobre el progreso y podremos solicitarte que valides la solución antes del lanzamiento.
4. **Publicación y aviso:**
   - Se publicará una nueva versión o parche correctivo.
   - Se emitirá un aviso de seguridad (*Security Advisory*) público describiendo el problema, las versiones afectadas y la solución.
   - Solicitaremos un CVE si corresponde.

Nos comprometemos a mantener una comunicación transparente durante todo el proceso.

---

## Reglas de compromiso (Divulgación Responsable)

Pedimos a los investigadores y contribuyentes que:
- Actúen de buena fe para evitar interrupciones en el servicio, destrucción de datos o violaciones de privacidad de terceros.
- Nos otorguen un plazo razonable (generalmente `<30 a 90 días>`) para investigar y mitigar la vulnerabilidad antes de hacer pública cualquier información técnica.
- No exploten la vulnerabilidad más allá de lo estrictamente necesario para demostrar el fallo como prueba de concepto.

---

## Buenas prácticas para contribuidores

Para prevenir incidentes de seguridad en el repositorio:
- **No incluyas secretos ni credenciales:** Nunca hagas commit de tokens de API, contraseñas, claves privadas ni archivos `.env`. Utiliza siempre `.env.example` para plantillas.
- **Auditoría de dependencias:** Revisa regularmente vulnerabilidades en dependencias usando herramientas como `<npm audit | pip-audit | cargo audit | Dependabot>`.
- **Firma de commits (opcional):** Fomenta la firma de commits con claves GPG o SSH para asegurar la procedencia del código.

---

## Reconocimientos

Agradecemos públicamente a quienes descubran y reporten vulnerabilidades de manera responsable. Salvo que prefieras permanecer en el anonimato, incluiremos tu nombre/usuario en:
- Las notas de la versión (*Release Notes*).
- El aviso de seguridad (*Security Advisory*).
- `<Muro de agradecimientos / Hall of Fame en docs/SECURITY-HALL-OF-FAME.md (opcional)>`.
