package dim.rusnak.poker_planning.entity;

import dim.rusnak.poker_planning.enums.UserStoryStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user_stories")
public class UserStoryEntity {
    @Id
    private String idUserStory;
    private String description;
    private UserStoryStatus status = UserStoryStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "idSession", nullable = false)
    private SessionEntity session;
    @OneToMany(mappedBy = "story")
    private Set<VoteEntity> votes = new HashSet<>();

    public String getIdUserStory() {
        return idUserStory;
    }

    public void setIdUserStory(String idUserStory) {
        this.idUserStory = idUserStory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserStoryStatus getStatus() {
        return status;
    }

    public void setStatus(UserStoryStatus status) {
        this.status = status;
    }

    public SessionEntity getSession() {
        return session;
    }

    public void setSession(SessionEntity session) {
        this.session = session;
    }

    public Set<VoteEntity> getVotes() {
        return votes;
    }

    public void setVotes(Set<VoteEntity> votes) {
        this.votes = votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStoryEntity that = (UserStoryEntity) o;
        return Objects.equals(idUserStory, that.idUserStory) && Objects.equals(description, that.description) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUserStory, description, status);
    }
}
