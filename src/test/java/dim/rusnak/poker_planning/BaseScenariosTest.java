package dim.rusnak.poker_planning;

import dim.rusnak.poker_planning.dto.MemberDto;
import dim.rusnak.poker_planning.dto.SessionDto;
import dim.rusnak.poker_planning.dto.UserStoryDto;
import dim.rusnak.poker_planning.dto.VoteDto;
import dim.rusnak.poker_planning.enums.UserStoryStatus;
import dim.rusnak.poker_planning.rest.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseScenariosTest {

    @Autowired
    protected WebTestClient webTestClient;

    protected String createSessionGetId() {
        var newSessionDto = new SessionDto();
        newSessionDto.setDeskType("deskType");
        newSessionDto.setTitle("title");

        var createdSession = webTestClient
                .post()
                .uri(Api.BASE)
                .body(BodyInserters.fromValue(newSessionDto))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(new ParameterizedTypeReference<Map<String, String>>() {
                })
                .returnResult()
                .getResponseBody();

        assertThat(createdSession).isNotNull();
        assertThat(createdSession).isNotEmpty();
        assertThat(createdSession.get("idSession")).isNotNull();

        return createdSession.get("idSession");
    }

    protected String createMemberGetId(String idSession) {
        var newMemberDto = new MemberDto();
        newMemberDto.setName("name");

        var createdMember = webTestClient
                .post()
                .uri(Api.BASE + Api.ID_SESSION_MEMBERS, Map.of("idSession", idSession))
                .body(BodyInserters.fromValue(newMemberDto))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(new ParameterizedTypeReference<Map<String, String>>() {
                })
                .returnResult()
                .getResponseBody();

        assertThat(createdMember).isNotNull();
        assertThat(createdMember).isNotEmpty();
        assertThat(createdMember.get("idMember")).isNotNull();

        return createdMember.get("idMember");
    }

    protected String createUserStoryGetId(String idSession) {
        return createUserStoryGetId(idSession, UserStoryStatus.PENDING);
    }

    protected String createUserStoryGetId(String idSession, UserStoryStatus status) {
        var idUserStory = UUID.randomUUID().toString();
        var userStoryDto = new UserStoryDto();
        userStoryDto.setIdUserStory(idUserStory);
        userStoryDto.setDescription("description");
        userStoryDto.setStatus(status);

        var createdUserStory = webTestClient
                .post()
                .uri(Api.BASE + Api.ID_SESSION_STORIES, Map.of("idSession", idSession))
                .body(BodyInserters.fromValue(userStoryDto))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(new ParameterizedTypeReference<Map<String, String>>() {
                })
                .returnResult()
                .getResponseBody();

        assertThat(createdUserStory).isNotNull();
        assertThat(createdUserStory).isNotEmpty();
        assertThat(createdUserStory.get("idUserStory")).isNotNull();

        return createdUserStory.get("idUserStory");
    }

    protected void createVote(String idSession, String idMember, String idUserStory) {
        var voteDto = new VoteDto();
        voteDto.setIdMember(idMember);
        voteDto.setIdUserStory(idUserStory);
        voteDto.setValue("value");

        var createdVote = webTestClient.post()
                .uri(Api.BASE + Api.ID_SESSION_VOTES, Map.of("idSession", idSession))
                .body(BodyInserters.fromValue(voteDto))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(new ParameterizedTypeReference<Map<String, String>>() {
                })
                .returnResult()
                .getResponseBody();

        assertThat(createdVote).isNotNull();
        assertThat(createdVote).isNotEmpty();
    }
}
