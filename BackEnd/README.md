# BackEnd
Todo lo necesario para aprobar esta asignatura donde se da Spring boot, JSP. 

Html esta en [FrontEnd](../FrontEnd). De jsp, lo que sea diferente a html esta aqui.

Cada carpeta es un proyecto distinto que se abre y ejecuta individualmente.

IDE-> InteliJ Ultimate-> licencia con github education

---
## Estructura
Todo esta en src/main.
- Resources-> aqui están las propiedades de la app y en /static los html.
- webappp-> páginas dinámicas(jsp), capaces de cargar variables y tener codigo java. en /WEB-INF va el contenido inaccesible desde el navegador.
- java/.../controller-> en esta carpeta estaran los controladores.
- java/.../dao-> aqui estarán los repositorios.
- java/.../entity-> aqui tendremos las entidades de la bd.
- java/.../service-> aqui tendremos los servicios del BE.
- java/.../dto-> aqui tendremos los objetos que se devuelven al FE.
- java/.../mapper-> aqui tendremos los mappers.

---
## demo_2026
Este proyecto esta decicado a la base, donde se explican controladores y jsp con una página básica html.
- Controlador.java-> ejemplo de controlador
- login.html-> formulario usado para el ejemplo, solo indica como se manda el form.
- test.jsp-> ejemplo de pg dinámica
- aaux.jsp-> ejemplo de parde en test.jsp q se actualiza.

---
## movies_TESAW
Este proyecto se centra en obtener información de una bd, mediante un repositorio y entidades.
- MoviesControler.java-> Ejemplo de uso del repositorio y consulta custom en /filtrar, lo demás de controlador en demo.
- AutenticaController.java-> Ejemplo de creacion y cierre de la sesion.
- MoviesRepository.java-> Ejemplo de creación de un repositorio y  creacion de querys customs para /filtrar.
- GenreEntity.java-> Ejemplo de entidad de la bd, los otros tambien tienen funciones.
- MovieEntity.java-> Ejemplo de la anotacion @join en relacion con genre y borrado en many to many.
- application.propierties-> Ejemplo del uso de este archivo para configurar el proyecto.
- header.jsp-> uso de la sesión para ver atributos durante toda la sesion del usuario.

---
## movies_TESAWRefactor
Refactorizacion con DTO, Servives y mappers(paso de Entity a DTO). 
- .jsp-> Ahora usan las clases DTO en vez de las Entity.
- *Controller.java-> Ahora llaman a service para la logica de negocio.
- /dto/Genre.java-> Ejemplo de dto, donde se usan los campos necesarios de la entidad.
- mappers services y controllers

---
## Creación de un proyecto

### Creación
- Se crea un nuevo proyecto seleccionando SpringBoot.
- Se elige el nombre, ubicación, grupo, jdk y lenguaje del proyecto(java+maven). Y el ejecutable .jar.
- Se indican las librerias a usar:
  - Lombok-> Geterrs y seters.
  - Springweb.
  - Spring Data JPA-> Para control bd SQL.
  - La de la BD a usar en SQL.
-Se crean las carpetas necesarias(ver en estructura). 
---

### Configuración
- Para facilitar la creación de JSP, vamos a modulos del proyecto, web, y añadimos webapp como directorio /.
- Se configuran las propiedades del proyecto para el uso de prefijos, sufijos y BD.
- Dependencias para JSP: org.apache.tomcat.embed y jakarta.servlet.jsp.jstl.



para el de ordenar, cada colum es un boton q lanza un evento de clic mandando su name e id
valor para ese id se invierte y los demas los pone a false(array)
fetch donde manda el valor y name y se ordena segun
