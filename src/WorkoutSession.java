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
    private String startTime;
    private String endTime;

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
    public WorkoutSession(int sessionID, String type, String date,
                      String startTime, String endTime,
                      int capacity, Trainer trainer) {

    this.sessionID = sessionID;
    this.type = type;
    this.date = date;
    this.startTime = startTime;
    this.endTime = endTime;
    this.capacity = capacity;
    this.trainer = trainer;
    this.enrolledMembers = new ArrayList<>();
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
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }

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
    public void setStartTime(String startTime) { this.startTime = startTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
/**
 * sets trainer
 * @param trainer
 */
    public void setTrainer(Trainer trainer) {
    this.trainer = trainer;
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
           ", Type: " + type +
           ", Date: " + date +
           ", Start: " + startTime +
           ", End: " + endTime +
           ", Capacity: " + capacity +
           ", Trainer: " + (trainer != null ? trainer.getName() : "None");
}


    /**
     * Deletes a workout session from the system.
     * The user can search for a session by ID, type, or date.
     * Once found, the method asks for confirmation before removing it.
     * All deletions are logged.
     *
     * @param system The GymSystem instance used to locate and remove the session.
     * @param input  Scanner used to read user input.
     */
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

    /**
     * Updates a workout session's details.
     * The user searches by ID, type, or date and is then presented with
     * options to update the session's date, time, or trainer.
     * Trainer updates include validation to ensure the trainer exists.
     *
     * @param system The GymSystem instance used to retrieve and modify the session.
     * @param input  Scanner for reading user selections and updated values.
     */
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
                    System.out.print("New start time: ");
                    s.setStartTime(input.nextLine());
                    System.out.print("New end time: ");
                    s.setEndTime(input.nextLine());
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
                    s.setTrainer(system.findTrainer(newTrainer));
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

    /**
     * Displays workout sessions based on the user's choice.
     * The user may either view all sessions or search for a specific session
     * using an ID, type, or date.
     *
     * @param system The GymSystem instance used to retrieve session data.
     * @param input  Scanner used to capture user input.
     */
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

    /**
     * Adds a new workout session to the system.
     * Prompts the user for session type, date, time, capacity,
     * and a trainer username. Trainer input is validated to ensure
     * the trainer exists before creating the session.
     *
     * @param system The GymSystem instance used to add the new session.
     * @param input  Scanner used to gather session details from the user.
     */
    public static void addWorkoutSession(GymSystem system, Scanner input) {
        System.out.println("\n=== ADD WORKOUT SESSION ===");
        int id = system.getLastSessionId() + 1;

        System.out.print("Enter session type: ");
        String type = input.nextLine();

        System.out.print("Enter session date (MM/DD/YY): ");
        String date = input.nextLine();

        System.out.print("Enter session start time (HH:MM): ");
        String start = input.nextLine();

        System.out.print("Enter session end time (HH:MM): ");
        String end = input.nextLine();


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

        WorkoutSession w = new WorkoutSession(id, type, date, start, end, capacity, trainer);


        system.addSession(w);

        Log.write("ADMIN ADD SESSION: " + type + " by " + trainer);

        System.out.println("Session added.");
    }

    /**
     * Checks whether the workout session has available capacity.
     *
     * @return true if the number of enrolled members is less than the capacity,
     *         false otherwise.
     */
    public boolean hasSpace() {
        return enrolledMembers.size() < capacity;
    }

    /**
     * Determines whether a given member is already enrolled in the session.
     *
     * @param member The Member to check for enrollment.
     * @return true if the member is already enrolled, false if not.
     */
    public boolean isEnrolled(Member member) {
        for (Member m : enrolledMembers) {
            if (m.getId() == member.getId()) {
                return true;
            }
        }
        return false;
    }

    

    /**
     * Returns the list of members currently enrolled in the session.
     *
     * @return A List of Member objects.
     */
    public List<Member> getEnrolledMembers() {
        return new ArrayList<>(enrolledMembers);
}

    /**
     * Returns the number of members enrolled in the workout session.
     *
     * @return The total count of enrolled members.
     */
    public int getEnrolledMembersCount() {
        return enrolledMembers.size();
    }

    /**
     * Enrolls a member in the session if space is available.
     *
     * @param m the member to enroll
     * @return true if enrollment succeeds
     */
    public boolean enrollMember(Member m) {
        if (isEnrolled(m)) return false;
        if (!hasSpace()) return false;

        enrolledMembers.add(m);
        return true;
    }

    /**
     * Removes a member from the session.
     *
     * @param m the member to remove
     * @return true if the member was enrolled and removed
     */
    public boolean removeMember(Member m) {
        return enrolledMembers.remove(m);
    }

}
