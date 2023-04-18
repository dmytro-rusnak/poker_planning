package dim.rusnak.poker_planning.repository;

import dim.rusnak.poker_planning.entity.VoteEntity;
import dim.rusnak.poker_planning.entity.VoteEntityKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends CrudRepository<VoteEntity, VoteEntityKey> {
}
