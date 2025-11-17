/**
 * The GymSystem class manages members, trainers, and admin functionality
 * within the gym application. It provides methods to add and display 
 * both members and trainers, and show menu options to the user.
 */
import java.util.ArrayList;
public class GymSystem {

    //Attributes
    private ArrayList<Member> members;
    private ArrayList<Trainer> trainers;
    private Admin admin;
    private int memberCount;
    private int trainerCount;
    //Constructor
    /**
    *this a default constructor for the Gym system. 
    */
    public GymSystem() {
        members = new ArrayList<>();
        trainers = new ArrayList<>();
        
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
        input.nextLine();
        switch(choice){
            case 1:
                registerTrainer(input);
                break;
            case 2:
                registerMember(input);
                break;
    }

    public void registerTrainer(Scanner input){
        System.out.println("Please enter your name:");
        Srting name = input.nextLine();

        Sysem.out.println("Please enter a username:");
        String username = input.nextLine();

        while(usernameExist(username)){
            System.out.println("User already exist. Try a different one:");
            username= input.nextLine();
        }

        System.out.println("Please enter your specialty:");
        String specialty = input..nextLine();

        System.out.println("Please enter your password");
        String password = input.nextLine();

        Trainer t = new Trainer(name,username,password,specialty);
        traibers.add(t);

        System.out.printl("Trainer registered correctly");
    
            
    }

    public void registerMember(Scanner input){
        System.out.println("Please enter your name:");
        Srting name = input.nextLine();

        Sysem.out.println("Please enter a username:");
        String username = input.nextLine();

        while(usernameExist(username)){
            System.out.println("User already exist. Try a different one:");
            username= input.nextLine();
        }

        System.out.println("Please enter your password");
        String password = input.nextLine();

        Member m = new Member(name, username, password);
        members.add(m);
        System.out.printl("Member registered correctly");
    }

    public boolean usernameExist(String us){
        for(Trainer t: trainers){
            if(t.getUsername == us){
                return true;
            }
        }

        for(Member m: members){
            if( m.getUsername == us){
                return true;
            }
        }
        return false;
    }



    
}
