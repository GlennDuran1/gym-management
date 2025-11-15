import java.util.Scanner;

/**
 * The Main class serves as the entry point for the Gym Management System.
 * It creates a GymSystem object and provides an interactive console-based
 * menu that allows the user to perform administrative actions such as
 * adding members, adding trainers, displaying members, and exiting the system.
 */

public class Main {
    /**
     * The main method begins execution of the program.
     * It initializes the GymSystem and Scanner, displays menu options
     * in a loop, and processes user input using a switch statement.
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
                    // add member
                    break;
                case 2:
                    // add trainer
                    break;
                case 3:
                    // display members
                    break;
                case 4:
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
