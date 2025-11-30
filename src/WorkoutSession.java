import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    /** Members enroll in this session */
    private List<Member> enrolledMembers = new ArrayList<>();

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

    public static void deleteWorkoutSession(GymSystem system, Scanner input) {
        System.out.println("\n=== DELETE WORKOUT SESSION ===");

        System.out.print("Enter session ID, type, or date: ");
        String key = input.nextLine();

        WorkoutSession s = system.searchSession(key);

        if (s == null) {
            System.out.println("Session not found.");
            return;
        }

        System.out.print("Are you sure you want to delete this session? (y/n): ");
        String confirm = input.nextLine();

        if (confirm.equalsIgnoreCase("y")) {
            system.deleteSession(s);
            Log.write("ADMIN DELETE SESSION: " + s.getSessionId());
            System.out.println("Session deleted.");
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    public static void updateWorkoutSession(GymSystem system, Scanner input) {
        System.out.println("\n=== UPDATE WORKOUT SESSION ===");

        System.out.print("Enter session ID, type, or date to update: ");
        String key = input.nextLine();

        WorkoutSession s = system.searchSession(key);

        if (s == null) {
            System.out.println("Session not found.");
            return;
        }

        boolean done = false;
        while (!done) {
            System.out.println("\nUpdating Session: " + s.getSessionId());
            System.out.println("1. Change Date");
            System.out.println("2. Change Time");
            System.out.println("3. Change Trainer");
            System.out.println("4. Back");
            System.out.print("Choose: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("New date: ");
                    String newDate = input.nextLine();
                    s.setDate(newDate);
                    Log.write("ADMIN UPDATE SESSION DATE: " + s.getSessionId());
                    System.out.println("Date updated.");
                    break;

                case 2:
                    System.out.print("New time: ");
                    String newTime = input.nextLine();
                    s.setTime(newTime);
                    Log.write("ADMIN UPDATE SESSION TIME: " + s.getSessionId());
                    System.out.println("Time updated.");
                    break;

                case 3:
                    System.out.print("New trainer username: ");
                    String newTrainer = input.nextLine();
                    while (system.findTrainer(newTrainer) == null) {
                        System.out.println("Trainer not found. Try again:");
                        newTrainer = input.nextLine();
                    }
                    s.setTrainerUsername(newTrainer);
                    Log.write("ADMIN UPDATE SESSION TRAINER: " + s.getSessionId());
                    System.out.println("Trainer updated.");
                    break;

                case 4:
                    done = true;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void viewWorkoutSessions(GymSystem system, Scanner input) {
        System.out.println("\n=== VIEW WORKOUT SESSIONS ===");
        System.out.println("1. Display all sessions");
        System.out.println("2. Search by ID / Type / Date");
        System.out.print("Choose: ");

        int choice = input.nextInt();
        input.nextLine();

        if (choice == 1) {
            system.displayAllSessions();
        } else if (choice == 2) {
            System.out.print("Enter ID, type, or date: ");
            String key = input.nextLine();

            WorkoutSession found = system.searchSession(key);

            if (found != null) {
                System.out.println(found);
            } else {
                System.out.println("Session not found.");
            }
        } else {
            System.out.println("Invalid option.");
        }
    }

    public static void addWorkoutSession(GymSystem system, Scanner input) {
        System.out.println("\n=== ADD WORKOUT SESSION ===");
        int id = system.getLastPlanId() + 1;

        System.out.print("Enter session type: ");
        String type = input.nextLine();

        System.out.print("Enter session date (MM/DD/YY): ");
        String date = input.nextLine();

        System.out.print("Enter session time (HH:MM): ");
        String time = input.nextLine();

        System.out.print("Enter capacity: ");
        int capacity = input.nextInt();
        input.nextLine();

        System.out.print("Enter trainer username: ");
        String traineruser = input.nextLine();

        while (system.findTrainer(traineruser) == null) {
            System.out.println("Trainer not found. Enter a valid trainer username:");
            traineruser = input.nextLine();
        }
        Trainer trainer = system.findTrainer(traineruser);

        WorkoutSession w = new WorkoutSession(id, type, date, time, capacity, trainer);

        system.addSession(w);

        Log.write("ADMIN ADD SESSION: " + type + " by " + trainer);

        System.out.println("Session added.");
    }

    public boolean hasSpace() {
        return enrolledMembers.size() < capacity;
    }

    public boolean isEnrolled(Member member) {
        for (Member m : enrolledMembers) {
            if (m.getId() == member.getId()) {
                return true;
            }
        }
        return false;
    }

    public boolean enroll(Member member) {
        if (!hasSpace() || isEnrolled(member)) {
            return false;
        }
        enrolledMembers.add(member);
        return true;
    }

    public List<Member> getEnrolledMembers() {
        return enrolledMembers;
    }

    public int getEnrolledMembersCount() {
        return enrolledMembers.size();
    }
}
