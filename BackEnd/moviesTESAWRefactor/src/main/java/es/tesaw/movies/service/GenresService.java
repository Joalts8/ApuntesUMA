package es.tesaw.movies.service;

import es.tesaw.movies.dao.GenreRepository;
import es.tesaw.movies.dto.Genre;
import es.tesaw.movies.entity.GenreEntity;
import es.tesaw.movies.mapper.GenreMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenresService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;


    public List<Genre> listarGenres() {
        List<GenreEntity> entities = this.genreRepository.findAll();
        return genreMapper.toDTOList(entities);
    }
}
