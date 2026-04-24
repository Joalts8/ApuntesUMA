package es.tesaw.movies.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MOVIE")
@Getter
@Setter
@NoArgsConstructor
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String originalTitle;

    @Column(length = 2014)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_language_id")
    private SpokenLanguageEntity originalLanguage;

    @ManyToMany
    @JoinTable(name = "MOVIE_GENRE",                    // Define el nombre de la tabla intermedia. En ToOne solo se usará @joincol(name="id")
        joinColumns = @JoinColumn(name = "movie_id"),   // Permite definir el nombre de la columna q actua como clave foranea de esta entidad
        inverseJoinColumns = @JoinColumn(name = "genre_id"))    // Permite definir el nombre de la columna q actua como clave foranea de otra entidad
    private List<GenreEntity> genres = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "MOVIE_PRODUCTION_COMPANY",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "company_id"))
    private List<ProductionCompanyEntity> productionCompanies = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "MOVIE_LANGUAGE",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "language_id"))
    private List<SpokenLanguageEntity> spokenLanguages = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    private List<MovieCastEntity> cast = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    private List<MovieCrewEntity> crew = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "editor_id")
    private UserEditorEntity userEditor;


    //Metodos para borarr si relacion Many to many
    public void deleteGeneres() {
        for (GenreEntity genre : this.genres) {
            genre.getMovies().remove(this);
        }
        this.genres.clear();
    }

    public void deleteProductionCompanies() {
        for (ProductionCompanyEntity company : this.productionCompanies) {
            company.getMovies().remove(this);
        }
        this.productionCompanies.clear();
    }

    public void deleteSpokenLanguages() {
        for (SpokenLanguageEntity spokenLanguage : this.spokenLanguages) {
            spokenLanguage.getMovies().remove(this);
        }
        this.spokenLanguages.clear();
    }
}
