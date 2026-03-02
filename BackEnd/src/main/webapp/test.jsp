<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--jsp, mezcla de java y html permitiendo pg dinámicas-->
<html>
    <head>
        <title>Title</title>

    </head>
    <body>
        <h1>${respuesta}</h1> <!--${} muestra el valor del atributo en el modelo del controlador-->
        <% String respuesta = (String) request.getAttribute("respuesta");%>     <!--Dentro de <% %> se puede escribir código java, si dentro se vuelve a escribir <% %> se cierra el bloque anterior-->
        <h1><%= respuesta %></h1> <!--<%= %> es una forma de mostrar el valor de una variable sin necesidad de escribir un bloque java, es como un println-->
    </body>
</html>
