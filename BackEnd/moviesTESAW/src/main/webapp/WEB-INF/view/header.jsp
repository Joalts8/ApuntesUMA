<%@ page import="java.util.Date" %>
<%@ page import="es.tesaw.movies.entity.UserEditorEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    // sesion permite acceder a los datos de la sesión actual, atributo solo al llamar el jsp en esa petición.
    // Con getId, da id del usuario; con getCreationTime, da fecha de creación de la sesión; con getTimeout, da tiempo de expiración de la sesión.
    UserEditorEntity editor = (UserEditorEntity) session.getAttribute("user");
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">

        <!-- Menú izquierdo -->
        <div class="navbar-nav">
            <a class="nav-link active" href="/movies/">Películas</a>
            <a class="nav-link" href="#">Personas</a>
            <a class="nav-link" href="#">Géneros</a>
            <a class="nav-link" href="#">Productoras</a>
            <a class="nav-link" href="#">Idiomas</a>
        </div>

        <!-- Usuario a la derecha -->
        <div class="d-flex text-white small text-end">
            <div>
                Bienvenido, <strong><%= editor.getName() %></strong><br/>
                sessionid: <%= session.getId() %><br/>
                fecha: <%= new Date(session.getCreationTime()) %><br/>
                <a href="/salir" class="text-warning">Salir</a>
            </div>
        </div>

    </div>
</nav>

