import java.util.Scanner;

public class Updates {

    public static void updateMember(GymSystem system, Scanner input, Member member) {
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

    public static void updateTrainer(GymSystem system, Scanner input, Trainer trainer) {
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

    public static void updateAdmin(GymSystem system, Scanner input, Admin admin) {
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

}
