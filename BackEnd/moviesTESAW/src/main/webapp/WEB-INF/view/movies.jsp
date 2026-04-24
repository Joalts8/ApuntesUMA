<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="es.tesaw.movies.entity.GenreEntity" %>
<%@ page import="es.tesaw.movies.entity.UserEditorEntity" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Lista de películas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%
    List<GenreEntity> generos = (List<GenreEntity>) request.getAttribute("generos");
%>

<jsp:include page="header.jsp" />

<h1>Lista de películas</h1>

<form id="form_filtrado">
    <input type="text" name="filtro" id="filtro"><br>
    <p>Generos:
    <%
        for (GenreEntity genero: generos) {
    %>
    <input type="checkbox" name="generos" value="<%= genero.getId() %>"/> <%= genero.getName() %>
    <%
        }
    %><p>
    <input type="submit" value="Filtrar" id ="filtrar">
</form>

<!--Este fragmento sirve para cargar un cacho de html. Util si cambia/se recarga -->
<div id="tabla">
    <jsp:include page="movies_table.jsp" />
</div>


<form method="POST" action="/movies/anadir">
    <button type="submit" class="btn btn-primary">Nueva película</button>
</form>

<script>
    document.getElementById("form_filtrado").addEventListener("submit", actualizar_tabla);

    function actualizar_tabla (event) {
        event.preventDefault();

        const form = document.getElementById("form_filtrado");
        const formData = new FormData(form);

        const params = new URLSearchParams();
        params.append("filtro", formData.get("filtro"));

        const generos = formData.getAll("generos");
        generos.forEach(genero => {
            params.append("generos", genero);
        });

        fetch("/movies/filtrar", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: params.toString()
        })
            .then(response => response.text())
            .then(html => {
                document.getElementById("tabla").innerHTML = html;
            })
            .catch(error => console.error("Error:", error));
    }
</script>

</body>
</html>