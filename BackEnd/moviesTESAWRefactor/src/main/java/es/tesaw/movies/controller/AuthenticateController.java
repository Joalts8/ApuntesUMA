package es.tesaw.movies.controller;

import es.tesaw.movies.dto.User;
import es.tesaw.movies.service.AuthenticateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class AuthenticateController {

    private final AuthenticateService authenticateService;

    @GetMapping("/")
    public String doLogin () {
        return "login";
    }

    @PostMapping("/autentica")
    public String doAutentica (@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               HttpSession session,
                               Model model) {
        User editor = this.authenticateService.login(username, password);
        if (editor == null) {
            model.addAttribute("error", "Usuario no encontrado o error de autenticación");
            return "login";
        } else {
            session.setAttribute("user", editor);
            return "redirect:/movies/";
        }
    }

    @GetMapping("/salir")
    public String doSalir (HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
