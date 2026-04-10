<%@ page import="es.tesaw.movies.entity.MovieEntity" %>
<%@ page import="es.tesaw.movies.entity.SpokenLanguageEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.tesaw.movies.entity.GenreEntity" %>
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
        <form action="/actualizar" method="post">

            <input type="hidden" name="id" value="<%=peli.getId()%>" size="100" maxlength="100">
            
            <label for="title">Título:</label>
            <input type="text" id="title" name="title" value="<%= peli.getTitle() %>">
            <br><br>

            <label for="originalTitle">Título original:</label>
            <input type="text" id="originalTitle" name="titulo_orig" value="<%= peli.getOriginalTitle() %>"/>
            <br/> <br>

            <label for="budget">Presupuesto:</label>
            <input type="number" id="budget" name="budget" value="<%= peli.getBudget() %>">
            <br><br>

            <label for="revenue">Ingresos: </label>
            <input type="number" id="revenue" name="revenue" value="<%= peli.getRevenue() %>" />
            <br/><br>

            <label for="status">Estado: </label>
            <input type="number" id="status" name="status" value="<%=peli.getStatus() %>" />
            <br/><br>

            <label for="rating">Puntuación:</label>
            <input type="number" id="rating" name="rating" value="<%= peli.getVoteAverage() %>" step="0.1" min="0" max="10">
            <br><br>

            <label for="tagline">Tagline: </label>
            <input type="text" id="tagline" size="100" maxlength="100" name="tagline" value="<%= peli.getTagline() %>"/>
            <br/> <br>

            <label for="popularity">Popularidad: </label>
            <input type="number" id="popularity" name="popularity" value="<%=peli.getPopularity() %>" />
            <br/><br>

            <label for="voteCount">Número de votos: </label>
            <input type="number" id="voteCount" name="voteCount" value="<%=peli.getVoteCount() %>" />
            <br/><br>

            <label for="duration">Duración:</label>
            <input type="number" id="duration" name="duration" value="<%= peli.getRuntime() %>">
            <br><br>

            <label for="plot">Sinopsis:</label>
            <textarea id="plot" name="plot" rows="10" cols="200"><%= peli.getOverview() %></textarea>
            <br><br>

            <label for="releaseDate">Fecha de estreno:</label>
            <input type="date" id="releaseDate" name="releaseDate" value="<%= peli.getReleaseDate() %>">
            <br><br>

            <label for="homepage">Sitio web: </label>
            <input type="text" id="homepage" size="100" maxlength="100" name="homepage" value="<%= peli.getHomepage() %>"/>
            <br/> <br>

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
            <br/><br>
            
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

            <input type="submit" value="Guardar">
        </form>

    </body>
</html>