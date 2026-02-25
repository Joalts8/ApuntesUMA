package es.uma.tesaw.demo2026.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;


@Controller
public class Controlador {
    @PostMapping("/inicio")
    public String doInicio() {
        return "test.html";
    }

    @GetMapping("/")
    public String doStart(){
        return "login.html";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("user")String usuario, @RequestParam("pwd")String password, Model model) {
        String respuesta="";
        if(usuario.equals(password)){
            respuesta="Son iguales";
        }else{
            respuesta="No son iguales";
        }

        model.addAttribute("respuesta", respuesta);
        return "test.jsp";
    }

}
