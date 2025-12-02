import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Provides all top-level menu interfaces for system users, including Members,
 * Trainers, and Admins. Each menu handles user interaction, validates input,
 * and delegates specific operations to the appropriate management handlers.
 */

public class Menus {

    /**
     * Displays the menu for a logged-in member and handles their menu selection.
     *
     * @param system The GymSystem instance used to access system data.
     * @param input  Scanner used to read user input from the console.
     * @param member The currently logged-in Member.
     */
    public static void memberMenu(GymSystem system, Scanner input, Member member) {
        while (true) {
            System.out.println("\n=== MEMBER MENU ===");
            System.out.println("1. Manage Plan");
            System.out.println("2. Enroll in Session");
            System.out.println("3. Sign Out");
            System.out.print("Choose: ");

            int choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {
                ManageMenus.managePlanMember(system, input, member);
                return;
            } else if (choice == 2) {
                enrollInSessionMenu(system, member, input);
            } else if (choice == 3) {
                System.out.println("Signed out.");
                return;
            }

        }
    }

    /**
     * Displays the menu for a logged-in trainer and handles their menu selection.
     *
     * @param system  The GymSystem instance used to access system data.
     * @param input   Scanner used to read user input.
     * @param trainer The currently logged-in Trainer.
     */
    public static void trainerMenu(GymSystem system, Scanner input, Trainer trainer) {
    while (true) {
        System.out.println("\n=== TRAINER MENU ===");
        System.out.println("1. View Sessions");
        System.out.println("2. View Members in a Session");
        System.out.println("3. Sign Out");
        System.out.print("Choose: ");

        int choice = input.nextInt();
        input.nextLine();

        // Sign out
        if (choice == 3) {
            System.out.println("Signed out.");
            return;
        }

        // OPTION 1 — View sessions assigned to this trainer
        if (choice == 1) {
            List<WorkoutSession> sessions = system.getSessionsForTrainer(trainer.getId());

            if (sessions.isEmpty()) {
                System.out.println("You are not assigned to any sessions.");
            } else {
                System.out.println("\n=== YOUR SESSIONS ===");
                for (WorkoutSession ws : sessions) {
                    System.out.println(ws);
                }
            }
        }

        // OPTION 2 — View members enrolled in a specific session
        else if (choice == 2) {
            System.out.print("Enter session ID: ");
            int sessionId = input.nextInt();
            input.nextLine();

            List<Member> members = system.getMembersEnrolledInSession(sessionId);

            if (members.isEmpty()) {
                System.out.println("No members are enrolled in this session.");
            } else {
                System.out.println("\n=== MEMBERS IN SESSION " + sessionId + " ===");
                for (Member m : members) {
                    System.out.println(m);
                }
            }
        }
    }
}


    /**
     * Displays the administrative menu and processes the admin's selected action.
     *
     * Logs the admin's logout action.
     *
     * @param system The GymSystem instance providing access to all system data.
     * @param input  Scanner used for reading user selections.
     * @param admin  The currently logged-in Admin.
     */
    public static void adminMenu(GymSystem system, Scanner input, Admin admin) {
        while (true) {
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1. Manage Members");
            System.out.println("2. Manage Trainers");
            System.out.println("3. Manage Admins");
            System.out.println("4. Manage Workout Sessions");
            System.out.println("5. Manage Membership Plans");
            System.out.println("6. Sign Out");
            System.out.print("Choose: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    ManageMenus.manageMembersMenu(system, input);
                    break;
                case 2:
                    ManageMenus.manageTrainersMenu(system, input);

                    break;
                case 3:
                    ManageMenus.manageAdminsMenu(system, input);
                    break;

                case 4:
                    ManageMenus.manageWorkoutSessionsMenu(system, input);
                    break;

                case 5:
                    ManageMenus.managePlansMenu(system, input);
                    break;
                case 6:
                    System.out.println("Signed out.");
                    Log.write("LOGOUT (Admin): " + admin.getUserName());
                    return;
                default:
                    System.out.println("Feature coming in Part B.");
            }
        }
    }

    /**
 * Allows a member to view and enroll in available workout sessions.
 *
 * @param system The GymSystem instance used to access workout sessions.
 * @param member The member attempting to enroll.
 * @param input  Scanner used to read menu input.
 */
    public static void enrollInSessionMenu(GymSystem system, Member member, Scanner input) {

        List<WorkoutSession> available = new ArrayList<>();

        for (WorkoutSession s : system.getWorkoutSessions()) {
            if (s.hasSpace() && !s.isEnrolled(member)) {
            available.add(s);
        }
    }

    if (available.isEmpty()) {
        System.out.println("No sessions currently available.");
        return;
    }

    System.out.println("Available Sessions:");
    for (int i = 0; i < available.size(); i++) {
        WorkoutSession s = available.get(i);
        System.out.println((i + 1) + ") " +
                s.getType() + " (ID:" + s.getSessionId() +
                ") - Spots left: " + (s.getCapacity() - s.getEnrolledMembersCount()));
    }

    System.out.println("Enter number to enroll or 0 to cancel: ");
    int choice = input.nextInt();

    if (choice == 0) return;

    if (choice < 1 || choice > available.size()) {
        System.out.println("Invalid choice.");
        return;
    }

    WorkoutSession selected = available.get(choice - 1);

    boolean success = system.enrollMemberInSession(member, selected.getSessionId());

    if (success) {
        System.out.println("Enrolled successfully in " + selected.getType());
    } else {
        System.out.println("Enrollment failed.");
    }
}



}
