import java.util.Scanner;

public class Main {
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
