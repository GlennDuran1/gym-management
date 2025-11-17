import java.util.ArrayList;
import java.util.Scanner;

public class GymSystem {

    private ArrayList<Member> members;
    private ArrayList<Trainer> trainers;

    // Constructor
    public GymSystem() {
        members = new ArrayList<>();
        trainers = new ArrayList<>();
    }

    public void addMember(Member m) {
        members.add(m);
    }

    public void addTrainer(Trainer t) {
        trainers.add(t);
    }

    public void displayAllMembers() {
        for (Member m : members) {
            m.displayInfo();
            System.out.println("----------------");
            
        }
    }

    public void displayAllTrainers() {
        for (Trainer t : trainers) {
            t.displayInfo();
            System.out.println("----------------");
            
        }
    }

    public void showMenu() {
        System.out.println("\n==== GYM SYSTEM MENU ====");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
    }

    public void showRegisterMenu(Scanner input) {
        System.out.println("\nRegister Menu:");
        System.out.println("1. Register as Trainer");
        System.out.println("2. Register as Member");
        System.out.print("Choose an option: ");

        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1:
                registerTrainer(input);
                break;
            case 2:
                registerMember(input);
                break;
        }
    }

    public void registerTrainer(Scanner input) {
        System.out.println("Please enter your name:");
        String name = input.nextLine();

        System.out.println("Please enter a username:");
        String username = input.nextLine();

        while (usernameExist(username)) {
            System.out.println("Username already exists. Try a different one:");
            username = input.nextLine();
        }

        System.out.println("Please enter your specialty:");
        String specialty = input.nextLine();

        System.out.println("Please enter your password:");
        String password = input.nextLine();

        Trainer t = new Trainer(name, username, password, specialty);
        trainers.add(t);

        System.out.println("Trainer registered correctly.");
    }

    public void registerMember(Scanner input) {
        System.out.println("Please enter your name:");
        String name = input.nextLine();

        System.out.println("Please enter a username:");
        String username = input.nextLine();

        while (usernameExist(username)) {
            System.out.println("Username already exists. Try a different one:");
            username = input.nextLine();
        }

        System.out.println("Please enter your password:");
        String password = input.nextLine();

        Member m = new Member(name, username, password);
        members.add(m);

        System.out.println("Member registered correctly.");
    }

    public boolean usernameExist(String us) {
        for (Trainer t : trainers) {
            if (t.getUsername().equals(us)) {
                return true;
            }
        }

        for (Member m : members) {
            if (m.getUsername().equals(us)) {
                return true;
            }
        }

        return false;
    }
}
