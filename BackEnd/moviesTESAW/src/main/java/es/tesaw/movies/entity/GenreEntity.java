package es.tesaw.movies.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity        //Indica que esta clase es una entidad de JPA, lo que significa que se mapeará a una tabla en laBD
//@Getter       @Data ya genera el setter y el getter
//@Setter       @Data ya genera el setter y el getter
@Data          //Genera automáticamente los métodos getters, setters, equals, hashCode y toString para la clase.
@Table(name = "GENRE")      //Especifica el nombre de la tabla en la base de datos. Asume el de la clase sino.
@NoArgsConstructor          //Genera un constructor sin argumentos para la clase.
public class GenreEntity {  //implements serializable es recomendado

    @Id         //Indica que este campo es la clave primaria de la tabla.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //indica que la clave la genera la BD
    @Basic(optional = false)    //Indica que este campo es obligatorio (no puede ser nulo). Basic también da cuando fetch
    @Column(name = "id", nullable = false) //Configura la columna, name(asume el de la variable) nulo(asume true), length(asume 255) updatable (asume true),unique(asueme false)
    private Integer id;

    private String name;

    //Indica que existe una relación de muchos a muchos que se mapea a través del campo "genres" en la clase Movies. OneToOne, ManyToOne y OneToMany
    @ManyToMany(mappedBy = "genres")        // OneTo se hace igual
    private List<MovieEntity> movies = new ArrayList<>();
}
