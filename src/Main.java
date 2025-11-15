import java.util.Scanner;

/**
 * The Main class serves as the entry point for the Gym Management System.
 * It creates a GymSystem object and provides an interactive console-based
 * menu that allows the user register as
 * a member, trainer or an admin, and exiting the system.
 */

public class Main {
    /**
     * The main method begins execution of the program.
     * It initializes the GymSystem and Scanner, displays menu options.
     *
     * @param args command-line arguments (not used in this program)
     */
    public static void main(String[] args) {
        GymSystem system = new GymSystem();
        Scanner input = new Scanner(System.in);
        boolean running = true;
        while (running) {
            system.showMenu();
            int options = input.nextInt();
            switch (options) {
                case 1:
                    // register
                    break;
                case 2:
                    // login
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Pick a valid option (1-4)");
            }
        }
        input.close();
    }
}
