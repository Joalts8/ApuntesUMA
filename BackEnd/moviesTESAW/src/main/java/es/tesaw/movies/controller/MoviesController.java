package es.tesaw.movies.controller;

import es.tesaw.movies.dao.*;
import es.tesaw.movies.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
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

    @Autowired
    protected GenreRepository genreRepository;

    @Autowired
    protected SpokenLanguageRepository spokenLanguageRepository;


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
    public String actualizar(@RequestParam("id") Integer id,
                            @RequestParam("title") String titulo,
                            @RequestParam("plot") String sinopsis,
                            @RequestParam("titulo_orig")  String originalTitle,
                            @RequestParam("releaseDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate releaseDate,
                            @RequestParam("duration") Float runtime,
                            @RequestParam("budget") Long budget,
                            @RequestParam("revenue") Long revenue,
                            @RequestParam("status") String status,
                            @RequestParam("tagline") String tagline,
                            @RequestParam("popularity") Float popularity,
                            @RequestParam("rating") Float voteAverage,
                            @RequestParam("voteCount") Integer voteCount,
                            @RequestParam("homepage") String homepage) {
        MovieEntity pelicula = this.moviesRepository.findById(id).get();
        pelicula.setTitle(titulo);
        pelicula.setOriginalTitle(originalTitle);
        pelicula.setOverview(sinopsis);
        pelicula.setReleaseDate(releaseDate);
        pelicula.setRuntime(runtime);
        pelicula.setBudget(budget);
        pelicula.setRevenue(revenue);
        pelicula.setStatus(status);
        pelicula.setTagline(tagline);
        pelicula.setPopularity(popularity);
        pelicula.setVoteAverage(voteAverage);
        pelicula.setVoteCount(voteCount);
        pelicula.setHomepage(homepage);
        this.moviesRepository.save(pelicula);
        return "redirect:/";
    }
}
