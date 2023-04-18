package dim.rusnak.poker_planning.rest.controller;

import dim.rusnak.poker_planning.dto.MemberDto;
import dim.rusnak.poker_planning.dto.SessionDto;
import dim.rusnak.poker_planning.rest.Api;
import dim.rusnak.poker_planning.service.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(Api.BASE)
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<SessionDto> getSessions() {
        return sessionService.findAllSessions();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SessionDto createSession(@RequestBody SessionDto dto) {
        return sessionService.createSession(dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = Api.ID_SESSION, produces = MediaType.APPLICATION_JSON_VALUE)
    public SessionDto getSession(@PathVariable String idSession) {
        return sessionService.findSessionById(idSession);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = Api.ID_SESSION, produces = MediaType.APPLICATION_JSON_VALUE)
    public SessionDto deleteSession(@PathVariable String idSession) {
        return sessionService.deleteSessionById(idSession);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = Api.ID_SESSION_MEMBERS, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<MemberDto> getSessionMembers(@PathVariable String idSession) {
        return sessionService.findAllMembersBySession(idSession);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = Api.ID_SESSION_MEMBERS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public MemberDto joinSession(@PathVariable String idSession, @RequestBody MemberDto dto) {
        return sessionService.joinSession(idSession, dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = Api.ID_SESSION_MEMBERS_ID_MEMBER, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<MemberDto> logoutMember(@PathVariable String idSession, @PathVariable String idMember) {
        return sessionService.logoutSession(idSession, idMember);
    }

}
