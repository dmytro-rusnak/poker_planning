package dim.rusnak.poker_planning;

import dim.rusnak.poker_planning.dto.UserStoryDto;
import dim.rusnak.poker_planning.dto.VoteDto;
import dim.rusnak.poker_planning.enums.UserStoryStatus;
import dim.rusnak.poker_planning.rest.Api;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class VotesManagementScenariosTest extends BaseScenariosTest {

    @Test
    public void startVotingUserStory() {
        var idSession = createSessionGetId();
        var idUserStory = createUserStoryGetId(idSession);

        var stories = webTestClient.get()
                .uri(Api.BASE + Api.ID_SESSION_STORIES, Map.of("idSession", idSession))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<List<Map<String, String>>>() {
                })
                .returnResult()
                .getResponseBody();

        assertThat(stories).isNotNull();
        assertThat(stories).isNotEmpty();
        assertThat(stories.get(0)).isNotNull();
        assertThat(stories.get(0).get("idUserStory")).isNotNull();
        assertThat(stories.get(0).get("idUserStory")).isEqualTo(idUserStory);

        var userStoryDto = new UserStoryDto();
        userStoryDto.setIdUserStory(idUserStory);
        userStoryDto.setDescription("newDescription");
        userStoryDto.setStatus(UserStoryStatus.VOTING);

       webTestClient.put()
               .uri(Api.BASE + Api.ID_SESSION_ID_USER_STORY, Map.of("idSession", idSession, "idUserStory", idUserStory))
               .body(BodyInserters.fromValue(userStoryDto))
               .accept(MediaType.APPLICATION_JSON)
               .exchange()
               .expectStatus()
               .isOk()
               .expectBody()
               .jsonPath("$.idUserStory").isEqualTo(idUserStory)
               .jsonPath("$.description").isEqualTo("newDescription")
               .jsonPath("$.status").isEqualTo(UserStoryStatus.VOTING.name());
    }

    @Test
    public void voteUserStory() {
        var idSession = createSessionGetId();
        var idMember = createMemberGetId(idSession);
        var idUserStory = createUserStoryGetId(idSession, UserStoryStatus.VOTING);
        var voteDto = new VoteDto();
        voteDto.setIdMember(idMember);
        voteDto.setIdUserStory(idUserStory);
        voteDto.setValue("value");

        webTestClient.post()
                .uri(Api.BASE + Api.ID_SESSION_VOTES, Map.of("idSession", idSession))
                .body(BodyInserters.fromValue(voteDto))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody()
                .jsonPath("$.idMember").isEqualTo(idMember)
                .jsonPath("$.idUserStory").isEqualTo(idUserStory)
                .jsonPath("$.value").isEqualTo("value");
    }

    @Test
    public void listenToVotingStatus() {
        var idSession = createSessionGetId();
        var idMember = createMemberGetId(idSession);
        var idUserStory = createUserStoryGetId(idSession, UserStoryStatus.VOTING);
        createVote(idSession, idMember, idUserStory);

        webTestClient.get()
                .uri(Api.BASE + Api.ID_SESSION_VOTES, Map.of("idSession", idSession))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("[0].idMember").isEqualTo(idMember)
                .jsonPath("[0].idUserStory").isEqualTo(idUserStory)
                .jsonPath("[0].value").isEqualTo("value");
    }

    @Test
    public void finishVotingUserStory() {
        var idSession = createSessionGetId();
        var idMember = createMemberGetId(idSession);
        var idUserStory = createUserStoryGetId(idSession, UserStoryStatus.VOTING);
        createVote(idSession, idMember, idUserStory);

        var userStoryDto = new UserStoryDto();
        userStoryDto.setIdUserStory(idUserStory);
        userStoryDto.setDescription("newDescription");
        userStoryDto.setStatus(UserStoryStatus.VOTE);

        webTestClient.get()
                .uri(Api.BASE + Api.ID_SESSION_VOTES, Map.of("idSession", idSession))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("[0].idMember").isEqualTo(idMember)
                .jsonPath("[0].idUserStory").isEqualTo(idUserStory)
                .jsonPath("[0].value").isEqualTo("value");

        webTestClient.put()
                .uri(Api.BASE + Api.ID_SESSION_ID_USER_STORY, Map.of("idSession", idSession, "idUserStory", idUserStory))
                .body(BodyInserters.fromValue(userStoryDto))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.idUserStory").isEqualTo(idUserStory)
                .jsonPath("$.description").isEqualTo("newDescription")
                .jsonPath("$.status").isEqualTo(UserStoryStatus.VOTE.name());
    }
}
