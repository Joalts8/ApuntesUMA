package es.tesaw.movies.controller;

import es.tesaw.movies.dao.*;
import es.tesaw.movies.entity.MovieEntity;
import es.tesaw.movies.entity.GenreEntity;
import es.tesaw.movies.entity.SpokenLanguageEntity;
import es.tesaw.movies.entity.UserEditorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/movies")
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
    public String doInit (@SessionAttribute(name = "user", required = false) UserEditorEntity user, Model model, HttpSession session) {
        if (user == null) {
            return "redirect:/";
        } else {
            List<MovieEntity> pelis = this.moviesRepository.findAll();
            model.addAttribute("pelis", pelis);
            List<GenreEntity> generos = this.genreRepository.findAll();
            model.addAttribute("generos", generos);
            return "movies";
        }
    }


    @GetMapping("/editar")
    public String doEditar (@SessionAttribute(name = "user", required = false) UserEditorEntity user,
                            @RequestParam("id") Integer id, Model model) {
        if (user == null) {
            return "redirect:/";
        } else {
            return this.editarCrear(id, model);
        }
    }


    @PostMapping("/anadir")
    public String doAnadir(@SessionAttribute(name = "user", required = false) UserEditorEntity user,
                           Model model) {
        if (user == null) {
            return "redirect:/";
        } else {
            return this.editarCrear(null, model);
        }
    }


    @GetMapping("/borrar")
    public String doBorrar(@SessionAttribute(name = "user", required = false) UserEditorEntity user,
                           @RequestParam("id") Integer id) {
        if (user == null) {
            return "redirect:/";
        } else {
            MovieEntity pelicula = this.moviesRepository.findById(id).get();
            pelicula.deleteGeneres();
            pelicula.deleteProductionCompanies();
            pelicula.deleteSpokenLanguages();
            this.moviesRepository.delete(pelicula);
            return "redirect:/movies/";
        }
    }

    //Ejemplo de consulta custom
    @PostMapping("/filtrar")
    public String filtrar(Model model, @RequestParam("filtro") String filtro,
                          @RequestParam(value = "generos", required = false) List<Integer> generosIds) {
        List<MovieEntity> pelis;
        if (filtro.equals("") && generosIds == null) {
            pelis = this.moviesRepository.findAll();
        } else if (generosIds == null) {
            pelis = this.moviesRepository.filtroPorPalabra(filtro);
        } else {
            pelis = this.moviesRepository.filtrarPorPalabraYGeneros(filtro,
                    generosIds);
        }
        model.addAttribute("pelis", pelis);
        return "movies_table";
    }


    @PostMapping("/actualizar")
    public String actualizar(@SessionAttribute(name = "user", required = false) UserEditorEntity user,
                            @RequestParam(value = "id", required = false) Integer id,
                            @RequestParam(value = "title", required = false) String titulo,
                            @RequestParam(value ="plot", required = false) String sinopsis,
                            @RequestParam(value ="titulo_orig", required = false)  String originalTitle,
                            @RequestParam(value ="releaseDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate releaseDate,
                            @RequestParam(value ="duration", required = false) Float runtime,
                            @RequestParam(value ="budget", required = false) Long budget,
                            @RequestParam(value ="revenue", required = false) Long revenue,
                            @RequestParam(value ="status", required = false) String status,
                            @RequestParam(value ="tagline", required = false) String tagline,
                            @RequestParam(value ="popularity", required = false) Float popularity,
                            @RequestParam(value ="rating", required = false) Float voteAverage,
                            @RequestParam(value = "voteCount", required = false) Integer voteCount,
                            @RequestParam(value = "homepage", required = false) String homepage,
                            @RequestParam("idioma") Integer OriginalIdioma,
                            @RequestParam(value = "generos", required = false) List<Integer> generosIDs) {

        if (user == null) {
            return "redirect:/";
        }

        MovieEntity pelicula = this.buscarPelicula(id);

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

        SpokenLanguageEntity idioma = this.spokenLanguageRepository.findById(OriginalIdioma).get();
        pelicula.setOriginalLanguage(idioma);

        if (generosIDs != null) {
            List<GenreEntity> genres = this.genreRepository.findAllById(generosIDs);
            pelicula.setGenres(genres);
        }

        if (pelicula.getUserEditor() == null) {
            pelicula.setUserEditor(user);
        }

        this.moviesRepository.save(pelicula);
        return "redirect:/movies/";
    }


    protected String editarCrear(Integer id, Model model) {
        MovieEntity peli = buscarPelicula(id);
        model.addAttribute("pelicula", peli);
        List<GenreEntity> generos = this.genreRepository.findAll();
        model.addAttribute("generos", generos);
        List<SpokenLanguageEntity> idiomas = this.spokenLanguageRepository.findAll();
        model.addAttribute("idiomas", idiomas);
        return "edit_movie";
    }

    protected MovieEntity buscarPelicula(Integer id) {
        MovieEntity pelicula = null;
        if (id == null) {
            pelicula = new MovieEntity();
        } else {
            pelicula = this.moviesRepository.findById(id).get();
        }
        return pelicula;
    }
}
