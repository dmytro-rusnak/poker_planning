package dim.rusnak.poker_planning.rest.controller;

import dim.rusnak.poker_planning.dto.VoteDto;
import dim.rusnak.poker_planning.rest.Api;
import dim.rusnak.poker_planning.service.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
public class VoteController {

    private final SessionService sessionService;

    public VoteController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = Api.ID_SESSION_VOTES, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<VoteDto> getVotes(@PathVariable String idSession) {
        return sessionService.findAllVotesBySession(idSession);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = Api.ID_SESSION_VOTES, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public VoteDto emitVote(@PathVariable String idSession, @RequestBody VoteDto dto) {
        return sessionService.createVote(idSession, dto);
    }

}
