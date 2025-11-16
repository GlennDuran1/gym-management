/**
 * The GymSystem class manages members, trainers, and admin functionality
 * within the gym application. It provides methods to add and display 
 * both members and trainers, and show menu options to the user.
 */

public class GymSystem {

    //Attributes
    private Member[] members;
    private Trainer[] trainers;
    private Admin admin;
    private int memberCount;
    private int trainerCount;
    //Constructor
    /**
    *this a default constructor for the Gym system. 
    */
    public GymSystem() {
        
    }

    /**
    * This method should add a new member to the system method
    *@param m the member to be added
    */
    public void addMember(Member m) {

    }
    /**
     * Displays all registered members by looping 
     * through the members array and printing each one.
     */
    public void displayAllMembers() {
        
    }

    /**
     * Adds a new trainer to the system.
     * @param t the Trainer object to be added
     */
    public void addTrainer(Trainer t) {

    }
    /**
     * Displays all registered trainers by looping 
     * through the trainers array and printing each one.
     */
    public void displayAllTrainers() {
        
    }

    /**
     * Prints a list of available menu options 
     * for the user to choose from.
     */
    public void showMenu() {
        System.out.println("\n==== GYM SYSTEM MENU ====");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
        
    }

    public void showRegisterMenu(Scanner input){
        System.out.println("\nRegister Menu:");
        System.out.println("1. Register as Trainer");
        System.out.println("2. Register as Member");
        System.out.print("Choose an option: ");

        int choice = input.nextInt();
        switch(choice){
            case 1:
                registerTrainer(input);
                break;
            case 2:
                registerMember(input);
                break;
    }

    public void registerTrainer(Scanner input){
        
    }

    public void registerMember(Scanner input){
        
    }

}
