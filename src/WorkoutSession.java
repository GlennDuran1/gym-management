public class WorkoutSession {

    private int sessionID;
    private String type;
    private String date;
    private String time; // <-- added
    private int capacity; // <-- added
    private Trainer trainer; // <-- added

    public WorkoutSession(int sessionID, String type, String date, String time, int capacity, Trainer trainer) {
        this.sessionID = sessionID;
        this.type = type;
        this.date = date;
        this.time = time;
        this.capacity = capacity;
        this.trainer = trainer;
    }

    // --------- Getters ---------
    public int getSessionID() {
        return sessionID;
    }

    // duplicate you requested
    public int getSessionId() {
        return sessionID;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getCapacity() {
        return capacity;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    // --------- Setters you requested ---------
    public void setDate(String newDate) {
        this.date = newDate;
    }

    public void setTime(String newTime) {
        this.time = newTime;
    }

    // --------- findTrainer() ---------
    public Trainer findTrainer() {
        return this.trainer; // simply returns the assigned trainer
    }

    public void setTrainerUsername(String newUsername) {
        if (this.trainer != null) {
            this.trainer.setUserName(newUsername); // Person has setUserName()
        } else {
            // If you prefer to look up a Trainer by username and set the reference,
            // do that via GymSystem when updating sessions (better).
            throw new IllegalStateException("No trainer assigned to this session to update username.");
        }
    }

    @Override
    public String toString() {
        return "Session ID: " + sessionID +
                " | " + type +
                " | Date: " + date +
                " | Time: " + time +
                " | Capacity: " + capacity +
                " | Trainer: " + (trainer != null ? trainer.getName() : "None");
    }
}
