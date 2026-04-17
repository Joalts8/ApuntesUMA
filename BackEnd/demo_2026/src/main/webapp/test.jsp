<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--jsp, mezcla de java y html permitiendo pg dinámicas-->
<html>
    <head>
        <title>Title</title>

    </head>
    <body>
        <h1>${respuesta}</h1> <!--${} muestra el valor del atributo en el modelo del controlador-->

        <% String respuesta = (String) request.getAttribute("respuesta");%>     <!--Dentro de <% %> se puede escribir código java. asi se obtiene el valor del atributo en java, pero al ser object hay q envolverlo-->
        
        <h1><%= respuesta %></h1> <!--<%= %> es una forma de mostrar el valor de una variable sin necesidad de escribir un bloque java, es como un println pero sin terminal-->

        <jsp:include page="test2.jsp" /> <!--Incluir otra jsp dentro de esta, se puede usar para reutilizar código como el header o el footer. Util si cambia/se recarga una tabla-->


<!--Ejemplo de scipt para actualizar tabla con fetch, se coge el formulario y se envía una solicitud POST al servidor , luego se actualiza la parte de la página con la tabla-->
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
            
                fetch("/filtrar", {
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
