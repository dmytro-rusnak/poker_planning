package dim.rusnak.poker_planning.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "votes")
public class VoteEntity {

    @Id
    private VoteEntityKey id;
    @ManyToOne
    @MapsId(value = "idMember")
    @JoinColumn(name = "idMember")
    private MemberEntity member;
    @ManyToOne
    @MapsId(value = "idUserStory")
    @JoinColumn(name = "idUserStory")
    private UserStoryEntity story;
    @Column(name = "vote_value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "idSession", nullable = false)
    private SessionEntity session;

    public VoteEntityKey getId() {
        return id;
    }

    public void setId(VoteEntityKey id) {
        this.id = id;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public UserStoryEntity getStory() {
        return story;
    }

    public void setStory(UserStoryEntity story) {
        this.story = story;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public SessionEntity getSession() {
        return session;
    }

    public void setSession(SessionEntity session) {
        this.session = session;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteEntity that = (VoteEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(member, that.member) && Objects.equals(story, that.story) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, member, story, value);
    }
}
