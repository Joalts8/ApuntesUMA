package es.uma.tesaw.demo2026.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

// Definimos un controlador mediante annotación @Controller, que se encargará de manejar las solicitudes HTTP y devolver respuestas adecuadas. 
// @GetMapping y @PostMapping se utilizan para mapear las solicitudes GET y POST a métodos específicos dentro del controlador, permitiendo así la gestión de diferentes tipos de solicitudes y la interacción con el modelo para pasar datos a las vistas.

// Get será para mostrar páginas, Post para procesar datos enviados por el usuario. El modelo se utiliza para pasar datos desde el controlador a la vista(JSP), permitiendo mostrar resultados dinámicos en las páginas web.

@Controller
@RequestMapping("/jsp")     //Especifica que todas las rutas de este controlador estarán prefijadas. Nos permirte organizar mejor las rutas y evitar conflictos con otros controladores.
public class Controlador {

    // @GetMapping lanzara su método cuando se reciba una solicitud GET en (""). Devuelve la página a mostrar
    @GetMapping("/")
    public String doInicio(){
        return "login.html";        //Ruta a devolver asumiendo que esta en la carpeta static(o webapp para los jsp).
    }


    /* @PostMapping lanzara su método cuando se reciba una solicitud POST en (""). Recibe los parámetros de la solicitud y el modelo para pasar datos a la vista. Realiza las operaciones necesarias y devuelve la página a mostrar.
        Puede hacerse con get, pero los datos se mostrarían en la URL, lo que no es seguro para información sensible. */
    // @RequestParam se utiliza para extraer los parámetros de la solicitud HTTP, mismos nombres "" que en el formulario.
    @PostMapping("/login")
    public String doLogin(@RequestParam("user")String usuario, @RequestParam("pwd")String password, Model model) {
        String respuesta="";

        if(usuario.equals(password)){               //Operaciones
            respuesta="Son iguales";
        }else{
            respuesta="No son iguales";
        }

        model.addAttribute("respuesta", respuesta); // Agrega el atributo "respuesta" al modelo, que se podrá acceder en la vista para mostrar el resultado.
        return "test.jsp";
    }
}
