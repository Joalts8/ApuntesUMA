<%@ page import="java.util.List" %>
<%@ page import="es.tesaw.movies.dto.Movie" %>
<%@ page import="es.tesaw.movies.dto.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Movie> peliculas = (List<Movie>) request.getAttribute("pelis");
    User user = (User) session.getAttribute("user");
%>

<table class="table table-striped table-bordered table-hover align-middle">
    <tr>
        <th>TITLE</th>
        <th>BUDGET</th>
        <th>RATING</th>
        <th>DURATION</th>
        <th>PLOT</th>
        <th>RELEASE DATE</th>
        <th>LANGUAGE</th>
        <th>EDITOR</th>
        <th></th>
        <th></th>
    </tr>
<%
    for (Movie peli: peliculas) {
%>
    <tr>
        <td><%= peli.getTitle() %> </td>
        <td><%= peli.getBudget() %> </td>
        <td><%= peli.getVoteAverage() %> </td>
        <td><%= peli.getRuntime() %> </td>
        <td><%= peli.getOverview() %> </td>
        <td><%= peli.getReleaseDate() %> </td>
        <td><%= peli.getOriginalLanguage().getName() %> </td>
        <td><%= peli.getEditor().getUsername() %> </td>
<%
        if (peli.getEditor().getId() == user.getId()) {
%>
        <td><a href="/movies/editar?id=<%= peli.getId() %>"> Editar</a> </td>
        <td><a href="/movies/borrar?id=<%= peli.getId() %>"> Borrar</a> </td>
<%
        } else {
%>
        <td>Editar</td>
        <td>Borrar</td>
<%
        }
%>
    </tr>
<%
    }
%>

</table>

