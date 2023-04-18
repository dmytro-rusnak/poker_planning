package dim.rusnak.poker_planning.mapper;

import dim.rusnak.poker_planning.dto.MemberDto;
import dim.rusnak.poker_planning.entity.MemberEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.StreamSupport;

@Component
public final class MemberMapper {

    private MemberMapper() {
    }

    public MemberDto entityToDto(MemberEntity entity) {
        var dto = new MemberDto();
        dto.setName(entity.getName());
        dto.setIdMember(entity.getIdMember());
        return dto;
    }

    public MemberEntity dtoToEntity(MemberDto dto) {
        var entity = new MemberEntity();
        entity.setName(dto.getName());
        return entity;
    }

    public Collection<MemberDto> entitiesToDtos(Iterable<MemberEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false).map(this::entityToDto).toList();
    }
}
