import java.util.Scanner;

/**
 * The Main class serves as the entry point for the Gym Management System.
 * It creates a GymSystem object and provides an interactive console-based
 * menu that allows the user register as
 * a member, trainer or an admin, and exiting the system.
 */

public class Main {
    /*
     * The main method begins execution of the program.
     * It initializes the GymSystem and Scanner, displays menu options.
     *
     * @param args command-line arguments (not used in this program)
     */
    public static void main(String[] args) {
        System.out.println("Working directory: " + System.getProperty("user.dir"));

        GymSystem system = new GymSystem();
        // Load CSV data
        CSVHandler.loadUsers("data/GymUsersData.csv", system);
        CSVHandler.loadSessions("data/GymSessions.csv", system);
        CSVHandler.loadPlans("data/GymPlans.csv", system);

        System.out.println("CSV Data Loaded Successfully.");

        Scanner input = new Scanner(System.in);
        boolean running = true;
        while (running) {
            system.showMenu();
            int options = input.nextInt();
            input.nextLine();
            switch (options) {
                case 1:
                    system.showRegisterMenu(input);
                    system.displayAllTrainers();
                    break;
                case 2:
                    login(system, input);

                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Pick a valid option (1-3)");
            }
        }
        input.close();
    }

    private static void registerMember(GymSystem system, Scanner input) {
        System.out.print("Enter name: ");
        String name = input.nextLine();
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();
        System.out.print("Enter membership type: ");
        String membershipType = input.nextLine();
        System.out.print("Enter ID: ");
        int id = input.nextInt();
        input.nextLine();

        Member m = new Member(name, username, password, id, membershipType);
        system.addMember(m);
    }

    private static void login(GymSystem system, Scanner input) {
        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        // Try member
        Member member = system.findMemberByCredentials(username, password);
        if (member != null) {
            System.out.println("\nWelcome, " + member.getName() + " (Member)");
            Log.write("LOGIN (Member): " + member.getUserName());
            memberMenu(system, input, member);
            return;
        }

        // Try trainer
        Trainer trainer = system.findTrainerByCredentials(username, password);
        if (trainer != null) {
            System.out.println("\nWelcome, " + trainer.getName() + " (Trainer)");
            Log.write("LOGIN (Trainer): " + trainer.getUserName());
            trainerMenu(system, input, trainer);
            return;
        }

        // Try admin
        Admin admin = system.findAdminByCredentials(username, password);
        if (admin != null) {
            System.out.println("\nWelcome, " + admin.getName() + " (Admin)");
            Log.write("LOGIN (Admin): " + admin.getUserName());
            adminMenu(system, input, admin);
            return;
        }

        System.out.println("Invalid username or password.");
    }

    private static void memberMenu(GymSystem system, Scanner input, Member member) {
        while (true) {
            System.out.println("\n=== MEMBER MENU ===");
            System.out.println("1. Manage Plan (Part B)");
            System.out.println("2. Enroll in Session (Part B)");
            System.out.println("3. Sign Out");
            System.out.print("Choose: ");

            int choice = input.nextInt();
            input.nextLine();

            if (choice == 3) {
                System.out.println("Signed out.");
                return;
            }

            System.out.println("Feature will be implemented in Part B.");
        }
    }

    private static void trainerMenu(GymSystem system, Scanner input, Trainer trainer) {
        while (true) {
            System.out.println("\n=== TRAINER MENU ===");
            System.out.println("1. View Sessions (Part B)");
            System.out.println("2. View Members (Part B)");
            System.out.println("3. Sign Out");
            System.out.print("Choose: ");

            int choice = input.nextInt();
            input.nextLine();

            if (choice == 3) {
                System.out.println("Signed out.");
                return;
            }

            System.out.println("Feature will be implemented in Part B.");
        }
    }

    private static void adminMenu(GymSystem system, Scanner input, Admin admin) {
        while (true) {
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1. Manage Members");
            System.out.println("2. Manage Trainers");
            System.out.println("3. Manage Admins");
            System.out.println("4. Manage Workout Sessions (Part B)");
            System.out.println("5. Sign Out");
            System.out.print("Choose: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    manageMembersMenu(system, input);
                    break;
                case 2:
                    manageTrainersMenu(system, input);

                    break;
                case 3:
                    manageAdminsMenu(system, input);
                    break;

                case 4:
                    manageWorkoutSessionsMenu(system, input);
                    break;
                case 5:
                    System.out.println("Signed out.");
                    Log.write("LOGOUT (Admin): " + admin.getUserName());
                    return;
                default:
                    System.out.println("Feature coming in Part B.");
            }
        }
    }

    private static void manageMembersMenu(GymSystem system, Scanner input) {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== MANAGE MEMBERS ===");
            System.out.println("1. Add Member");
            System.out.println("2. View Members");
            System.out.println("3. Update Member");
            System.out.println("4. Delete Member");
            System.out.println("5. Back");
            System.out.print("Choose: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    // reuse existing registration
                    registerMember(system, input);
                    break;

                case 2:
                    System.out.println("1. Display all members");
                    System.out.println("2. Search by ID / Name / Username");
                    System.out.print("Choose: ");
                    int viewChoice = input.nextInt();
                    input.nextLine();

                    if (viewChoice == 1) {
                        system.displayAllMembers();
                    } else if (viewChoice == 2) {
                        System.out.print("Enter ID, name, or username: ");
                        String key = input.nextLine();
                        Member found = system.searchMember(key);
                        if (found != null) {
                            System.out.println(found);
                        } else {
                            System.out.println("Member not found.");
                        }
                    } else {
                        System.out.println("Invalid option.");
                    }
                    break;

                case 3:
                    System.out.print("Enter member ID, name, or username to update: ");
                    String keyUpdate = input.nextLine();
                    Member toUpdate = system.searchMember(keyUpdate);
                    if (toUpdate == null) {
                        System.out.println("Member not found.");
                    } else {
                        updateMember(system, input, toUpdate);
                    }
                    break;

                case 4:
                    System.out.print("Enter member ID, name, or username to delete: ");
                    String keyDelete = input.nextLine();
                    Member toDelete = system.searchMember(keyDelete);
                    if (toDelete == null) {
                        System.out.println("Member not found.");
                    } else {
                        System.out.print("Are you sure you want to delete this member? (y/n): ");
                        String confirm = input.nextLine();
                        if (confirm.equalsIgnoreCase("y")) {
                            system.deleteMember(toDelete);
                            Log.write("ADMIN DELETE MEMBER: " + toDelete.getUserName());
                            System.out.println("Member deleted.");
                        } else {
                            System.out.println("Delete cancelled.");
                        }
                    }
                    break;

                case 5:
                    back = true;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void updateMember(GymSystem system, Scanner input, Member member) {
        boolean done = false;
        while (!done) {
            System.out.println("\nUpdating Member: " + member.getUserName());
            System.out.println("1. Change Name");
            System.out.println("2. Change Username");
            System.out.println("3. Change Password");
            System.out.println("4. Change Membership");
            System.out.println("5. Back");
            System.out.print("Choose: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter new name: ");
                    String newName = input.nextLine();
                    member.setName(newName);
                    Log.write("ADMIN UPDATE MEMBER NAME: " + member.getUserName());
                    System.out.println("Name updated.");
                    break;

                case 2:
                    System.out.print("Enter new username: ");
                    String newUser = input.nextLine();
                    if (system.isUsernameTaken(newUser)) {
                        System.out.println("Username already taken. Try another.");
                    } else {
                        member.setUserName(newUser);
                        Log.write("ADMIN UPDATE MEMBER USERNAME: " + newUser);
                        System.out.println("Username updated.");
                    }
                    break;

                case 3:
                    System.out.print("Enter new password: ");
                    String newPass = input.nextLine();
                    member.setPassword(newPass);
                    Log.write("ADMIN UPDATE MEMBER PASSWORD: " + member.getUserName());
                    System.out.println("Password updated.");
                    break;

                case 4:
                    System.out.print("Enter new membership plan name: ");
                    String newPlan = input.nextLine();
                    member.setMembershipType(newPlan);
                    Log.write("ADMIN UPDATE MEMBER MEMBERSHIP: " + member.getUserName() + " -> " + newPlan);
                    System.out.println("Membership updated.");
                    break;

                case 5:
                    done = true;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void manageTrainersMenu(GymSystem system, Scanner input) {
        boolean back = false;

        while (!back) {
            System.out.println("\n=== MANAGE TRAINERS ===");
            System.out.println("1. Add Trainer");
            System.out.println("2. View Trainers");
            System.out.println("3. Update Trainer");
            System.out.println("4. Delete Trainer");
            System.out.println("5. Back");
            System.out.print("Choose: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    // Same as registering a trainer
                    system.registerTrainer(input);
                    break;

                case 2:
                    System.out.println("1. Display all trainers");
                    System.out.println("2. Search by ID / Name / Username");
                    System.out.print("Choose: ");
                    int viewChoice = input.nextInt();
                    input.nextLine();

                    if (viewChoice == 1) {
                        system.displayAllTrainers();
                    } else if (viewChoice == 2) {
                        System.out.print("Enter ID, name, or username: ");
                        String key = input.nextLine();
                        Trainer found = system.searchTrainer(key);
                        if (found != null) {
                            System.out.println(found);
                        } else {
                            System.out.println("Trainer not found.");
                        }
                    } else {
                        System.out.println("Invalid option.");
                    }
                    break;

                case 3:
                    System.out.print("Enter trainer ID, name, or username to update: ");
                    String keyUpdate = input.nextLine();
                    Trainer toUpdate = system.searchTrainer(keyUpdate);

                    if (toUpdate == null) {
                        System.out.println("Trainer not found.");
                    } else {
                        updateTrainer(system, input, toUpdate);
                    }
                    break;

                case 4:
                    System.out.print("Enter trainer ID, name, or username to delete: ");
                    String keyDelete = input.nextLine();
                    Trainer toDelete = system.searchTrainer(keyDelete);

                    if (toDelete == null) {
                        System.out.println("Trainer not found.");
                    } else {
                        System.out.print("Are you sure you want to delete this trainer? (y/n): ");
                        String confirm = input.nextLine();
                        if (confirm.equalsIgnoreCase("y")) {
                            system.deleteTrainer(toDelete);
                            Log.write("ADMIN DELETE TRAINER: " + toDelete.getUserName());
                            System.out.println("Trainer deleted.");
                        } else {
                            System.out.println("Delete cancelled.");
                        }
                    }
                    break;

                case 5:
                    back = true;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void updateTrainer(GymSystem system, Scanner input, Trainer trainer) {
        boolean done = false;

        while (!done) {
            System.out.println("\nUpdating Trainer: " + trainer.getUserName());
            System.out.println("1. Change Name");
            System.out.println("2. Change Username");
            System.out.println("3. Change Password");
            System.out.println("4. Change Specialty");
            System.out.println("5. Back");
            System.out.print("Choose: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter new name: ");
                    String newName = input.nextLine();
                    trainer.setName(newName);
                    Log.write("ADMIN UPDATE TRAINER NAME: " + trainer.getUserName());
                    System.out.println("Name updated.");
                    break;

                case 2:
                    System.out.print("Enter new username: ");
                    String newUser = input.nextLine();

                    if (system.isUsernameTaken(newUser)) {
                        System.out.println("Username already taken. Try another.");
                    } else {
                        trainer.setUserName(newUser);
                        Log.write("ADMIN UPDATE TRAINER USERNAME: " + newUser);
                        System.out.println("Username updated.");
                    }
                    break;

                case 3:
                    System.out.print("Enter new password: ");
                    String newPass = input.nextLine();
                    trainer.setPassword(newPass);
                    Log.write("ADMIN UPDATE TRAINER PASSWORD: " + trainer.getUserName());
                    System.out.println("Password updated.");
                    break;

                case 4:
                    System.out.print("Enter new specialty: ");
                    String newSpec = input.nextLine();
                    trainer.setSpecialty(newSpec);
                    Log.write("ADMIN UPDATE TRAINER SPECIALTY: " + trainer.getUserName() + " - " + newSpec);
                    System.out.println("Specialty updated.");
                    break;

                case 5:
                    done = true;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void manageAdminsMenu(GymSystem system, Scanner input) {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== MANAGE ADMINS ===");
            System.out.println("1. Add Admin");
            System.out.println("2. View Admins");
            System.out.println("3. Update Admin");
            System.out.println("4. Delete Admin");
            System.out.println("5. Back");
            System.out.print("Choose: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    // Reuse existing admin registration
                    system.registerAdmin(system, input);
                    break;

                case 2:
                    System.out.println("1. Display all admins");
                    System.out.println("2. Search by ID / Name / Username");
                    System.out.print("Choose: ");
                    int viewChoice = input.nextInt();
                    input.nextLine();

                    if (viewChoice == 1) {
                        system.displayAllAdmins();
                    } else if (viewChoice == 2) {
                        System.out.print("Enter ID, name, or username: ");
                        String key = input.nextLine();
                        Admin found = system.searchAdmin(key);
                        if (found != null) {
                            System.out.println(found);
                        } else {
                            System.out.println("Admin not found.");
                        }
                    } else {
                        System.out.println("Invalid option.");
                    }
                    break;

                case 3:
                    System.out.print("Enter admin ID, name, or username to update: ");
                    String keyUpdate = input.nextLine();
                    Admin toUpdate = system.searchAdmin(keyUpdate);

                    if (toUpdate == null) {
                        System.out.println("Admin not found.");
                    } else {
                        updateAdmin(system, input, toUpdate);
                    }
                    break;

                case 4:
                    System.out.print("Enter admin ID, name, or username to delete: ");
                    String keyDelete = input.nextLine();
                    Admin toDelete = system.searchAdmin(keyDelete);

                    if (toDelete == null) {
                        System.out.println("Admin not found.");
                    } else {
                        System.out.print("Are you sure you want to delete this admin? (y/n): ");
                        String confirm = input.nextLine();

                        if (confirm.equalsIgnoreCase("y")) {
                            system.deleteAdmin(toDelete);
                            Log.write("ADMIN DELETE ADMIN: " + toDelete.getUserName());
                            System.out.println("Admin deleted.");
                        } else {
                            System.out.println("Delete cancelled.");
                        }
                    }
                    break;

                case 5:
                    back = true;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void updateAdmin(GymSystem system, Scanner input, Admin admin) {
        boolean done = false;
        while (!done) {
            System.out.println("\nUpdating Admin: " + admin.getUserName());
            System.out.println("1. Change Name");
            System.out.println("2. Change Username");
            System.out.println("3. Change Password");
            System.out.println("4. Back");
            System.out.print("Choose: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter new name: ");
                    String newName = input.nextLine();
                    admin.setName(newName);
                    Log.write("ADMIN UPDATE ADMIN NAME: " + admin.getUserName());
                    System.out.println("Name updated.");
                    break;

                case 2:
                    System.out.print("Enter new username: ");
                    String newUser = input.nextLine();
                    if (system.isUsernameTaken(newUser)) {
                        System.out.println("Username already taken. Try another.");
                    } else {
                        admin.setUserName(newUser);
                        Log.write("ADMIN UPDATE ADMIN USERNAME: " + newUser);
                        System.out.println("Username updated.");
                    }
                    break;

                case 3:
                    System.out.print("Enter new password: ");
                    String newPass = input.nextLine();
                    admin.setPassword(newPass);
                    Log.write("ADMIN UPDATE ADMIN PASSWORD: " + admin.getUserName());
                    System.out.println("Password updated.");
                    break;

                case 4:
                    done = true;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void manageWorkoutSessionsMenu(GymSystem system, Scanner input) {
        boolean back = false;

        while (!back) {
            System.out.println("\n=== MANAGE WORKOUT SESSIONS ===");
            System.out.println("1. Add Session");
            System.out.println("2. View Sessions");
            System.out.println("3. Update Session");
            System.out.println("4. Delete Session");
            System.out.println("5. Back");
            System.out.print("Choose: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    addWorkoutSession(system, input);
                    break;

                case 2:
                    viewWorkoutSessions(system, input);
                    break;

                case 3:
                    updateWorkoutSession(system, input);
                    break;

                case 4:
                    deleteWorkoutSession(system, input);
                    break;

                case 5:
                    back = true;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addWorkoutSession(GymSystem system, Scanner input) {
        System.out.println("\n=== ADD WORKOUT SESSION ===");
        System.out.print("Enter session id: ");
        String idStr = input.nextLine();
        int id = Integer.parseInt(idStr);

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

    private static void viewWorkoutSessions(GymSystem system, Scanner input) {
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

    private static void updateWorkoutSession(GymSystem system, Scanner input) {
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

    private static void deleteWorkoutSession(GymSystem system, Scanner input) {
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

}
