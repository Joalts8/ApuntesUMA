<%@ page import="java.util.List" %>
<%@ page import="es.tesaw.movies.entity.MovieEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<MovieEntity> peliculas =  (List<MovieEntity>) request.getAttribute("pelis");
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
        <th></th>
        <th></th>
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
        <td><%= peli.getOriginalLanguage().getName() %> </td>
        <td><a href="/editar?id=<%= peli.getId() %>"> Editar</a> </td>
        <td><a href="/borrar?id=<%= peli.getId() %>"> Borrar</a> </td>
<%
    }
%>

</table>