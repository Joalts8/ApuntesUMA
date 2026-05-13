package es.tesaw.movies.service;

import es.tesaw.movies.dao.UserEditorRepository;
import es.tesaw.movies.dto.User;
import es.tesaw.movies.entity.UserEditorEntity;
import es.tesaw.movies.mapper.GenreMapper;
import es.tesaw.movies.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticateService {

  private final UserEditorRepository userEditorRepository;
  private final UserMapper userMapper;


  public User login(String username, String password) {
    UserEditorEntity editor = this.userEditorRepository.autheticate(username, password);
    if (editor == null) {
      return null;
    } else {
      return userMapper.toDTO(editor);
    }
  }

}
