import java.util.Scanner;

public class Menus {

    public static void memberMenu(GymSystem system, Scanner input, Member member) {
        while (true) {
            System.out.println("\n=== MEMBER MENU ===");
            System.out.println("1. Manage Plan (Part B)");
            System.out.println("2. Enroll in Session (Part B)");
            System.out.println("3. Sign Out");
            System.out.print("Choose: ");

            int choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {
                ManageMenus.managePlanMember(system, input, member);
                return;
            } else if (choice == 2) {
                System.out.println("yes");
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

}
