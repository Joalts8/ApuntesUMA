package es.tesaw.movies.controller;

import es.tesaw.movies.dao.MoviesRepository;
import es.tesaw.movies.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MoviesController {

    @Autowired // Conecta el repositorio con el controlador para poder acceder a la BD
    protected MoviesRepository moviesRepository;
    // También se puede hacer con un constructor.

    /*protected MoviesRepository moviesRepository;
    public MoviesController(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }*/

    @GetMapping("/")
    public String doInit (Model model) {

        List<MovieEntity> pelis = this.moviesRepository.findAll();
        model.addAttribute("pelis", pelis);
        return "movies";
    }

    @GetMapping("/editar")
    public String editar(Model model, @RequestParam("id")int id) {
        MovieEntity peli = this.moviesRepository.findById(id).get();
        model.addAttribute("peli", peli);
        return "edit_movie";
    }

    @PostMapping("/actualizar")
    public String actualizar(Model model) {
        List<MovieEntity> pelis = this.moviesRepository.findAll();
        model.addAttribute("pelis", pelis);
        return "movies";
    }
}
