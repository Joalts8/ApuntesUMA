package es.tesaw.movies.mapper;

import es.tesaw.movies.dto.User;
import es.tesaw.movies.entity.UserEditorEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends MapperDTO<User, UserEditorEntity>{

    public User toDTO(UserEditorEntity entity) {
        User usuario = new User();
        usuario.setId(entity.getId());
        usuario.setName(entity.getName());
        usuario.setUsername(entity.getUsername());
        return usuario;
    }
}