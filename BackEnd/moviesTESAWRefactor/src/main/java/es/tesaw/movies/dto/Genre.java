package es.tesaw.movies.dto;

import lombok.Data;

@Data
public class Genre {
    private Integer id;
    private String name;
}
/*
Para las relaciones existen 3 opciones:
    - Puner un DTO completo de la otra entidad(si no tiene la otra DTO la relacion para no hacer un dto infinito)
    - Usar un dto con menos campos de la otra entidad
    - Usar solo un campo de la otra entidad (id/name)
*/