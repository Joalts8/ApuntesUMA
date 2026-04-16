<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de películas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<h1>Lista de películas</h1>

<form action="/filtrar" method="post">
    <input type="text" name="filtro" id="filtro">
    <input type="submit" value="Filtrar" id ="filtrar">
</form>

<div id="tabla">
    <jsp:include page="movies_table.jsp" />
</div>


<form method="POST" action="/anadir">
    <button type="submit" class="btn btn-primary">Nueva película</button>
</form>

</body>
</html>