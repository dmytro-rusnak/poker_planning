package dim.rusnak.poker_planning.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SessionDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String idSession;
    private String deskType;
    private String title;

    public String getIdSession() {
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }

    public String getDeskType() {
        return deskType;
    }

    public void setDeskType(String deskType) {
        this.deskType = deskType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
