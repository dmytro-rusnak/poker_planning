package dim.rusnak.poker_planning.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "sessions")
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idSession;
    private String deckType;
    private String title;

    @OneToMany(mappedBy = "session", orphanRemoval = true)
    private Set<VoteEntity> votes = new HashSet<>();
    @OneToMany(mappedBy = "session", orphanRemoval = true)
    private Set<MemberEntity> members = new HashSet<>();
    @OneToMany(mappedBy = "session", orphanRemoval = true)
    private Set<UserStoryEntity> stories = new HashSet<>();

    public String getIdSession() {
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }

    public String getDeckType() {
        return deckType;
    }

    public void setDeckType(String deckType) {
        this.deckType = deckType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String session) {
        this.title = session;
    }

    public Set<VoteEntity> getVotes() {
        return votes;
    }

    public void setVotes(Set<VoteEntity> votes) {
        this.votes = votes;
    }

    public Set<MemberEntity> getMembers() {
        return members;
    }

    public void setMembers(Set<MemberEntity> members) {
        this.members = members;
    }

    public Set<UserStoryEntity> getStories() {
        return stories;
    }

    public void setStories(Set<UserStoryEntity> stories) {
        this.stories = stories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionEntity that = (SessionEntity) o;
        return Objects.equals(idSession, that.idSession) && Objects.equals(deckType, that.deckType) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSession, deckType, title);
    }
}
