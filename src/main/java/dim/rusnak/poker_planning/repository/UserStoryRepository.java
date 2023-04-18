package dim.rusnak.poker_planning.repository;

import dim.rusnak.poker_planning.entity.UserStoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStoryRepository extends CrudRepository<UserStoryEntity, String> {
}
