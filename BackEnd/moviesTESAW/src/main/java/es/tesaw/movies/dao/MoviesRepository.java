package es.tesaw.movies.dao;

import es.tesaw.movies.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MoviesRepository extends JpaRepository<MovieEntity, Integer> {

    // Creacion de consultas personalizadas a la base de datos, sirve para evitar tener que hacer consultas a mano y SQL INJECTION
    @Query("SELECT m FROM MovieEntity m WHERE m.originalTitle LIKE concat('%', :cadena, '%') or m.overview LIKE concat('%', :cadena, '%')")
    public List<MovieEntity> filtroPorPalabra(@Param("cadena") String filtro);
}

/* Nos permite hacer consultas a la base de datos.
 *  No hay que implementar nada, solo crear la clase que hereda de jpaRepository y ya tiene implementados los métodos */