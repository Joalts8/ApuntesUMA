package es.tesaw.movies.service;

import es.tesaw.movies.dao.GenreRepository;
import es.tesaw.movies.dao.MoviesRepository;
import es.tesaw.movies.dao.SpokenLanguageRepository;
import es.tesaw.movies.dao.UserEditorRepository;
import es.tesaw.movies.dto.Movie;
import es.tesaw.movies.dto.User;
import es.tesaw.movies.entity.GenreEntity;
import es.tesaw.movies.entity.MovieEntity;
import es.tesaw.movies.entity.SpokenLanguageEntity;
import es.tesaw.movies.mapper.MovieMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class MoviesService {

    private final MoviesRepository moviesRepository;
    private final GenreRepository genreRepository;
    private final SpokenLanguageRepository spokenLanguageRepository;
    private final UserEditorRepository userEditorRepository;
    private final MovieMapper movieMapper;


    public List<Movie> listarMovies () {
        return this.listarMovies(null, null);
    }

    public List<Movie> listarMovies (String nombre, List<Integer> generosIds) {
        List<MovieEntity> lista;

        if ((nombre == null || nombre.isEmpty()) && generosIds == null) {
            lista = this.moviesRepository.findAll();
        } else if (generosIds == null) {
            lista = this.moviesRepository.filtrarPorPalabra(nombre);
        } else {
            lista = this.moviesRepository.filtrarPorPalabraYGeneros(nombre,
                    generosIds);
        }
        return movieMapper.toDTOList(lista);
    }

    public void guardarMovie (Movie movie) {
            this.guardarMovie(movie.getEditor(),
                              movie.getId(),
                              movie.getTitle(),
                              movie.getOverview(),
                              movie.getOriginalTitle(),
                              movie.getReleaseDate(),
                              movie.getRuntime(),
                              movie.getBudget(),
                              movie.getRevenue(),
                              movie.getStatus(),
                              movie.getTagline(),
                              movie.getPopularity(),
                              movie.getVoteAverage(),
                              movie.getVoteCount(),
                              movie.getHomepage(),
                              movie.getOriginalLanguage().getId(),
                              movie.getGeneros());
        }

        public void guardarMovie (User user,
                              Integer id,
                              String titulo,
                              String sinopsis,
                              String originalTitle,
                              LocalDate releaseDate,
                              Float runtime,
                              Long budget,
                              Long revenue,
                              String status,
                              String tagline,
                              Float popularity,
                              Float voteAverage,
                              Integer voteCount,
                              String homepage,
                              Integer iddioma,
                              List<Integer> generosIDs) {
            MovieEntity pelicula = null;
            if (id == null) {
                pelicula = new MovieEntity();
                pelicula.setUserEditor(this.userEditorRepository.findById(user.getId()).get());
            } else {
                pelicula = this.moviesRepository.findById(id).get();
            }

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

            SpokenLanguageEntity idioma = this.spokenLanguageRepository.findById(iddioma).get();
            pelicula.setOriginalLanguage(idioma);

            if (generosIDs != null) {
                List<GenreEntity> genres = this.genreRepository.findAllById(generosIDs);
                pelicula.setGenres(genres);
            }

            this.moviesRepository.save(pelicula);
    }

    public void borrarMovie (Integer id) {
        MovieEntity pelicula = this.moviesRepository.findById(id).get();
        pelicula.deleteGeneres();
        pelicula.deleteProductionCompanies();
        pelicula.deleteSpokenLanguages();
        this.moviesRepository.delete(pelicula);
    }

    public Movie buscarMovie (Integer id) {
        Movie peli;
        if (id == null) {
            peli = new Movie();
        } else {
            MovieEntity pelicula = this.moviesRepository.findById(id).get();
            peli = movieMapper.toDTO(pelicula);
        }
        return peli;
    }

}
