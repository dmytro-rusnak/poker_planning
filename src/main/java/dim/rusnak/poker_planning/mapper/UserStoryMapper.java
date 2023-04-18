package dim.rusnak.poker_planning.mapper;

import dim.rusnak.poker_planning.dto.UserStoryDto;
import dim.rusnak.poker_planning.entity.UserStoryEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.StreamSupport;

@Component
public final class UserStoryMapper {

    private UserStoryMapper() {
    }

    public UserStoryDto entityToDto(UserStoryEntity entity) {
        var dto = new UserStoryDto();
        dto.setIdUserStory(entity.getIdUserStory());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public UserStoryEntity dtoToEntity(UserStoryDto dto) {
        var entity = new UserStoryEntity();
        entity.setIdUserStory(dto.getIdUserStory());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    public Collection<UserStoryDto> entitiesToDtos(Iterable<UserStoryEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false).map(this::entityToDto).toList();
    }

    public void update(UserStoryEntity target, UserStoryDto source) {
        target.setDescription(source.getDescription());
        target.setStatus(source.getStatus());
    }
}
