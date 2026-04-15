# ForntEnd
Todo lo necesario para aprobar esta asignatura donde se da html, css, javascript y react.

IDE-> vscode

---
## HTML
Nombres de los archivos en minuscula(me dio pereza cambiarlo)
- Introducción-> Incluye todo lo basico de html.
- Formulario-> Incluye mas funcionalidades de html para crear formularios.
- Css-> HTML auxilir para el tema de css.
- Js-> HTML auxilir para el tema de js. Explica las 2 formas de importar JS.


---
## CSS
- style-> Incluye lo basico de CSS, combinadores, flexbox, grid y web adaptativa.

---
## JS
- js_basico-> Incluye lo basico de JS. Puede iniciarse normal con defer o con type module.
- poo-> Explica las clases y los objetos en JS.
- script-> Interactua con un HTML mediante el DOM. Para las funciones al interactuar, usar EventListener, ignorando el onclick tanto en el elemento como para html.
- alm_cliente-> Tiene informacion sobre las cookies, y web storange.
- json-> ejemplo de archivo json para un objeto basico. Puede usarse con varios objetos([{},{}]) o varios valores [valor1, valor2].

---
## React
Proyecto con lo basico de REact, .jsx. Se crea con la plantilla para react y js.
- index.html-> ejemplo de carga de jsx.
- main.jsx-> ejemplo de carga de la app
- App.jsx-> Ejemplo de componente principal de la app web, realmente llamaria a las rutas y cargaria la página que sea.
- Componente.jsx-> Otro ejemplo de componente para otras funciones.

### Creación
- npm create vite@latest .
- npm install
- npm run dev

### Estructura
- node_modules-> dependencias, se ignorar en el git.
- public-> archivos estáticos públicos.
- src -> todo el código fuente de la aplicación.
 - assets-> assets utilizados.
 - api/services-> llamadas a API y acceso a datos.
 - components-> componentes reutilizables, como botones, cards, navbars o formularios.
 - pages-> cada página principal de la app, normalmente una por ruta.
 - routes-> configuración de React Router para cargar cada página.
 - hooks-> hooks personalizados para reutilizar lógica.
 - context-> estado global compartido, como autenticación o tema.
 - utils-> funciones auxiliares y constantes.
 - styles->estilos globales, variables y temas.
 - `main.jsx`-> punto de entrada de React.
 - `App.jsx`-> componente raíz de la aplicación.
 - `App.css`-> estilos específicos de la app.
 - `index.css`-> estilos globales.
- `index.html`-> página principal que carga main.