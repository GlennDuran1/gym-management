import java.util.List;
import java.util.Scanner;

/**
 * Utility class responsible for handling all management menus within the Gym
 * System.
 */

public class ManageMenus {

    /**
     * Displays and controls the admin management menu.
     * Allows the user to add, view, update, and delete admin accounts.
     *
     * @param system The main GymSystem instance used for accessing and modifying
     *               admin data.
     * @param input  Scanner used to read user input from the console.
     */
    public static void manageAdminsMenu(GymSystem system, Scanner input) {
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
                    Register.registerAdmin(system, input);
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
                        Updates.updateAdmin(system, input, toUpdate);
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

    /**
     * Displays and handles the trainer management menu.
     * Allows the user to add new trainers, view trainers, update trainer
     * information,
     * and delete trainer accounts.
     *
     * @param system The GymSystem instance used for trainer operations.
     * @param input  Scanner for reading user input.
     */
    public static void manageTrainersMenu(GymSystem system, Scanner input) {
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
                    Register.registerTrainer(system, input);
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
                        Updates.updateTrainer(system, input, toUpdate);
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

    /**
     * Displays and manages the member menu, enabling the user to add, view,
     * update, or delete member records.
     *
     * @param system The GymSystem instance used for accessing and modifying member
     *               data.
     * @param input  Scanner used for user input.
     */
    public static void manageMembersMenu(GymSystem system, Scanner input) {
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
                    Register.registerMember(system, input);
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
                        Updates.updateMember(system, input, toUpdate);
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

    /**
     * Displays and manages the workout session menu.
     * Allows the user to add new workout sessions, view existing ones,
     * update session details, or delete sessions.
     *
     * @param system The GymSystem instance used for session operations.
     * @param input  Scanner for reading user input.
     */
    public static void manageWorkoutSessionsMenu(GymSystem system, Scanner input) {
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
                    WorkoutSession.addWorkoutSession(system, input);
                    break;

                case 2:
                    WorkoutSession.viewWorkoutSessions(system, input);
                    break;

                case 3:
                    WorkoutSession.updateWorkoutSession(system, input);
                    break;

                case 4:
                    WorkoutSession.deleteWorkoutSession(system, input);
                    break;

                case 5:
                    back = true;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    /**
     * Displays and handles the membership plan management menu.
     * Allows the user to add, view, update, and delete membership plans.
     *
     * @param system The GymSystem instance used to process membership plan data.
     * @param input  Scanner used to capture user input.
     */
    public static void managePlansMenu(GymSystem system, Scanner input) {
        boolean back = false;

        while (!back) {
            System.out.println("\n=== MANAGE MEMBERSHIP PLANS ===");
            System.out.println("1. Add Plan");
            System.out.println("2. View Plans");
            System.out.println("3. Update Plan");
            System.out.println("4. Delete Plan");
            System.out.println("5. Back");
            System.out.print("Choose: ");

            int choice = input.nextInt();
            input.nextLine(); // consume newline

            switch (choice) {

                case 1: // ---------------------- ADD PLAN ----------------------
                    System.out.print("Enter plan name: ");
                    String name = input.nextLine();

                    if (system.planExists(name)) {
                        System.out.println("A plan with that name already exists.");
                        break;
                    }

                    System.out.print("Enter duration (months): ");
                    int months = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter price: ");
                    double price = input.nextDouble();
                    input.nextLine();

                    int id = system.getLastPlanId() + 1;

                    MembershipPlan plan = new MembershipPlan(id, name, months, price);
                    system.addPlan(plan);

                    Log.write("ADMIN CREATE PLAN: " + name);
                    System.out.println("Membership plan added.");
                    break;

                case 2: // ---------------------- VIEW PLANS ----------------------
                    system.displayAllPlans();
                    break;

                case 3: // ---------------------- UPDATE PLAN ----------------------
                    System.out.print("Enter the name of the plan to update: ");
                    String updateName = input.nextLine();

                    MembershipPlan toUpdate = system.searchPlan(updateName);
                    if (toUpdate == null) {
                        System.out.println("Plan not found.");
                        break;
                    }

                    System.out.print("Enter new duration in months (or -1 to skip): ");
                    int newMonths = input.nextInt();
                    input.nextLine();

                    System.out.print("Enter new price (or -1 to skip): ");
                    double newPrice = input.nextDouble();
                    input.nextLine();

                    if (newMonths > 0)
                        toUpdate.setDurationMonths(newMonths);
                    if (newPrice > 0)
                        toUpdate.setPrice(newPrice);

                    Log.write("ADMIN UPDATE PLAN: " + updateName);
                    System.out.println("Plan updated.");
                    break;

                case 4: // ---------------------- DELETE PLAN ----------------------
                    System.out.print("Enter the name of the plan to delete: ");
                    String deleteName = input.nextLine();

                    MembershipPlan toDelete = system.searchPlan(deleteName);
                    if (toDelete == null) {
                        System.out.println("Plan not found.");
                        break;
                    }

                    System.out.print("Are you sure you want to delete this plan? (y/n): ");
                    String confirm = input.nextLine();

                    if (confirm.equalsIgnoreCase("y")) {
                        system.deletePlan(toDelete);
                        Log.write("ADMIN DELETE PLAN: " + deleteName);
                        System.out.println("Plan deleted.");
                    } else {
                        System.out.println("Delete cancelled.");
                    }
                    break;

                case 5: // ---------------------- BACK ----------------------
                    back = true;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    /**
     * Handles membership plan assignment or upgrade for a specific member.
     * If the member already has a plan, available upgrades are shown.
     * If not, the user is allowed to select a new plan from the list.
     *
     * @param system The GymSystem instance used to retrieve and update plan
     *               information.
     * @param input  Scanner used to read user choices.
     * @param member The member whose membership plan is being managed.
     */
    public static void managePlanMember(GymSystem system, Scanner input, Member member) {

        System.out.println("\n=== MANAGE MEMBERSHIP PLAN ===");

        MembershipPlan currentPlan = system.searchPlan(member.getMembershipType());

        // -------------------------------
        // CASE 1: MEMBER ALREADY HAS A PLAN
        // -------------------------------
        if (currentPlan != null) {
            System.out.println("You are currently enrolled in: " + currentPlan.getPlanName());

            // Get only plans with higher price or higher duration
            List<MembershipPlan> upgradeOptions = system.getUpgradeOptions(currentPlan);

            if (upgradeOptions.isEmpty()) {
                System.out.println("No upgrades available.");
                return;
            }

            System.out.println("\nAvailable Upgrades:");
            for (int i = 0; i < upgradeOptions.size(); i++) {
                System.out.println((i + 1) + ". " + upgradeOptions.get(i));
            }
            System.out.println((upgradeOptions.size() + 1) + ". Back");

            System.out.print("\nChoose an option: ");
            int choice = input.nextInt();
            input.nextLine();

            if (choice >= 1 && choice <= upgradeOptions.size()) {
                MembershipPlan selected = upgradeOptions.get(choice - 1);

                member.setMembershipType(selected.getPlanName());
                system.saveMemberChanges(member); // write to CSV
                Log.write("MEMBER UPGRADED: " + member.getUserName() +
                        " -> " + selected.getPlanName());

                System.out.println("Membership upgraded successfully!");
            } else {
                System.out.println("Returning...");
            }
            return;
        }

        // -------------------------------
        // CASE 2: MEMBER HAS NO PLAN
        // -------------------------------
        System.out.println("You are currently NOT enrolled in a membership plan.");
        if (system.planCount() == 0) {
            System.out.println("No Plans Available");
        }
        system.displayPlanInfo();

        System.out.println("\nAvailable Plans:");
        for (int i = 0; i < system.planCount(); i++) {
            System.out.println((i + 1) + ". " + system.getPlans().get(i));
        }
        System.out.println((system.planCount() + 1) + ". Back");

        System.out.print("\nChoose a plan: ");
        int choice = input.nextInt();
        input.nextLine();

        if (choice >= 1 && choice <= system.planCount()) {
            MembershipPlan selected = system.getPlans().get(choice - 1);

            member.setMembershipType(selected.getPlanName());
            system.saveMemberChanges(member);
            Log.write("MEMBER NEW PLAN: " + member.getUserName() +
                    " -> " + selected.getPlanName());

            System.out.println("Membership added successfully!");
        } else {
            System.out.println("Returning...");
        }
    }
}
