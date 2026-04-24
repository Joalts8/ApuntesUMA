<%@ page import="es.tesaw.movies.entity.MovieEntity" %>
<%@ page import="es.tesaw.movies.entity.SpokenLanguageEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.tesaw.movies.entity.GenreEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es-ES">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%
            MovieEntity peli = (MovieEntity) request.getAttribute("pelicula");
            List<SpokenLanguageEntity> idiomas = (List<SpokenLanguageEntity>) request.getAttribute("idiomas");
            List<GenreEntity> generos = (List<GenreEntity>) request.getAttribute("generos");
        %>
        <title><%=(peli.getId()!=null?"Editar":"Crear")%> película</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
        rel="stylesheet">
    </head>
    <body>

    <jsp:include page="header.jsp" />

    <div class="container mt-5">
        <H1 class="mb-4"><%=(peli.getId()!=null?"Editar":"Crear")%> película </H1>

        <form action="/movies/actualizar" method="post">
            <%
                if(peli.getId()!=null){
            %>
                    <input type="hidden" name="id" value="<%=peli.getId()%>" size="100" maxlength="100">
            <%
                }
            %>

            
            <div class="mb-3">
                <label for="title">Título:</label>
                <input type="text" id="title" name="title" value="<%= peli.getTitle() %>">
            </div>

            <div class="mb-3">
                <label for="originalTitle">Título original:</label>
                <input type="text" id="originalTitle" name="titulo_orig" value="<%= peli.getOriginalTitle() %>"/>
            </div>

            <div class="mb-3">
                <label for="budget">Presupuesto:</label>
                <input type="number" id="budget" name="budget" value="<%= peli.getBudget() %>">
            </div>

            <div class="mb-3">
                <label for="revenue">Ingresos: </label>
                <input type="number" id="revenue" name="revenue" value="<%= peli.getRevenue() %>" />
            </div>

            <div class="mb-3">
                <label for="status">Estado: </label>
                <input type="number" id="status" name="status" value="<%=peli.getStatus() %>" />
            </div>

            <div class="mb-3">
                <label for="rating">Puntuación:</label>
                <input type="number" id="rating" name="rating" value="<%= peli.getVoteAverage() %>" step="0.1" min="0" max="10">
            </div>

            <div class="mb-3">
                <label for="tagline">Tagline: </label>
                <input type="text" id="tagline" size="100" maxlength="100" name="tagline" value="<%= peli.getTagline() %>"/>
            </div>

            <div class="mb-3">
                <label for="popularity">Popularidad: </label>
                <input type="number" id="popularity" name="popularity" value="<%=peli.getPopularity() %>" />
            </div>

            <div class="mb-3">
                <label for="voteCount">Número de votos: </label>
                <input type="number" id="voteCount" name="voteCount" value="<%=peli.getVoteCount() %>" />
            </div>

            <div class="mb-3">
                <label for="duration">Duración:</label>
                <input type="number" id="duration" name="duration" value="<%= peli.getRuntime() %>">
            </div>

            <div class="mb-3">
                <label for="plot">Sinopsis:</label>
                <textarea id="plot" name="plot" rows="10" cols="200"><%= peli.getOverview() %></textarea>
            </div>

            <div class="mb-3">
                <label for="releaseDate">Fecha de estreno:</label>
                <input type="date" id="releaseDate" name="releaseDate" value="<%= peli.getReleaseDate() %>">
            </div>

            <div class="mb-3">
                <label for="homepage">Sitio web: </label>
                <input type="text" id="homepage" size="100" maxlength="100" name="homepage" value="<%= peli.getHomepage() %>"/>
            </div>

            <div class="mb-3">
                <label for="idioma">Idioma:</label>
                <select name="idioma" id="idioma">
                <%
                    for (SpokenLanguageEntity idioma: idiomas) {
                        String selected = "";
                        if (peli.getId()!= null && idioma.getId() == peli.getOriginalLanguage().getId())
                            selected = "selected";
                %>
                        <option value="<%= idioma.getId() %>" <%= selected %> ><%= idioma.getName()%></option>
                <%
                    }
                %>
                </select>
            </div>
            
            <div class="mb-3">
                <label for="generos">Géneros: </label>
                <%
                    for (GenreEntity genero: generos) {
                        String checked = "";
                        if (peli.getId()!= null && peli.getGenres().contains(genero))
                            checked = "checked";
                %>

                <input type="checkbox" id="generos" <%= checked %> name="generos" value="<%= genero.getId() %>"><%= genero.getName() %>

                <%
                    }
                %>
            </div>

            <input type="submit" value="Guardar">
        </form>
      </div>
    </body>
</html>