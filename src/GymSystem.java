import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The GymSystem class manages all core entities within the gym application,
 * including Members, Trainers, Admins, Workout Sessions, and Membership Plans.
 * <p>
 * This class provides methods for:
 * <ul>
 * <li>Adding, searching, displaying, and deleting system users.</li>
 * <li>Managing workout sessions and membership plans.</li>
 * <li>Registering new users (Member, Trainer, Admin).</li>
 * <li>Credential checks and username validation.</li>
 * <li>Displaying main menu and registration menu options.</li>
 * </ul>
 * <p>
 * It acts as the central controller of the gym management system.
 */
public class GymSystem {

    /** List of all registered members in the system. */
    private ArrayList<Member> members;

    /** List of all registered trainers. */
    private ArrayList<Trainer> trainers;

    /** List of all admin users. */
    private ArrayList<Admin> admins;

    /** List of all workout sessions available. */
    private ArrayList<WorkoutSession> sessions;

    /** List of all membership plans offered by the gym. */
    private ArrayList<MembershipPlan> plans;

    private ArrayList<String[]> progress;


    // ---- Counters ----

    /**
     * @return total number of registered members
     */
    public int membersCount() {
        return members.size();
    }

    /**
     * @return total number of registered trainers
     */
    public int trainerCount() {
        return trainers.size();
    }

    /**
     * @return total number of admin users
     */
    public int adminCount() {
        return admins.size();
    }

    /**
     * @return total number of workout sessions
     */
    public int sessionCount() {
        return sessions.size();
    }

    /**
     * @return total number of membership plans
     */
    public int planCount() {
        return plans.size();
    }

    public int getLastPlanId() {
        if (plans.isEmpty()) {
            return 0;
        }
        return plans.get(plans.size() - 1).getId();
    }

    public int getLastSessionId() {
        if (sessions.isEmpty()) {
        return 0;
        }
        return sessions.get(sessions.size() - 1).getSessionId();
    }


    public int getLastMemberId() {
        if (members.isEmpty()) {
            return 0;
        }
        return members.get(members.size() - 1).getId();
    }

    public int getLastTrainerId() {
        if (trainers.isEmpty()) {
            return 0;
        }
        return trainers.get(trainers.size() - 1).getId();
    }

    public int getLastAdminId() {
        if (admins.isEmpty()) {
            return 0;
        }
        return admins.get(admins.size() - 1).getId();
    }

    public List<MembershipPlan> getPlans() {
        return new ArrayList<>(plans);
    }

    public List<WorkoutSession> getWorkoutSessions() {
        return sessions;
    }

    // ---- Constructor ----

    /**
     * Creates an empty GymSystem with no members, trainers, admins, sessions, or
     * plans.
     */
    public GymSystem() {
        members = new ArrayList<>();
        trainers = new ArrayList<>();
        admins = new ArrayList<>();
        sessions = new ArrayList<>();
        plans = new ArrayList<>();
        progress = new ArrayList<>();

    }
    // ---- MEMBERS METHODS ----

    /**
     * Adds a new member to the system.
     *
     * @param m member to be added
     */
    public void addMember(Member m) {
        members.add(m);
        System.out.println("Member added");
    }

    /**
     * Displays all registered members in the system.
     */
    public void displayAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members yet.");
            return;
        }
        System.out.println("\n=== Registered Members ===");
        for (Member m : members) {
            System.out.println(m);
        }
    }

    /**
     * Searches for a member by ID, name, or username.
     *
     * @param key search value
     * @return matching Member object, or null if not found
     */
    public Member searchMember(String key) {
        for (Member m : members) {
            if (String.valueOf(m.getId()).equals(key) ||
                    m.getName().equalsIgnoreCase(key) ||
                    m.getUserName().equalsIgnoreCase(key)) {
                return m;
            }
        }
        return null;
    }

    /**
     * Deletes a member from the system.
     *
     * @param m member to delete
     * @return true if removed successfully
     */
    public boolean deleteMember(Member m) {
        return members.remove(m);
    }

    /**
     * Finds a member by username and password.
     */
    public Member findMemberByCredentials(String username, String password) {
        for (Member m : members) {
            if (m.checkCredentials(username, password))
                return m;
        }
        return null;
    }

    public void saveMemberChanges(Member member) {
        CSVHandler.saveToFile(member, members);
    }

    // -------TRAINER METHODS--------//

    /**
     * Adds a new trainer to the system.
     *
     * @param t Trainer object to be added
     */
    public void addTrainer(Trainer t) {
        trainers.add(t);
        System.out.println("Trainer added");
    }

    /**
     * Displays all registered trainers.
     */
    public void displayAllTrainers() {
        if (trainers.isEmpty()) {
            System.out.println("No trainers registered.");
            return;
        }
        System.out.println("\n=== Registered Trainers ===");
        for (Trainer t : trainers) {
            System.out.println(t);
        }
    }

    /**
     * Searches for a trainer by ID, name, or username.
     *
     * @param key search keyword
     * @return Trainer if found, otherwise null
     */
    public Trainer searchTrainer(String key) {
        for (Trainer t : trainers) {
            if (String.valueOf(t.getId()).equals(key) ||
                    t.getUserName().equalsIgnoreCase(key) ||
                    t.getName().equalsIgnoreCase(key)) {
                return t;
            }
        }
        return null;
    }

    /**
     * Deletes a trainer from the system.
     *
     * @param t trainer to delete
     * @return true if removed
     */
    public boolean deleteTrainer(Trainer t) {
        return trainers.remove(t);
    }

    /**
     * Finds a trainer by username and password.
     */
    public Trainer findTrainerByCredentials(String username, String password) {
        for (Trainer t : trainers) {
            if (t.checkCredentials(username, password))
                return t;
        }
        return null;
    }

    /**
     * Searches for a trainer by username.
     *
     * @param username trainer username
     * @return trainer or null
     */
    public Trainer findTrainer(String username) {
        for (Trainer t : trainers) {
            if (t.getUserName().equalsIgnoreCase(username))
                return t;
        }
        return null;
    }

    /**
     * Searches for a trainer by ID.
     *
     * @param id trainer ID
     * @return trainer if found, else null
     */
    public Trainer findTrainerById(int id) {
        for (Trainer t : trainers) {
            if (t.getId() == id)
                return t;
        }
        return null;
    }

    // --------ADMIN METHODS-------//

    /**
     * Adds a new admin to the system.
     *
     * @param a Admin to add
     */
    public void addAdmin(Admin a) {
        admins.add(a);
        System.out.println("Admin added successfully.");
    }

    /**
     * Displays all admin users.
     */
    public void displayAllAdmins() {
        if (admins.isEmpty()) {
            System.out.println("No admins registered.");
            return;
        }
        System.out.println("\n=== Admin Users ===");
        for (Admin a : admins) {
            System.out.println(a);
        }
    }

    /**
     * Searches for an admin by ID, name, or username.
     *
     * @param key search keyword
     * @return Admin if found, else null
     */
    public Admin searchAdmin(String key) {
        for (Admin a : admins) {
            if (String.valueOf(a.getId()).equals(key) ||
                    a.getName().equalsIgnoreCase(key) ||
                    a.getUserName().equalsIgnoreCase(key)) {
                return a;
            }
        }
        return null;
    }

    /**
     * Finds an admin by username and password.
     */
    public Admin findAdminByCredentials(String username, String password) {
        for (Admin a : admins) {
            if (a.checkCredentials(username, password))
                return a;
        }
        return null;
    }

    /**
     * Deletes an admin.
     *
     * @param a admin to delete
     * @return true if deleted
     */
    public boolean deleteAdmin(Admin a) {
        return admins.remove(a);
    }

    // -------SESSIONS METHODS------//

    /**
     * Adds a workout session.
     *
     * @param s session to add
     */
    public void addSession(WorkoutSession s) {
        sessions.add(s);
        System.out.println("Session added successfully.");
    }

    /**
     * Displays all workout sessions.
     */
    public void displayAllSessions() {
        if (sessions.isEmpty()) {
            System.out.println("No sessions available.");
            return;
        }

        System.out.println("\n=== Workout Sessions ===");
        for (WorkoutSession s : sessions) {
            System.out.println(s);
        }
    }

    /**
     * Searches for a workout session by ID, type, or date.
     *
     * @param key search keyword
     * @return matching WorkoutSession or null
     */
    public WorkoutSession searchSession(String key) {
        for (WorkoutSession s : sessions) {
            if (String.valueOf(s.getSessionId()).equals(key) ||
                    s.getType().equalsIgnoreCase(key) ||
                    s.getDate().equalsIgnoreCase(key)) {
                return s;
            }
        }
        return null;
    }
    /**
     * returns workoutSessions assigned to a trainer
     * @param trainerId id of trainer
     * @return list of workoutSessions performed by the trainer
     */
    public List<WorkoutSession> getSessionsForTrainer(int trainerId) {
    List<WorkoutSession> list = new ArrayList<>();
    for (WorkoutSession s : sessions) {
        if (s.getTrainer().getId() == trainerId) {
            list.add(s);
        }
    }
    return list;
    }
    // ==== PART B: TRAINER VIEW METHODS ====

    /**
     * Gets all sessions taught by a trainer using username.
     *
     * @param username trainer username
     * @return list of sessions taught by the trainer
     */
    public List<WorkoutSession> getSessionsByTrainer(String username) {
        List<WorkoutSession> list = new ArrayList<>();
        for (WorkoutSession s : sessions) {
            if (s.getTrainer().getUserName().equalsIgnoreCase(username)) {
                list.add(s);
            }
        }
        return list;
    }

    /**
     * Returns all members enrolled in a given session.
     *
     * @param sessionId the id of the session
     * @return list of enrolled members
     */
    public List<Member> getMembersInSession(int sessionId) {
        WorkoutSession session = searchSession(String.valueOf(sessionId));
        if (session == null) return new ArrayList<>();
        return session.getEnrolledMembers();
    }
    

    /**
     * Enrolls a member into a session if space exists.
     *
     * @param member the member to enroll
     * @param sessionId the session ID
     * @return true if enrollment succeeded
     */
    public boolean enrollMemberInSession(Member member, int sessionId) {
        WorkoutSession session = searchSession(String.valueOf(sessionId));
        if (session == null) return false;

        if (!session.hasSpace()) return false;

        if (session.isEnrolled(member)) return false;

        boolean enrolled = session.enrollMember(member);
        if (enrolled) {
            addProgressRecord(member.getId(), sessionId);
        }

        return enrolled;
    }

    /**
     * Removes a member from a session.
     *
     * @param member the member
     * @param sessionId the id of the session
     * @return true if unenrollment succeeded
     */
    public boolean unenrollMemberFromSession(Member member, int sessionId) {
        WorkoutSession session = searchSession(String.valueOf(sessionId));
        if (session == null) return false;

        return session.removeMember(member);
    }



    /**
     * Deletes a workout session.
     *
     * @param s session to delete
     * @return true if removed
     */
    public boolean deleteSession(WorkoutSession s) {
        return sessions.remove(s);
    }
    /** 
     * Returns sessions that the member can currently enroll in
     * 
     * @param member the member to check availability for
     * @return a list of sessions
     */
    public List<WorkoutSession> getAvailableSessionsForMember(Member member) {
    List<WorkoutSession> list = new ArrayList<>();
    for (WorkoutSession s : sessions) {
        if (s.hasSpace() && !s.isEnrolled(member)) {
            list.add(s);
        }
    }
    return list;
}


    // ---------MEMBERSHIP PLANS METHODS-----------//
       // ==== PART B: ADMIN SESSION MANAGEMENT ====

    /**
     * Updates a session by replacing it with a new session object.
     *
     * @param updated updated session info
     * @return true if updated
     */
    public boolean updateSession(WorkoutSession updated) {
        for (int i = 0; i < sessions.size(); i++) {
            if (sessions.get(i).getSessionId() == updated.getSessionId()) {
                sessions.set(i, updated);
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes a session by ID.
     *
     * @param sessionId the id of the session
     * @return true if deleted
     */
    public boolean deleteSession(int sessionId) {
        for (WorkoutSession s : sessions) {
            if (s.getSessionId() == sessionId) {
                sessions.remove(s);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a copy of all workout sessions.
     */
    public List<WorkoutSession> getAllSessions() {
        return new ArrayList<>(sessions);
    }

    /**
     * Adds a membership plan to the system.
     *
     * @param p plan to add
     */
    public void addPlan(MembershipPlan p) {
        plans.add(p);
        System.out.println("Membership plan added.");
    }

    /**
     * Displays all membership plans.
     */
    public void displayAllPlans() {
        if (plans.isEmpty()) {
            System.out.println("No plans available.");
            return;
        }

        System.out.println("\n=== Membership Plans ===");
        for (MembershipPlan p : plans) {
            System.out.println(p);
        }
    }

    /**
     * Searches for a membership plan by name.
     *
     * @param name plan name
     * @return matching plan or null
     */
    public MembershipPlan searchPlan(String name) {
        for (MembershipPlan p : plans) {
            if (p.getPlanName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Deletes a membership plan.
     *
     * @param p plan to delete
     * @return true if removed
     */
    public boolean deletePlan(MembershipPlan p) {
        return plans.remove(p);
    }

    public boolean planExists(String planName) {
        return searchPlan(planName) != null;
    }

    public void displayPlanInfo() {
        if (plans.isEmpty()) {
            System.out.println("No plans available.");
            return;
        }

        System.out.println("\n=== Available Membership Pla ===");
        for (MembershipPlan p : plans) {
            System.out.println(p);
        }
    }
    /**
     * Adds enrollment record for a member into a workoutSession into progress file
     * @param memberId the ID of the member
     * @param sessionId the ID of the session
     */
    public void addProgressRecord(int memberId, int sessionId) {
    progress.add(new String[] {
        String.valueOf(memberId),
        String.valueOf(sessionId)
    });
    CSVHandler.saveProgress(progress);
}
    /**
    * Returns members enrolled in a workout session.
    * This method now simply delegates to getMembersInSession()
    * for consistent behavior across the system.
    */
    public List<Member> getMembersEnrolledInSession(int sessionId) {
        return getMembersInSession(sessionId);
    }



    public List<MembershipPlan> getUpgradeOptions(MembershipPlan current) {
    List<MembershipPlan> upgrades = new ArrayList<>();

    for (MembershipPlan p : plans) {
        if (p == null) continue;

        boolean higherPrice = p.getPrice() > current.getPrice();
        boolean longerDuration = p.getDurationMonths() > current.getDurationMonths();

        if (higherPrice || longerDuration) {
            upgrades.add(p);
        }
    }
    return upgrades;
}


    /**
     * Displays the main menu options for the gym system.
     */
    public void showMenu() {
        System.out.println("\n==== GYM SYSTEM MENU ====");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
    }

    /**
     * Checks if a username already exists among Members, Trainers, or Admins.
     *
     * @param username username to check
     * @return true if taken
     */
    public boolean isUsernameTaken(String username) {
        for (Member m : members)
            if (m.getUserName().equalsIgnoreCase(username))
                return true;

        for (Trainer t : trainers)
            if (t.getUserName().equalsIgnoreCase(username))
                return true;

        for (Admin a : admins)
            if (a.getUserName().equalsIgnoreCase(username))
                return true;

        return false;
    }

    // /**
    // * Displays the registration menu and routes the choice.
    // *
    // * @param input scanner input
    // */
    public void showRegisterMenu(GymSystem system, Scanner input) {
        System.out.println("\nRegister Menu:");
        System.out.println("1. Register as Trainer");
        System.out.println("2. Register as Member");
        System.out.print("Choose an option: ");

        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1 -> Register.registerTrainer(system, input);
            case 2 -> Register.registerMember(system, input);
        }
    }
    /**
    * Returns all stored progress records (memberID → sessionID).
    *
    * @return list of progress entries
    */
    public List<String[]> getProgressRecords() {
     return progress;
    }

}
