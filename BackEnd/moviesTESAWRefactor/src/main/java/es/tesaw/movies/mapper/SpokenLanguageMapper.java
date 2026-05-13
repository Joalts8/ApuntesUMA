package es.tesaw.movies.mapper;

import es.tesaw.movies.dto.SpokenLanguage;
import es.tesaw.movies.entity.SpokenLanguageEntity;
import org.springframework.stereotype.Component;

@Component
public class SpokenLanguageMapper extends MapperDTO<SpokenLanguage, SpokenLanguageEntity> {

    public SpokenLanguage toDTO (SpokenLanguageEntity entity) {
        SpokenLanguage language = new SpokenLanguage();
        language.setId(entity.getId());
        language.setName(entity.getName());
        return language;
    }


}
