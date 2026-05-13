package es.tesaw.movies.controller;

import es.tesaw.movies.dto.Genre;
import es.tesaw.movies.dto.Movie;
import es.tesaw.movies.dto.SpokenLanguage;
import es.tesaw.movies.dto.User;
import es.tesaw.movies.service.GenresService;
import es.tesaw.movies.service.MoviesService;
import es.tesaw.movies.service.SpokenLanguagesService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/movies")
public class MoviesController {

    private final MoviesService moviesService;
    private final GenresService genresService;
    private final SpokenLanguagesService spokenLanguagesService;


    @GetMapping("/")
    public String doInit (@SessionAttribute(name = "user", required = false) User user,
                          Model model, HttpSession session) {
        if (user == null) {
            return "redirect:/";
        } else {
            List<Movie> pelis = this.moviesService.listarMovies();
            List<Genre> generos = this.genresService.listarGenres();
            model.addAttribute("pelis", pelis);
            model.addAttribute("generos", generos);
            return "movies";
        }
    }

    @PostMapping("/filtrar")
    public String doFiltrar (@RequestParam(value = "filtro", required = false) String filtro,
                             @RequestParam(value = "generos", required = false) List<Integer> generosIds,
                             Model model) {

        List<Movie> lista = this.moviesService.listarMovies(filtro, generosIds);
        model.addAttribute("pelis", lista);
        return "movies_table";
    }

    protected String editarCrear (Integer id, Model model) {
        Movie pelicula = null;

        pelicula = this.moviesService.buscarMovie(id);
        model.addAttribute("pelicula", pelicula);

        List<SpokenLanguage> spokenLanguages = this.spokenLanguagesService.listarLanguages();
        model.addAttribute("idiomas", spokenLanguages);

        List<Genre> genres = this.genresService.listarGenres();
        model.addAttribute("generos", genres);

        return "movie_edit";
    }

    @PostMapping("/anadir")
    public String doAnadir(@SessionAttribute(name = "user", required = false) User user,
                           Model model) {
        if (user == null) {
            return "redirect:/";
        } else {
            return this.editarCrear(null, model);
        }
    }

    @GetMapping("/borrar")
    public String doBorrar(@SessionAttribute(name = "user", required = false) User user,
                                            @RequestParam("id") Integer id) {
        if (user == null) {
            return "redirect:/";
        } else {

            this.moviesService.borrarMovie(id);
            return "redirect:/movies/";
        }
    }

    @GetMapping("/editar")
    public String doEditar (@SessionAttribute(name = "user", required = false) User user,
                            @RequestParam("id") Integer id, Model model) {
        if (user == null) {
            return "redirect:/";
        } else {
            return this.editarCrear(id, model);
        }
    }

   @PostMapping("/guardar")
   public String doGuardar (@SessionAttribute(name = "user") User user,
                            @RequestParam(value = "id", required = false) Integer id,
                            @RequestParam(value = "titulo", required = false) String titulo,
                            @RequestParam(value = "sinopsis", required = false) String sinopsis,
                            @RequestParam(value = "titulo_orig", required = false)  String originalTitle,
                            @RequestParam(value = "fecha", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate releaseDate,
                            @RequestParam(value = "runtime", required = false) Float runtime,
                            @RequestParam(value = "budget", required = false) Long budget,
                            @RequestParam(value = "revenue", required = false) Long revenue,
                            @RequestParam(value = "status", required = false) String status,
                            @RequestParam(value = "tagline", required = false) String tagline,
                            @RequestParam(value = "popularity", required = false) Float popularity,
                            @RequestParam(value = "voteAverage", required = false) Float voteAverage,
                            @RequestParam(value = "voteCount", required = false) Integer voteCount,
                            @RequestParam(value = "homepage", required = false) String homepage,
                            @RequestParam("idioma") Integer iddioma,
                            @RequestParam(value = "generos", required = false) List<Integer> generosIDs) {

        this.moviesService.guardarMovie(user,
                id,
                titulo,
                sinopsis,
                originalTitle,
                releaseDate,
                runtime,
                budget,
                revenue,
                status,
                tagline,
                popularity,
                voteAverage,
                voteCount,
                homepage,
                iddioma,
                generosIDs);
        return "redirect:/movies/";
   }
}
