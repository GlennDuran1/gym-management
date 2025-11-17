import java.util.ArrayList;
import java.util.Scanner;

/**
 * The GymSystem class manages members, trainers, and admin functionality
 * within the gym application. It provides methods to add and display
 * both members and trainers, and show menu options to the user.
 */

public class GymSystem {
    //
    public int membersCount() {
        return members.size();
    }

    public int trainerCount() {
        return trainers.size();
    }

    public int adminCount() {
        return admins.size();
    }

    public int sessionCount() {
        return sessions.size();
    }

    public int planCount() {
        return plans.size();
    }

    // Attributes
    private ArrayList<Member> members;
    private ArrayList<Trainer> trainers;
    private ArrayList<Admin> admins;
    private ArrayList<WorkoutSession> sessions;
    private ArrayList<MembershipPlan> plans;

    // Constructor
    /**
     * this a default constructor for the Gym system.
     */
    public GymSystem() {
        members = new ArrayList<>();
        trainers = new ArrayList<>();
        admins = new ArrayList<>();
        sessions = new ArrayList<>();
        plans = new ArrayList<>();
    }

    /**
     * This method should add a new member to the system method
     * 
     * @param m the member to be added
     */
    public void addMember(Member m) {
        members.add(m);
        System.out.println("Member added");
    }

    /**
     * Displays all registered members by looping
     * through the members list and printing each one.
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

    /** Search for Member by ID / name / username */
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

    /** Delete Member */
    public boolean deleteMember(Member m) {
        return members.remove(m);
    }

    /**
     * Adds a new trainer to the system.
     * 
     * @param t the Trainer object to be added
     */
    public void addTrainer(Trainer t) {
        trainers.add(t);
        System.out.println("Trainer added");
    }

    /**
     * Displays all registered trainers by looping
     * through the trainers array and printing each one.
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

    public boolean deleteTrainer(Trainer t) {
        return trainers.remove(t);
    }

    public void addAdmin(Admin a) {
        admins.add(a);
        System.out.println("Admin added successfully.");
    }

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

    public boolean deleteAdmin(Admin a) {
        return admins.remove(a);
    }

    public void addSession(WorkoutSession s) {
        sessions.add(s);
        System.out.println("Session added successfully.");
    }

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

    public WorkoutSession searchSession(String key) {
        for (WorkoutSession s : sessions) {
            if (String.valueOf(s.getSessionID()).equals(key) ||
                    s.getSessionName().equalsIgnoreCase(key) ||
                    s.getDate().equalsIgnoreCase(key)) {

                return s;
            }
        }
        return null;
    }

    public boolean deleteSession(WorkoutSession s) {
        return sessions.remove(s);
    }

    // plan management

    public void addPlan(MembershipPlan p) {
        plans.add(p);
        System.out.println("Membership plan added.");
    }

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

    public MembershipPlan searchPlan(String name) {
        for (MembershipPlan p : plans) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    public boolean deletePlan(MembershipPlan p) {
        return plans.remove(p);
    }

    /**
     * Prints a list of available menu options
     * for the user to choose from.
     */
    public void showMenu() {
        System.out.println("\n==== GYM SYSTEM MENU ====");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");

    }

    public Member findMemberByCredentials(String username, String password) {
        for (Member m : members) {
            if (m.checkCredentials(username, password))
                return m;
        }
        return null;
    }

    public Trainer findTrainerByCredentials(String username, String password) {
        for (Trainer t : trainers) {
            if (t.checkCredentials(username, password))
                return t;
        }
        return null;
    }

    public Admin findAdminByCredentials(String username, String password) {
        for (Admin a : admins) {
            if (a.checkCredentials(username, password))
                return a;
        }
        return null;
    }

    // username check

    public boolean isUsernameTaken(String username) {

        for (Member m : members) {
            if (m.getUserName().equalsIgnoreCase(username)) {
                return true;
            }
        }

        for (Trainer t : trainers) {
            if (t.getUserName().equalsIgnoreCase(username)) {
                return true;
            }
        }

        for (Admin a : admins) {
            if (a.getUserName().equalsIgnoreCase(username)) {
                return true;
            }
        }

        return false;
    }

    // Register Logic
    public void showRegisterMenu(Scanner input) {
        System.out.println("\nRegister Menu:");
        System.out.println("1. Register as Trainer");
        System.out.println("2. Register as Member");
        System.out.print("Choose an option: ");

        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1:
                registerTrainer(input);
                break;
            case 2:
                registerMember(input);
                break;
        }
    }

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

        int id = admins.size() + members.size() + trainers.size() + 1;

        Trainer t = new Trainer(name, username, password, id, specialty);
        trainers.add(t);

        Log.write("Trainer " + username + " registered");
        System.out.println("Trainer registered correctly.");
    }

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

        int id = admins.size() + members.size() + trainers.size() + 1;

        Member m = new Member(name, username, password, id, membershipType);
        members.add(m);

        Log.write("Member " + username + " registered");
        System.out.println("Member registered correctly.");
    }

    public boolean usernameExist(String us) {
        for (Trainer t : trainers) {
            if (t.getUserName().equals(us)) {
                return true;
            }
        }

        for (Member m : members) {
            if (m.getUserName().equals(us)) {
                return true;
            }
        }

        return false;
    }

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

}
