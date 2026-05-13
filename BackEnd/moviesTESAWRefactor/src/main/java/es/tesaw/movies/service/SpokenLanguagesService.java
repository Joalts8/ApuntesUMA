package es.tesaw.movies.service;

import es.tesaw.movies.dao.SpokenLanguageRepository;
import es.tesaw.movies.dto.SpokenLanguage;
import es.tesaw.movies.entity.SpokenLanguageEntity;
import es.tesaw.movies.mapper.SpokenLanguageMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SpokenLanguagesService {
    private final SpokenLanguageRepository languageRepository;
    private final SpokenLanguageMapper spokenLanguageMapper;


    public List<SpokenLanguage> listarLanguages() {
        List<SpokenLanguageEntity> entities = this.languageRepository.findAll();
        return spokenLanguageMapper.toDTOList(entities);
    }
}
