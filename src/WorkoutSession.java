public class WorkoutSession {

    private int sessionID;
    private String sessionName;
    private String date;

    public WorkoutSession(int sessionID, String sessionName, String date) {
        this.sessionID = sessionID;
        this.sessionName = sessionName;
        this.date = date;
    }

    public int getSessionID() {
        return sessionID;
    }

    public String getSessionName() {
        return sessionName;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Session ID: " + sessionID + " | " + sessionName + " | Date: " + date;
    }
}
