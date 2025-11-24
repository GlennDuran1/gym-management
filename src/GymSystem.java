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
        return sessions.get(plans.size() - 1).getSessionId();
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

    public List<MembershipPlan> getPlans() {
        return new ArrayList<>(plans);
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
    }

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
                    t.getName().equalsIgnoreCase(key) ||
                    t.getUserName().equalsIgnoreCase(key)) {
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
     * Deletes an admin.
     *
     * @param a admin to delete
     * @return true if deleted
     */
    public boolean deleteAdmin(Admin a) {
        return admins.remove(a);
    }

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
     * Deletes a workout session.
     *
     * @param s session to delete
     * @return true if removed
     */
    public boolean deleteSession(WorkoutSession s) {
        return sessions.remove(s);
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
     * Finds a member by username and password.
     */
    public Member findMemberByCredentials(String username, String password) {
        for (Member m : members) {
            if (m.checkCredentials(username, password))
                return m;
        }
        return null;
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

    /**
     * Displays the registration menu and routes the choice.
     *
     * @param input scanner input
     */
    public void showRegisterMenu(Scanner input) {
        System.out.println("\nRegister Menu:");
        System.out.println("1. Register as Trainer");
        System.out.println("2. Register as Member");
        System.out.print("Choose an option: ");

        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1 -> registerTrainer(input);
            case 2 -> registerMember(input);
        }
    }

    /**
     * Handles trainer registration.
     *
     * @param input user input
     */
    public void registerTrainer(Scanner input) {
        System.out.println("Please enter your name:");
        String name = input.nextLine();

        System.out.println("Please enter a username:");
        String username = input.nextLine();

        while (isUsernameTaken(username)) {
            System.out.println("Username already exists. Try a different one:");
            username = input.nextLine();
        }

        System.out.println("Please enter your specialty:");
        String specialty = input.nextLine();

        System.out.println("Please enter your password:");
        String password = input.nextLine();

        int id = getLastTrainerId() + 1;

        Trainer t = new Trainer(name, username, password, id, specialty);
        trainers.add(t);

        Log.write("Trainer " + username + " registered");
        System.out.println("Trainer registered correctly.");
    }

    /**
     * Handles member registration.
     *
     * @param input user input
     */
    public void registerMember(Scanner input) {
        System.out.println("Please enter your name:");
        String name = input.nextLine();

        System.out.println("Please enter a username:");
        String username = input.nextLine();

        while (isUsernameTaken(username)) {
            System.out.println("Username already exists. Try a different one:");
            username = input.nextLine();
        }

        System.out.println("Please enter your password:");
        String password = input.nextLine();

        System.out.println("Please enter your membership type:");
        String membershipType = input.nextLine();

        while (!planExists(membershipType)) {
            System.out.println("please enter a valid Membership Plan.");
            displayPlanInfo();
            membershipType = input.nextLine();
        }

        int id = getLastMemberId() + 1;

        Member m = new Member(name, username, password, id, membershipType);
        members.add(m);

        Log.write("Member " + username + " registered");
        System.out.println("Member registered correctly.");
    }

    /**
     * Handles admin registration.
     *
     * @param system reference to gym system
     * @param input  scanner input
     */
    public void registerAdmin(GymSystem system, Scanner input) {
        System.out.print("Enter name: ");
        String name = input.nextLine();

        System.out.print("Enter username: ");
        String username = input.nextLine();

        while (system.isUsernameTaken(username)) {
            System.out.println("Username already taken. Try another:");
            username = input.nextLine();
        }

        System.out.print("Enter password: ");
        String password = input.nextLine();

        int id = admins.size() + members.size() + trainers.size() + 1;

        Admin admin = new Admin(name, username, password, id, system);
        system.addAdmin(admin);

        Log.write("ADMIN REGISTER ADMIN: " + username);
        System.out.println("Admin registered successfully.");
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

    public boolean planExists(String planName) {
        for (MembershipPlan p : plans) {
            if (p.getPlanName().equalsIgnoreCase(planName)) {
                return true; // plan found
            }
        }
        return false; // not found
    }

    public void displayPlanInfo() {
        if (plans.isEmpty()) {
            System.out.println("No plans available.");
            return;
        }

        System.out.println("\n=== Available Membership Pla ===");
        for (MembershipPlan p : plans) {
            System.out.println("- " + p.getPlanName());
            System.out.println("- " + p.getDurationMonths());
            System.out.println("- " + p.getPrice());
        }
    }

    public void saveMemberChanges(Member member) {
        CSVHandler.saveMemberToFile(member, members);
    }

    public List<MembershipPlan> getUpgradeOptions(MembershipPlan current) {
        List<MembershipPlan> upgrades = new ArrayList<>();

        for (MembershipPlan p : plans) {
            if (p.getPrice() > current.getPrice()) {
                upgrades.add(p);
            }
        }
        return upgrades;
    }
}
