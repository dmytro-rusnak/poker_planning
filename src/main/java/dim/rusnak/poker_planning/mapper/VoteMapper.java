package dim.rusnak.poker_planning.mapper;

import dim.rusnak.poker_planning.dto.VoteDto;
import dim.rusnak.poker_planning.entity.VoteEntity;
import dim.rusnak.poker_planning.entity.VoteEntityKey;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.StreamSupport;

@Component
public final class VoteMapper {

    private VoteMapper() {
    }

    public VoteDto entityToDto(VoteEntity entity) {
        var dto = new VoteDto();
        dto.setIdMember(entity.getId().getIdMember());
        dto.setIdUserStory(entity.getId().getIdUserStory());
        dto.setValue(entity.getValue());
        return dto;
    }

    public VoteEntity dtoToEntity(VoteDto dto) {
        var entity = new VoteEntity();
        entity.setId(new VoteEntityKey(dto.getIdMember(), dto.getIdUserStory()));
        entity.setValue(dto.getValue());
        return entity;
    }

    public Collection<VoteDto> entitiesToDtos(Iterable<VoteEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false).map(this::entityToDto).toList();
    }

    public void update(VoteEntity target, VoteDto source) {
        target.setId(new VoteEntityKey(source.getIdMember(), source.getIdUserStory()));
        target.setValue(source.getValue());
    }
}
