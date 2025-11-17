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
        CSVHandler.loadUsers("../data/GymUsersData.csv", system);
        CSVHandler.loadSessions("../data/GymSessions.csv", system);
        CSVHandler.loadPlans("../data/GymPlans.csv", system);


        System.out.println("CSV Data Loaded Successfully.");




        Scanner input = new Scanner(System.in);
        boolean running = true;
        while (running) {
            system.showMenu();
            int options = input.nextInt();
            input.nextLine();
            switch (options) {
                case 1:
                    // register
                    System.out.println("\nRegister as:");
                    System.out.println("1. Member");
                    System.out.println("2. Trainer");
                    System.out.println("3. Admin");
                    System.out.print("Choose: ");
                    int regChoice = input.nextInt();
                    input.nextLine();

                    switch (regChoice) {
                        case 1:
                            registerMember(system, input);
                            break;
                        case 2:
                            registerTrainer(system, input);
                            break;
                        case 3:
                            registerAdmin(system, input);
                            break;
                        default:
                            System.out.println("Invalid registration type.");
                    }
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

    private static void registerTrainer(GymSystem system, Scanner input) {
        System.out.print("Enter name: ");
        String name = input.nextLine();
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();
        System.out.print("Enter specialty: ");
        String specialty = input.nextLine();
        System.out.print("Enter ID: ");
        int id = input.nextInt();
        input.nextLine();

        Trainer t = new Trainer(name, username, password, id, specialty);
        system.addTrainer(t);
    }

    private static void registerAdmin(GymSystem system, Scanner input) {
        System.out.print("Enter name: ");
        String name = input.nextLine();
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();
        System.out.print("Enter ID: ");
        int id = input.nextInt();
        input.nextLine();

        Admin a = new Admin(name, username, password, id, system);
        system.addAdmin(a);
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
        System.out.println("2. Manage Trainers (Part B)");
        System.out.println("3. Manage Admins (Part B)");
        System.out.println("4. Manage Workout Sessions (Part B)");
        System.out.println("5. Sign Out");
        System.out.print("Choose: ");

        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1:
                manageMembersMenu(system, input);
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
private static void handleViewMembers(GymSystem system, Scanner input) {
    System.out.println("\n=== VIEW MEMBERS ===");
    system.displayAllMembers();
}

private static void handleUpdateMember(GymSystem system, Scanner input) {
    System.out.print("Enter ID, name, or username to update: ");
    String key = input.nextLine();

    Member m = system.searchMember(key);
    if (m == null) {
        System.out.println("Member not found.");
        return;
    }

    updateMember(system, input, m);
}

private static void handleDeleteMember(GymSystem system, Scanner input) {
    System.out.print("Enter ID, name, or username to delete: ");
    String key = input.nextLine();

    Member m = system.searchMember(key);
    if (m == null) {
        System.out.println("Member not found.");
        return;
    }

    System.out.print("Are you sure? (y/n): ");
    if (input.nextLine().equalsIgnoreCase("y")) {
        system.deleteMember(m);
        System.out.println("Member deleted.");
    }
}
private static void handleViewTrainers(GymSystem system, Scanner input) {
    System.out.println("\n=== VIEW TRAINERS ===");
    system.displayAllTrainers();
}

private static void handleUpdateTrainer(GymSystem system, Scanner input) {
    System.out.print("Enter ID, name, or username to update: ");
    String key = input.nextLine();

    Trainer t = system.searchTrainer(key);
    if (t == null) {
        System.out.println("Trainer not found.");
        return;
    }

    System.out.println("\nUpdate Trainer:");
    System.out.println("1. Change Name");
    System.out.println("2. Change Username");
    System.out.println("3. Change Password");
    System.out.println("4. Change Specialty");
    System.out.print("Choose: ");

    int choice = input.nextInt();
    input.nextLine();

    switch (choice) {
        case 1:
            System.out.print("New name: ");
            t.setName(input.nextLine());
            break;

        case 2:
            System.out.print("New username: ");
            t.setUserName(input.nextLine());
            break;

        case 3:
            System.out.print("New password: ");
            t.setPassword(input.nextLine());
            break;

        case 4:
            System.out.print("New specialty: ");
            t.setSpecialty(input.nextLine());
            break;

        default:
            System.out.println("Invalid option.");
    }
}

private static void handleDeleteTrainer(GymSystem system, Scanner input) {
    System.out.print("Enter ID, name, or username to delete: ");
    String key = input.nextLine();

    Trainer t = system.searchTrainer(key);
    if (t == null) {
        System.out.println("Trainer not found.");
        return;
    }

    System.out.print("Delete trainer? (y/n): ");
    if (input.nextLine().equalsIgnoreCase("y")) {
        system.deleteTrainer(t);
        System.out.println("Trainer deleted.");
    }
}
private static void handleViewAdmins(GymSystem system, Scanner input) {
    System.out.println("\n=== VIEW ADMINS ===");
    system.displayAllAdmins();
}

private static void handleUpdateAdmin(GymSystem system, Scanner input) {
    System.out.print("Enter ID, name, or username to update: ");
    String key = input.nextLine();

    Admin a = system.searchAdmin(key);
    if (a == null) {
        System.out.println("Admin not found.");
        return;
    }

    System.out.println("\nUpdate Admin:");
    System.out.println("1. Change Name");
    System.out.println("2. Change Username");
    System.out.println("3. Change Password");
    System.out.print("Choose: ");

    int choice = input.nextInt();
    input.nextLine();

    switch (choice) {
        case 1:
            System.out.print("New name: ");
            a.setName(input.nextLine());
            break;

        case 2:
            System.out.print("New username: ");
            a.setUserName(input.nextLine());
            break;

        case 3:
            System.out.print("New password: ");
            a.setPassword(input.nextLine());
            break;

        default:
            System.out.println("Invalid option.");
    }
}

private static void handleDeleteAdmin(GymSystem system, Scanner input) {
    System.out.print("Enter ID, name, or username to delete: ");
    String key = input.nextLine();

    Admin a = system.searchAdmin(key);
    if (a == null) {
        System.out.println("Admin not found.");
        return;
    }

    System.out.print("Delete admin? (y/n): ");
    if (input.nextLine().equalsIgnoreCase("y")) {
        system.deleteAdmin(a);
        System.out.println("Admin deleted.");
    }
}


}