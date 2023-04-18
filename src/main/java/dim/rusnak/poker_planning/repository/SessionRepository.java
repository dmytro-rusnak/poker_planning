package dim.rusnak.poker_planning.repository;

import dim.rusnak.poker_planning.entity.SessionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends CrudRepository<SessionEntity, String> {
}
