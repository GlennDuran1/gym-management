import java.util.Scanner;

/**
 * Utility class responsible for handling user registration processes within
 * the gym management system.
 * <p>
 * This class provides static methods to register new Members, Trainers, and
 * Admins. Each method collects input from the user, validates usernames to
 * prevent duplicates, creates the appropriate user object, adds it to the
 * GymSystem, and logs the registration event.
 * </p>
 *
 * <p>
 * The class is not intended to be instantiated.
 * </p>
 */

public class Register {
    /**
     * Handles trainer registration.
     *
     * @param input user input
     */
    public static void registerTrainer(GymSystem system, Scanner input) {
        System.out.println("Please enter your name:");
        String name = input.nextLine();

        System.out.println("Please enter a username:");
        String username = input.nextLine();

        while (system.isUsernameTaken(username)) {
            System.out.println("Username already exists. Try a different one:");
            username = input.nextLine();
        }

        System.out.println("Please enter your specialty:");
        String specialty = input.nextLine();

        System.out.println("Please enter your password:");
        String password = input.nextLine();

        int id = system.getLastTrainerId() + 1;

        Trainer t = new Trainer(name, username, password, id, specialty);
        system.addTrainer(t);

        Log.write("Trainer " + username + " registered");
        System.out.println("Trainer registered correctly.");
    }

    /**
     * Handles member registration.
     *
     * @param input user input
     */
    public static void registerMember(GymSystem system, Scanner input) {
        System.out.println("Please enter your name:");
        String name = input.nextLine();

        System.out.println("Please enter a username:");
        String username = input.nextLine();

        while (system.isUsernameTaken(username)) {
            System.out.println("Username already exists. Try a different one:");
            username = input.nextLine();
        }

        System.out.println("Please enter your password:");
        String password = input.nextLine();

        System.out.println("Please enter your membership type:");
        String membershipType = input.nextLine();

        while (!system.planExists(membershipType)) {
            System.out.println("please enter a valid Membership Plan.");
            system.displayPlanInfo();
            membershipType = input.nextLine();
        }

        int id = system.getLastMemberId() + 1;

        Member m = new Member(name, username, password, id, membershipType);
        system.addMember(m);

        Log.write("Member " + username + " registered");
        System.out.println("Member registered correctly.");
    }

    /**
     * Handles admin registration.
     *
     * @param system reference to gym system
     * @param input  scanner input
     */
    public static void registerAdmin(GymSystem system, Scanner input) {
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

        int id = system.getLastAdminId() + 1;

        Admin admin = new Admin(name, username, password, id, system);
        system.addAdmin(admin);

        Log.write("ADMIN REGISTER ADMIN: " + username);
        System.out.println("Admin registered successfully.");
    }

}
