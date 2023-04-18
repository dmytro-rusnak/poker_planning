package dim.rusnak.poker_planning.repository;

import dim.rusnak.poker_planning.entity.MemberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<MemberEntity, String> {

    Optional<MemberEntity> findByName(String name);
}
