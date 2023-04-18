package dim.rusnak.poker_planning;

import dim.rusnak.poker_planning.dto.MemberDto;
import dim.rusnak.poker_planning.dto.SessionDto;
import dim.rusnak.poker_planning.rest.Api;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Map;

class SessionManagementScenariosTest extends BaseScenariosTest {

    @Test
    public void newSession() {
        var newSessionDto = new SessionDto();
        newSessionDto.setDeskType("deskType");
        newSessionDto.setTitle("title");

        webTestClient
                .post()
                .uri(Api.BASE)
                .body(BodyInserters.fromValue(newSessionDto))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody()
                .jsonPath("$.idSession").isNotEmpty()
                .jsonPath("$.deskType").isEqualTo("deskType")
                .jsonPath("$.title").isEqualTo("title");
    }

    @Test
    public void enterSession() {
        var idSession = createSessionGetId();
        var newMemberDto = new MemberDto();
        newMemberDto.setName("name");

        webTestClient
                .post()
                .uri(Api.BASE + Api.ID_SESSION_MEMBERS, Map.of("idSession", idSession))
                .body(BodyInserters.fromValue(newMemberDto))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody()
                .jsonPath("$.idMember").exists()
                .jsonPath("$.name").isEqualTo("name");

        webTestClient
                .get()
                .uri(Api.BASE + Api.ID_SESSION_MEMBERS, Map.of("idSession", idSession))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.[0].idMember").isNotEmpty()
                .jsonPath("$.[0].name").isEqualTo("name");
    }

    @Test
    public void destroySession() {
        var idSession = createSessionGetId();

        webTestClient
                .delete()
                .uri(Api.BASE + Api.ID_SESSION, Map.of("idSession", idSession))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.idSession").isEqualTo(idSession)
                .jsonPath("$.deskType").isEqualTo("deskType")
                .jsonPath("$.title").isEqualTo("title");
    }


}
