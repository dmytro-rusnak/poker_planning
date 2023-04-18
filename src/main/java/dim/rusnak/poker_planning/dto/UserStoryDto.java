package dim.rusnak.poker_planning.dto;

import dim.rusnak.poker_planning.enums.UserStoryStatus;

public class UserStoryDto {
    private String idUserStory;
    private String description;
    private UserStoryStatus status = UserStoryStatus.PENDING;

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
}
