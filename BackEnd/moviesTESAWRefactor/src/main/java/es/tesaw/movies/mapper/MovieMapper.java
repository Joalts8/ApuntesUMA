package es.tesaw.movies.mapper;

import es.tesaw.movies.dto.Movie;
import es.tesaw.movies.entity.GenreEntity;
import es.tesaw.movies.entity.MovieEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class MovieMapper extends MapperDTO<Movie, MovieEntity> {

    private final SpokenLanguageMapper spokenLanguageMapper;
    private final UserMapper userMapper;


    public Movie toDTO (MovieEntity movieEntity) {
        Movie movie = new Movie();
        movie.setId(movieEntity.getId());
        movie.setTitle(movieEntity.getTitle());
        movie.setOriginalTitle(movieEntity.getOriginalTitle());
        movie.setOverview(movieEntity.getOverview());
        movie.setReleaseDate(movieEntity.getReleaseDate());
        movie.setRuntime(movieEntity.getRuntime());
        movie.setBudget(movieEntity.getBudget());
        movie.setRevenue(movieEntity.getRevenue());
        movie.setStatus(movieEntity.getStatus());
        movie.setTagline(movieEntity.getTagline());
        movie.setPopularity(movieEntity.getPopularity());
        movie.setVoteAverage(movieEntity.getVoteAverage());
        movie.setVoteCount(movieEntity.getVoteCount());
        movie.setHomepage(movieEntity.getHomepage());
        List<Integer> genresIds = new ArrayList<>();
        for (GenreEntity genre : movieEntity.getGenres()) {
            genresIds.add(genre.getId());
        }
        movie.setGeneros(genresIds);
        movie.setOriginalLanguage(spokenLanguageMapper.toDTO(movieEntity.getOriginalLanguage()));
        movie.setEditor(userMapper.toDTO(movieEntity.getUserEditor()));
        return movie;
    }

}
