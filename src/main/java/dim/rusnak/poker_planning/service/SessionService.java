package dim.rusnak.poker_planning.service;

import dim.rusnak.poker_planning.dto.MemberDto;
import dim.rusnak.poker_planning.dto.SessionDto;
import dim.rusnak.poker_planning.dto.UserStoryDto;
import dim.rusnak.poker_planning.dto.VoteDto;
import dim.rusnak.poker_planning.entity.MemberEntity;
import dim.rusnak.poker_planning.entity.SessionEntity;
import dim.rusnak.poker_planning.entity.UserStoryEntity;
import dim.rusnak.poker_planning.entity.VoteEntity;
import dim.rusnak.poker_planning.exception.EntityNotFoundException;
import dim.rusnak.poker_planning.mapper.SessionMapper;
import dim.rusnak.poker_planning.repository.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Objects;

@Service
public class SessionService {

    private final VoteService voteService;
    private final MemberService memberService;
    private final SessionMapper sessionMapper;
    private final UserStoryService userStoryService;
    private final SessionRepository sessionRepository;

    public SessionService(VoteService voteService, MemberService memberService, SessionMapper sessionMapper,
                          UserStoryService userStoryService, SessionRepository sessionRepository) {
        this.voteService = voteService;
        this.memberService = memberService;
        this.sessionMapper = sessionMapper;
        this.userStoryService = userStoryService;
        this.sessionRepository = sessionRepository;
    }

    @Transactional(readOnly = true)
    public SessionDto findSessionById(String idSession) {
        return sessionMapper.entityToDto(findById(idSession));
    }

    @Transactional(readOnly = true)
    public Collection<SessionDto> findAllSessions() {
        return sessionMapper.entitiesToDtos(sessionRepository.findAll());
    }

    @Transactional(readOnly = true)
    public Collection<MemberDto> findAllMembersBySession(String idSession) {
        var session = findById(idSession);
        return memberService.entitiesToDtos(session.getMembers());
    }

    @Transactional(readOnly = true)
    public Collection<UserStoryDto> findAllStoriesBySession(String idSession) {
        var session = findById(idSession);
        return userStoryService.entitiesToDtos(session.getStories());
    }

    @Transactional(readOnly = true)
    public Collection<VoteDto> findAllVotesBySession(String idSession) {
        var session = findById(idSession);
        return voteService.entitiesToDtos(session.getVotes());
    }

    @Transactional
    public SessionDto deleteSessionById(String idSession) {
        var entity = findById(idSession);

        sessionRepository.delete(entity);

        return sessionMapper.entityToDto(entity);
    }

    @Transactional
    public SessionDto createSession(SessionDto dto) {
        return sessionMapper.entityToDto(sessionRepository.save(sessionMapper.dtoToEntity(dto)));
    }

    @Transactional
    public MemberDto joinSession(String idSession, MemberDto dto) {
        var session = findById(idSession);
        var member = memberService.dtoToEntity(dto);

        member.setSession(session);
        session.getMembers().add(member);

        var saved = memberService.save(member);
        sessionRepository.save(session);
        return saved;
    }

    @Transactional
    public UserStoryDto addUserStory(String idSession, UserStoryDto dto) {
        var session = findById(idSession);
        var story = userStoryService.dtoToEntity(dto);

        story.setSession(session);
        session.getStories().add(story);

        var saved = userStoryService.save(story);
        sessionRepository.save(session);
        return saved;
    }

    @Transactional
    public VoteDto createVote(String idSession, VoteDto dto) {
        var session = findById(idSession);
        var member = findMember(session, dto.getIdMember());
        var story = findUserStory(session, dto.getIdUserStory());
        var vote = findVote(session, dto.getIdMember(), dto.getIdUserStory());
        voteService.update(vote, dto);

        vote.setStory(story);
        vote.setMember(member);
        vote.setSession(session);
        story.getVotes().add(vote);

        var saved = voteService.save(vote);
        sessionRepository.save(session);
        return saved;
    }

    @Transactional
    public Collection<MemberDto> logoutSession(String idSession, String idMember) {
        var session = findById(idSession);
        var member = findMember(session, idMember);

        member.setSession(null);
        session.getMembers().remove(member);

        memberService.save(member);
        sessionRepository.save(session);

        return memberService.entitiesToDtos(session.getMembers());
    }

    @Transactional
    public UserStoryDto updateUserStory(String idSession, String idUserStory, UserStoryDto dto) {
        var session = findById(idSession);
        var story = findUserStory(session, idUserStory);
        userStoryService.update(story, dto);

        return userStoryService.save(story);
    }

    @Transactional
    public UserStoryDto deleteUserStory(String idSession, String idUserStory) {
        var session = findById(idSession);
        var story = findUserStory(session, idUserStory);

        return userStoryService.delete(story);
    }

    private SessionEntity findById(String idSession) {
        if (idSession == null) {
            throw new NullPointerException("The `idSession` field can't be null.");
        }

        return sessionRepository.findById(idSession)
                .orElseThrow(() -> new EntityNotFoundException("Session by `%s` id not found.".formatted(idSession)));
    }

    private VoteEntity findVote(SessionEntity entity, String idMember, String idUserStory) {
        return entity.getVotes()
                .stream()
                .filter(vote -> Objects.equals(vote.getId().getIdMember(), idMember)
                        && Objects.equals(vote.getId().getIdUserStory(), idUserStory))
                .findFirst()
                .orElseGet(VoteEntity::new);
    }

    private MemberEntity findMember(SessionEntity entity, String idMember) {
        return entity.getMembers()
                .stream()
                .filter(member -> Objects.equals(member.getIdMember(), idMember))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Member by `%s` id not found.".formatted(idMember)));
    }

    private UserStoryEntity findUserStory(SessionEntity entity, String idUserStory) {
        return entity.getStories()
                .stream()
                .filter(userStory -> Objects.equals(userStory.getIdUserStory(), idUserStory))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("UserStory by `%s` id not found.".formatted(idUserStory)));
    }
}
