package dim.rusnak.poker_planning.service;

import dim.rusnak.poker_planning.dto.VoteDto;
import dim.rusnak.poker_planning.entity.VoteEntity;
import dim.rusnak.poker_planning.exception.NoContentException;
import dim.rusnak.poker_planning.mapper.VoteMapper;
import dim.rusnak.poker_planning.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class VoteService {

    private final VoteMapper voteMapper;
    private final VoteRepository voteRepository;

    public VoteService(VoteMapper voteMapper, VoteRepository voteRepository) {
        this.voteMapper = voteMapper;
        this.voteRepository = voteRepository;
    }

    public VoteDto save(VoteEntity entity) {
        return voteMapper.entityToDto(voteRepository.save(entity));
    }

    public void update(VoteEntity entity, VoteDto dto) {
        voteMapper.update(entity, dto);
    }

    public Collection<VoteDto> entitiesToDtos(Collection<VoteEntity> entities) {
        if (entities.isEmpty()) {
            throw new NoContentException();
        }

        return voteMapper.entitiesToDtos(entities);
    }
}
