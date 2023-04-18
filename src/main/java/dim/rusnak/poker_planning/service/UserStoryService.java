package dim.rusnak.poker_planning.service;

import dim.rusnak.poker_planning.dto.UserStoryDto;
import dim.rusnak.poker_planning.entity.UserStoryEntity;
import dim.rusnak.poker_planning.enums.UserStoryStatus;
import dim.rusnak.poker_planning.exception.ForbiddenException;
import dim.rusnak.poker_planning.mapper.UserStoryMapper;
import dim.rusnak.poker_planning.repository.UserStoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class UserStoryService {

    private final UserStoryMapper userStoryMapper;
    private final UserStoryRepository userStoryRepository;

    public UserStoryService(UserStoryMapper userStoryMapper, UserStoryRepository userStoryRepository) {
        this.userStoryMapper = userStoryMapper;
        this.userStoryRepository = userStoryRepository;
    }

    public UserStoryEntity dtoToEntity(UserStoryDto dto) {
        return userStoryMapper.dtoToEntity(dto);
    }

    public void update(UserStoryEntity entity, UserStoryDto dto) {
        userStoryMapper.update(entity, dto);
    }

    public Collection<UserStoryDto> entitiesToDtos(Collection<UserStoryEntity> entities) {
        return userStoryMapper.entitiesToDtos(entities);
    }

    @Transactional
    public UserStoryDto save(UserStoryEntity entity) {
        return userStoryMapper.entityToDto(userStoryRepository.save(entity));
    }

    @Transactional
    public UserStoryDto delete(UserStoryEntity entity) {
        if(!UserStoryStatus.PENDING.equals(entity.getStatus())) {
            throw new ForbiddenException();
        }

        userStoryRepository.delete(entity);
        return userStoryMapper.entityToDto(entity);
    }
}
