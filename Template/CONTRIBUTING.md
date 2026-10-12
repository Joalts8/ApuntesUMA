# Guía de Contribución

_Esta plantilla detalla cómo cualquier persona puede colaborar en el proyecto. Adapta los comandos, rutas y flujos según la tecnología de tu proyecto (`constitution/tech-stack.md`). Sustituye todo lo que esté entre `<...>` y borra las notas en cursiva antes de usarlo._

¡Gracias por tu interés en contribuir a `<Nombre del Proyecto>`! 🎉

Este documento proporciona pautas y pasos recomendados para que el proceso de contribución sea claro, eficiente y gratificante para todos.

---

## Tabla de contenidos

1. [Código de Conducta](#código-de-conducta)
2. [¿Cómo puedo contribuir?](#cómo-puedo-contribuir)
3. [Entorno de desarrollo local](#entorno-de-desarrollo-local)
4. [Flujo de trabajo con Git y ramas](#flujo-de-trabajo-con-git-y-ramas)
5. [Convención de commits](#convención-de-commits)
6. [Estándares de calidad y pruebas](#estándares-de-calidad-y-pruebas)
7. [Envío de Pull Requests](#envío-de-pull-requests)

---

## Código de Conducta

Al participar en este proyecto, aceptas cumplir con nuestro [Código de Conducta](CODE_OF_CONDUCT.md). Por favor, revísalo para asegurar un entorno respetuoso e inclusivo.

---

## ¿Cómo puedo contribuir?

### 1. Reportar errores (Bug Reports)
_Antes de crear un issue, busca en la pestaña de Issues si el problema ya ha sido reportado._

Si encuentras un error, abre un nuevo *Issue* usando la plantilla correspondiente o incluyendo:
- **Título descriptivo y conciso.**
- **Pasos detallados para reproducir el fallo.**
- **Comportamiento esperado vs. comportamiento observado.**
- **Capturas de pantalla o logs de error** (si aplica).
- **Entorno:** Sistema operativo, versión del lenguaje/runtime (`<ej. Node v20, Python 3.11, Java 21>`), navegador, etc.

### 2. Proponer nuevas características o mejoras
_En proyectos con metodología SDD (Desarrollo Guiado por Especificaciones), las features deben alinearse con la constitución (`constitution/mission.md` y `tech-stack.md`)._

- Abre un *Issue* describiendo la necesidad, el caso de uso y el beneficio esperado.
- Espera la discusión y visto bueno de los mantenedores antes de invertir tiempo programando código complejo.
- Para features nuevas, prepara o actualiza la especificación correspondiente en `features/<NNN-nombre-feature>/spec.md`.

### 3. Mejorar documentación
Las correcciones tipográficas, mejoras en los ejemplos o traducciones son siempre bienvenidas mediante un Pull Request directo.

---

## Entorno de desarrollo local

_Especifica los pasos exactos para clonar, configurar variables de entorno y arrancar el proyecto localmente._

### Requisitos previos
- `<Herramienta/Runtime 1 y versión, ej: Git >= 2.40>`
- `<Herramienta/Runtime 2 y versión, ej: Node.js >= 20.x | Python >= 3.11 | Rust >= 1.75 | JDK 21>`
- `<Gestor de paquetes, ej: npm | pnpm | poetry | pip | cargo | maven>`

### Pasos de instalación

1. **Haz un Fork** del repositorio en GitHub.
2. **Clona tu fork** en tu máquina local:
   ```bash
   git clone https://github.com/<tu-usuario>/<nombre-del-repo>.git
   cd <nombre-del-repo>
   ```

3. **Configura el repositorio remoto original (upstream):**
   ```bash
   git remote add upstream https://github.com/<propietario-original>/<nombre-del-repo>.git
   ```

4. **Instala las dependencias:**
   ```bash
   <comando de instalación, ej: npm install | pnpm install | poetry install | pip install -r requirements.txt>
   ```

5. **Configura las variables de entorno:**
   ```bash
   cp .env.example .env
   # Edita .env con tus valores locales (nunca subas este archivo al repositorio)
   ```

6. **Inicia el entorno de desarrollo:**
   ```bash
   <comando de ejecución, ej: npm run dev | python src/main.py | cargo run>
   ```

---

## Flujo de trabajo con Git y ramas

_Define el modelo de ramas del repositorio (ej. trabajo sobre ramas feature/ o fix/ partiendo de main o develop)._

1. Mantén tu rama principal actualizada con el repositorio upstream:
   ```bash
   git checkout <main | develop>
   git pull upstream <main | develop>
   ```

2. Crea una rama descriptiva para tu cambio:
   - Para nuevas funcionalidades: `feature/<nombre-breve-kebab-case>`
   - Para correcciones de bugs: `fix/<nombre-breve-kebab-case>`
   - Para documentación: `docs/<nombre-breve-kebab-case>`
   - Para refactorizaciones: `refactor/<nombre-breve-kebab-case>`

   ```bash
   git checkout -b feature/<nueva-funcionalidad>
   ```

---

## Convención de commits

_Recomendamos seguir la convención de [Conventional Commits](https://www.conventionalcommits.org/es/v1.0.0/)._

Usa el formato: `<tipo>(<alcance opcional>): <descripción concisa en presente/imperativo>`

- `feat:` Nueva funcionalidad para el usuario.
- `fix:` Corrección de un bug.
- `docs:` Cambios solo en la documentación.
- `style:` Cambios de formato (espacios en blanco, formato) que no alteran la lógica del código.
- `refactor:` Refactorización de código sin añadir features ni corregir bugs.
- `test:` Adición o corrección de pruebas automatizadas.
- `chore:` Tareas de mantenimiento, dependencias o configuración de compilación.

*Ejemplos:*
- `feat(auth): agregar validación de contraseña segura`
- `fix(api): corregir error 500 cuando el payload está vacío`
- `docs: actualizar instrucciones de instalación en README`

---

## Estándares de calidad y pruebas

Antes de enviar tus cambios, asegúrate de que pasen todas las comprobaciones de calidad definidas en el proyecto:

1. **Linter y formateo de código:**
   ```bash
   <comando para linter, ej: npm run lint | ruff check . | cargo clippy>
   <comando para formatear, ej: npm run format | black . | cargo fmt>
   ```

2. **Ejecución de pruebas automatizadas:**
   ```bash
   <comando para tests, ej: npm test | pytest | cargo test>
   ```

3. **Verificación de tipos (si aplica):**
   ```bash
   <comando de comprobación de tipos, ej: npm run typecheck | mypy src>
   ```

> [!IMPORTANT]
> Todo cambio en la lógica o nueva funcionalidad debe incluir sus pruebas correspondientes (`unitarias` o `de integración`) con todos los tests en verde.

---

## Envío de Pull Requests

1. Sube tu rama a tu fork en GitHub:
   ```bash
   git push origin <tipo>/<nombre-de-tu-rama>
   ```
2. Ve al repositorio original en GitHub y abre un **Pull Request**.
3. Rellena la descripción del PR explicando:
   - **Qué problema resuelve** y qué cambios se han introducido.
   - **Enlace al issue relacionado** (usando palabras clave como `Closes #123` o `Fixes #456`).
   - **Checklist de validación:**
     - [ ] He añadido o actualizado pruebas relevantes.
     - [ ] El código pasa el linter y los tests locales sin errores.
     - [ ] He actualizado la documentación o especificaciones si aplica.
     - [ ] El código no contiene secretos, tokens ni credenciales en archivos `.env`.
4. Espera la revisión de código (*Code Review*). Sé receptivo al feedback y realiza los ajustes solicitados directamente en tu rama.

¡Muchas gracias por hacer posible este proyecto! 🚀
