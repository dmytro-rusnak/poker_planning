package dim.rusnak.poker_planning.rest;

public abstract class Api {

    private static final String ROOT_PATH = "/";

    private Api() {
    }

    public static final String BASE = ROOT_PATH + "sessions";
    public static final String ID_SESSION = "/{idSession}";
    public static final String ID_SESSION_MEMBERS = ID_SESSION + "/members";
    public static final String ID_SESSION_MEMBERS_ID_MEMBER = ID_SESSION_MEMBERS + "/{idMember}";
    public static final String ID_SESSION_STORIES = ID_SESSION + "/stories";
    public static final String ID_SESSION_ID_USER_STORY = ID_SESSION_STORIES + "/{idUserStory}";
    public static final String ID_SESSION_VOTES = ID_SESSION + "/votes";
}
