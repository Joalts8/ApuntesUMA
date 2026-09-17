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

    //GetMapping sirve para devolver información. Info en la url(ver Delete)
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

    // DeleteMapping, sirve para borrar una película. Info en url. No devuelve nada.
    //Uso de @PathVariable, donde forma parte de la url el parametro. Si tras ? como en las otras url no REST, con RequestParam.
    @DeleteMapping("/{id}")
    public void doBorrar(@PathVariable Integer id) {
            this.moviesService.borrarMovie(id);
    }

    @GetMapping("/{id}")
    public Movie doEditar (@PathVariable("id") Integer id) {
        return this.moviesService.buscarMovie(id);
    }

    // PostMapping, sirve para crear un nuevo recurso. Info en el cuerpo de la petición. No devuelve nada.
    // Uso de @RequestBody, donde la información viene en el cuerpo de la petición.
   @PostMapping("/")
   public void doGuardarNuevo (@RequestBody Movie movie) {
        this.moviesService.guardarMovie(movie);
   }

    // PutMapping, sirve para actualizar un recurso completo. Info en el cuerpo de la petición. No devuelve nada.
    // PatchMapping, sirve para actualizar un recurso parcialmente. Info en el cuerpo de la petición.
    @PutMapping("/") 
    public void doGuardarEditar (@RequestBody Movie movie) {
        this.moviesService.guardarMovie(movie);
    }

}
