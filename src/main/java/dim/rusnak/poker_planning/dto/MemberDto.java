package dim.rusnak.poker_planning.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String idMember;
    private String name;

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
