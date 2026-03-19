# BackEnd
Todo lo necesario para aprobar esta asignatura donde se da Spring boot, JSP. 

Html esta en [FrontEnd](../FrontEnd). De jsp, lo que sea diferente a html esta aqui.

Cada carpeta es un proyecto distinto que se abre y ejecuta individualmente.

IDE-> InteliJ Ultimate-> licencia con github education

---
## Estructura
Todo esta en src/main.
- Resources-> aqui están las propiedades de la app y en /static los html.
- webappp-> páginas dinámicas(jsp), capaces de cargar variables y tener codigo java. en /WEB-INF va el contenido enaccesible desde el navehador.
- java/.../controller-> en esta carpeta estaran los controladores.
- java/.../dao-> aqui estarán los repositorios.
- java/.../entity-> aqui tendremos las entidades de la bd.

---
## demo_2026
Este proyecto esta decicado a la base, donde se explican controladores y jsp con una página básica html.
- Controlador.java-> ejemplo de controlador
- login.html-> formulario usado para el ejemplo.
- test.jsp-> ejmplo de pg dinámica
- login.html-> ejemplo de envio de formulario.

---
## movies_TESAW
Este proyecto se centra en obtener información de una bd, mediante un repositorio y entidades.
- MoviesControler.java-> Ejemplo de uso del repositorio.
- MoviesRepository.java-> Ejemplo de creación de un repositorio.
- GenreEntity.java-> Ejemplo de entidad de la bd, los otros tambien tienen funciones.
- MovieEntity.java-> Ejemplo de la anotacion @join en relacion con genre.