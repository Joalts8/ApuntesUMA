package es.tesaw.movies.controller.rest;

import es.tesaw.movies.dto.Movie;
import es.tesaw.movies.service.MoviesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador REST para la entidad Movie. Solo devuelve datos, no vistas; o no devuelve nada.
@RestController
@AllArgsConstructor
@RequestMapping("/api/movies")
public class MoviesRestController {

    private final MoviesService moviesService;

    @GetMapping("/")
    public List<Movie> doInit () {
            return this.moviesService.listarMovies();
        }

    @GetMapping({"/filtro/{filtro}/generos/{generos}",
                 "/filtro/{filtro}"})
    public List<Movie> doFiltrar (@PathVariable(required = false) String filtro,
                                  @PathVariable(value = "generos", required = false) List<Integer> generosIds) {

        return this.moviesService.listarMovies(filtro, generosIds);
    }

    // DeleteMapping, sirve para borrar una película.
    @DeleteMapping("/{id}")
    public void doBorrar(@PathVariable Integer id) {
            this.moviesService.borrarMovie(id);
    }

    // getMapping, sirve para traer una película
    @GetMapping("/{id}")
    public Movie doEditar (@PathVariable("id") Integer id) {
        return this.moviesService.buscarMovie(id);
    }

    // PostMapping, sirve para crear una película. 
   @PostMapping("/")
   public void doGuardarNuevo (@RequestBody Movie movie) {
        this.moviesService.guardarMovie(movie);
   }

    // PutMapping, sirve para actualizar una película. 
    @PutMapping("/") 
    public void doGuardarEditar (@RequestBody Movie movie) {
        this.moviesService.guardarMovie(movie);
    }

}
