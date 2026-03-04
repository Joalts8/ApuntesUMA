
package es.tesaw.movies.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity     //Indica que esta clase es una entidad de JPA, lo que significa que se mapeará a una tabla en la base de datos.
@Data       //Genera automáticamente los métodos getters, setters, equals, hashCode y toString para la clase. Esto por lombok
@Table(name = "GENRES")     //Especifica el nombre de la tabla en la base de datos a la que se mapeará esta entidad.
public class Genres implements Serializable {
    @Id                         //Indica que este campo es la clave primaria.
    @Basic(optional = false)    //Indica que este campo es obligatorio (no puede ser nulo).
    @Column(name = "ID", nullable = false)//Especifica el nombre de la columna en la base de datos a la que se mapeará este campo, y que no puede ser nulo.
    private Integer id;

    @Column(name = "NAME")
    private String name;

    //Indica que existe una relación de muchos a muchos entre Genres y Movies, y que esta relación se mapea a través del campo "genresList" en la clase Movies.
    @ManyToMany(mappedBy = "genresList")
    private List<Movies> moviesList;

    @Override
    public String toString() {
        return "es.taw.movies.entity.Genres[ id=" + id + " ]";
    }
    
}
