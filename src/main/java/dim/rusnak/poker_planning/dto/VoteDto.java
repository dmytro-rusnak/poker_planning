package dim.rusnak.poker_planning.dto;

public class VoteDto {
    private String idMember;
    private String idUserStory;
    private String value;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
