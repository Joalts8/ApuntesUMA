package es.tesaw.movies.mapper;

import es.tesaw.movies.dto.Genre;
import es.tesaw.movies.entity.GenreEntity;
import org.springframework.stereotype.Component;

// Mapper for GenreEntity to Genre DTO
// extiende MapperDTO para reutilizar el método toDTOList y evitar código repetido en otros mappers
// Hace set con los gets de GenreEntity a Genre DTO
@Component
public class GenreMapper extends MapperDTO<Genre, GenreEntity> {

    public Genre toDTO (GenreEntity entity) {
        Genre genre = new Genre();
        genre.setId(entity.getId());
        genre.setName(entity.getName());
        return genre;
    }
}
