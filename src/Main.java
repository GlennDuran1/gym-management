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
        try {
            CSVHandler.loadUsers("data/GymUsersData.csv", system);
            CSVHandler.loadSessions("data/GymSessions.csv", system);
            CSVHandler.loadPlans("data/GymPlans.csv", system);
            System.out.println("CSV Data Loaded Successfully.");
        } catch (Exception e) {
            System.out.println("Error loading CSV files: " + e.getMessage());
        }

        Scanner input = new Scanner(System.in);
        boolean running = true;
        while (running) {
            system.showMenu();
            int options = input.nextInt();
            input.nextLine();
            switch (options) {
                case 1:
                    system.showRegisterMenu(system, input);
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

    /**
     * Handles the login process for the gym management system.
     *
     * @param system the GymSystem instance used to verify user credentials and
     *               access user data
     * @param input  the Scanner used to read login input from the console
     */

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
            Menus.memberMenu(system, input, member);
            return;
        }

        // Try trainer
        Trainer trainer = system.findTrainerByCredentials(username, password);
        if (trainer != null) {
            System.out.println("\nWelcome, " + trainer.getName() + " (Trainer)");
            Log.write("LOGIN (Trainer): " + trainer.getUserName());
            Menus.trainerMenu(system, input, trainer);
            return;
        }

        // Try admin
        Admin admin = system.findAdminByCredentials(username, password);
        if (admin != null) {
            System.out.println("\nWelcome, " + admin.getName() + " (Admin)");
            Log.write("LOGIN (Admin): " + admin.getUserName());
            Menus.adminMenu(system, input, admin);
            return;
        }

        System.out.println("Invalid username or password.");
    }
}
