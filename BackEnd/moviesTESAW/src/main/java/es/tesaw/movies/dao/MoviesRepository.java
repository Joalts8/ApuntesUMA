package es.tesaw.movies.dao;

import es.tesaw.movies.entity.Genres;
import es.tesaw.movies.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesRepository extends JpaRepository<Movies, Integer> {
}
/* Nos permite hacer consultas a la base de datos.
*  No hay que implementar nada, solo crear la clase que hereda de jpaRepository y ya tiene implementados los métodos */