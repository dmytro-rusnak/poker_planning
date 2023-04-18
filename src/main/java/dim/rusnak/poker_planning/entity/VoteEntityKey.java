package dim.rusnak.poker_planning.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VoteEntityKey implements Serializable {
    private String idMember;
    private String idUserStory;

    public VoteEntityKey() {
    }

    public VoteEntityKey(String idMember, String idUserStory) {
        this.idMember = idMember;
        this.idUserStory = idUserStory;
    }

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

    public String getIdUserStory() {
        return idUserStory;
    }

    public void setIdUserStory(String idUserStory) {
        this.idUserStory = idUserStory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteEntityKey that = (VoteEntityKey) o;
        return Objects.equals(idMember, that.idMember) && Objects.equals(idUserStory, that.idUserStory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMember, idUserStory);
    }
}
