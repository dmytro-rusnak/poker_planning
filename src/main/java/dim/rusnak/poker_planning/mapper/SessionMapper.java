package dim.rusnak.poker_planning.mapper;

import dim.rusnak.poker_planning.dto.SessionDto;
import dim.rusnak.poker_planning.entity.SessionEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.StreamSupport;

@Component
public final class SessionMapper {

    private SessionMapper() {
    }

    public SessionDto entityToDto(SessionEntity entity) {
        var dto = new SessionDto();
        dto.setTitle(entity.getTitle());
        dto.setDeskType(entity.getDeckType());
        dto.setIdSession(entity.getIdSession());
        return dto;
    }

    public SessionEntity dtoToEntity(SessionDto dto) {
        var entity = new SessionEntity();
        entity.setTitle(dto.getTitle());
        entity.setDeckType(dto.getDeskType());
        return entity;
    }

    public Collection<SessionDto> entitiesToDtos(Iterable<SessionEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false).map(this::entityToDto).toList();
    }
}
