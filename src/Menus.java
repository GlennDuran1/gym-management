import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menus {

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

    public static void trainerMenu(GymSystem system, Scanner input, Trainer trainer) {
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

    public static void enrollInSessionMenu(GymSystem system, Member member, Scanner input) {

        // Step 1: Get valid sessions
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

        // Step 2: Show sessions
        System.out.println("Available Sessions:");
        for (int i = 0; i < available.size(); i++) {
            WorkoutSession s = available.get(i);
            System.out.println((i + 1) + ") " +
                    s.getType() + " (ID:" + s.getSessionId() +
                    ") - Spots left: " + (s.getCapacity() - s.getEnrolledMembersCount()));
        }
        System.out.println("Enter number to enroll or 0 to cancel: ");
        int choice = input.nextInt();

        if (choice == 0)
            return;

        if (choice < 1 || choice > available.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        WorkoutSession selected = available.get(choice - 1);

        // Step 3: Enroll
        selected.enroll(member);

        // Step 4: Save to CSV
        // addProgressRecord(member.getId(), selected.getSessionId());

        System.out.println("Enrolled successfully in " + selected.getType());
    }

}
