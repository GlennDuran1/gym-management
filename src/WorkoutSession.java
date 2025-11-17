/**
 * The WorkoutSession class represents a scheduled workout session at the gym.
 * Each session contains details such as its ID, type, date, time, capacity,
 * and the trainer assigned to conduct the session.
 *
 * This class provides methods to retrieve and update session information,
 * as well as update the trainer's username when needed.
 */
public class WorkoutSession {

    /** Unique identifier for the workout session. */
    private int sessionID;

    /** Type of workout session (e.g., Yoga, Cardio, Weightlifting). */
    private String type;

    /** Date on which the session is scheduled. */
    private String date;

    /** Time at which the session takes place. */
    private String time;

    /** Maximum number of participants allowed in the session. */
    private int capacity;

    /** Trainer assigned to conduct the workout session. */
    private Trainer trainer;

    /**
     * Constructs a new WorkoutSession with the given details.
     *
     * @param sessionID unique identifier for the session
     * @param type      type of workout session
     * @param date      session date
     * @param time      session time
     * @param capacity  maximum allowed participants
     * @param trainer   trainer assigned to the session
     */
    public WorkoutSession(int sessionID, String type, String date, String time, int capacity, Trainer trainer) {
        this.sessionID = sessionID;
        this.type = type;
        this.date = date;
        this.time = time;
        this.capacity = capacity;
        this.trainer = trainer;
    }

    // -------------------- Getters --------------------

    

    /**
     * Gets session Id
     *
     * @return sessionID
     */
    public int getSessionId() {
        return sessionID;
    }

    /**
     * Gets the session type.
     *
     * @return type of session
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the session date.
     *
     * @return date of session
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets the session time.
     *
     * @return time of session
     */
    public String getTime() {
        return time;
    }

    /**
     * Gets the maximum capacity of the session.
     *
     * @return maximum number of participants
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Gets the trainer assigned to this workout session.
     *
     * @return Trainer object or null if none assigned
     */
    public Trainer getTrainer() {
        return trainer;
    }

    // -------------------- Setters --------------------

    /**
     * Updates the session date.
     *
     * @param newDate the new date of the session
     */
    public void setDate(String newDate) {
        this.date = newDate;
    }

    /**
     * Updates the session time.
     *
     * @param newTime the new time of the session
     */
    public void setTime(String newTime) {
        this.time = newTime;
    }

    // -------------------- Trainer Methods --------------------

    /**
     * Returns the trainer assigned to this session.
     *
     * @return the Trainer object assigned to this session
     */
    public Trainer findTrainer() {
        return this.trainer;
    }

    /**
     * Updates the username of the trainer assigned to this session.
     *
     * @param newUsername the new username to assign to the trainer
     * @throws IllegalStateException if no trainer is assigned to the session
     */
    public void setTrainerUsername(String newUsername) {
        if (this.trainer != null) {
            this.trainer.setUserName(newUsername);
        } else {
            throw new IllegalStateException("No trainer assigned to this session to update username.");
        }
    }

    /**
     * Returns the information about the session.
     *
     * @return string representation of the WorkoutSession
     */
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
