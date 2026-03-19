<%@ page import="java.util.List" %>
<%@ page import="es.tesaw.movies.entity.MovieEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de películas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<%
    List<MovieEntity> peliculas =  (List<MovieEntity>) request.getAttribute("pelis");
%>
<body>
<h1>Lista de películas</h1>

<table class="table table-striped table-bordered table-hover align-middle">
    <tr>
        <th>TITLE</th>
        <th>BUDGET</th>
        <th>RATING</th>
        <th>DURATION</th>
        <th>PLOT</th>
        <th>RELEASE DATE</th>
    </tr>
<%
    for (MovieEntity peli: peliculas) {
%>
    <tr>

        <td><%= peli.getTitle() %> </td>
        <td><%= peli.getBudget() %> </td>
        <td><%= peli.getVoteAverage() %> </td>
        <td><%= peli.getRuntime() %> </td>
        <td><%= peli.getOverview() %> </td>
        <td><%= peli.getReleaseDate() %> </td>
        <td><a href="/editar?id=<%= peli.getId() %>">Editar</a></td>
    </tr>
<%
    }
%>

</table>

</body>
</html>
Hacer: en jsp, enlace de edicar con href "/editar?id=%getid%"

Controlador con get con requestparam para /editar con Peli=repositorio.findbyID(id), añade peli como atributo y return jsp de edit

nuevo jsp_edit q coge la peli en el head; formulario con titulo, campo de texto con value el normal+ acabar formulario