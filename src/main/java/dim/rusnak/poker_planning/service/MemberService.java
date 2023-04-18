package dim.rusnak.poker_planning.service;

import dim.rusnak.poker_planning.dto.MemberDto;
import dim.rusnak.poker_planning.entity.MemberEntity;
import dim.rusnak.poker_planning.exception.NoContentException;
import dim.rusnak.poker_planning.mapper.MemberMapper;
import dim.rusnak.poker_planning.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class MemberService {

    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;

    public MemberService(MemberMapper memberMapper, MemberRepository memberRepository) {
        this.memberMapper = memberMapper;
        this.memberRepository = memberRepository;
    }

    public MemberEntity dtoToEntity(MemberDto dto) {
        return memberMapper.dtoToEntity(dto);
    }

    public Collection<MemberDto> entitiesToDtos(Collection<MemberEntity> entities) {
        if (entities.isEmpty()) {
            throw new NoContentException();
        }

        return memberMapper.entitiesToDtos(entities);
    }

    @Transactional
    public MemberDto save(MemberEntity entity) {
        return memberMapper.entityToDto(memberRepository.save(entity));
    }
}
