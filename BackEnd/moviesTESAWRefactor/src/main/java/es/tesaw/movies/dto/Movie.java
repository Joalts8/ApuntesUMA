package es.tesaw.movies.dto;

import es.tesaw.movies.entity.SpokenLanguageEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Movie {
    private Integer id;
    private String title;
    private String originalTitle;
    private String overview;
    private LocalDate releaseDate;
    private Float runtime;
    private Long budget;
    private Long revenue;
    private String status;
    private String tagline;
    private Float popularity;
    private Float voteAverage;
    private Integer voteCount;
    private String homepage;
    private List<Integer> generos;
    private SpokenLanguage originalLanguage;
    private User editor;

}
