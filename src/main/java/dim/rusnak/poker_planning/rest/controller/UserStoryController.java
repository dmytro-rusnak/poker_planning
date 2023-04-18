package dim.rusnak.poker_planning.rest.controller;

import dim.rusnak.poker_planning.dto.UserStoryDto;
import dim.rusnak.poker_planning.rest.Api;
import dim.rusnak.poker_planning.service.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(Api.BASE)
public class UserStoryController {

    private final SessionService sessionService;

    public UserStoryController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = Api.ID_SESSION_STORIES, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<UserStoryDto> getStories(@PathVariable String idSession) {
        return sessionService.findAllStoriesBySession(idSession);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = Api.ID_SESSION_STORIES, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserStoryDto createStory(@PathVariable String idSession, @RequestBody UserStoryDto dto) {
        return sessionService.addUserStory(idSession, dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = Api.ID_SESSION_ID_USER_STORY, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserStoryDto updateStory(@PathVariable String idSession, @PathVariable String idUserStory, @RequestBody UserStoryDto dto) {
        return sessionService.updateUserStory(idSession, idUserStory, dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = Api.ID_SESSION_ID_USER_STORY, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserStoryDto deleteStory(@PathVariable String idSession, @PathVariable String idUserStory) {
        return sessionService.deleteUserStory(idSession, idUserStory);
    }

}
