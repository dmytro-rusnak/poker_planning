package dim.rusnak.poker_planning;

import dim.rusnak.poker_planning.dto.UserStoryDto;
import dim.rusnak.poker_planning.enums.UserStoryStatus;
import dim.rusnak.poker_planning.rest.Api;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Map;
import java.util.UUID;

class UserStoriesManagementScenariosTest extends BaseScenariosTest {

    @Test
    public void addUserStory() {
        var idSession = createSessionGetId();
        var idUserStory = UUID.randomUUID().toString();
        var userStoryDto = new UserStoryDto();
        userStoryDto.setIdUserStory(idUserStory);
        userStoryDto.setDescription("description");

        webTestClient
                .post()
                .uri(Api.BASE + Api.ID_SESSION_STORIES, Map.of("idSession", idSession))
                .body(BodyInserters.fromValue(userStoryDto))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody()
                .jsonPath("$.idUserStory").isEqualTo(idUserStory)
                .jsonPath("$.description").isEqualTo("description")
                .jsonPath("$.status").isEqualTo(UserStoryStatus.PENDING.name());
    }

    @Test
    public void deleteUserStory() {
        var idSession = createSessionGetId();
        var idUserStory = createUserStoryGetId(idSession);

        webTestClient.delete()
                .uri(Api.BASE + Api.ID_SESSION_ID_USER_STORY, Map.of("idSession", idSession, "idUserStory", idUserStory))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.idUserStory").isEqualTo(idUserStory)
                .jsonPath("$.description").isEqualTo("description")
                .jsonPath("$.status").isEqualTo(UserStoryStatus.PENDING.name());
    }


}
