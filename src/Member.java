/**
 * The Member class represents a gym member within the GymSystem.
 * It stores basic account information as well as the member's
 * selected membership type.
 */

public class Member {

    //Attributes
    private String name;
    private String username;
    private String password;
    private String membershipType;
    private String specialty;
    
    /**
     * Constructs a new Member object with the given information.
     *
     * @param name            the member's full name
     * @param username        the member's chosen username
     * @param password        the password for the account
     * @param membershipType  the membership plan the member has selected
     */
    public Member(String name, String username, String password, String specialty) {
        // initialize attributes
        this.name=name;
        this.username= username;
        this.password = password;
        this.specialty = specialty;
    }

    //Getters & Setters

     /**
     * Returns the member's full name.
     * @return the name of the member
     */
    public String getName() {
        return name;
     }
    /**
     * Returns the member's username.
     * @return the username of the member
     */
    public String getUsername() {
        return username;
     }
    /**
     * Returns the member's membership type.
     * @return the membership type
     */
    public String getSpecialty(){
        return specialty;
    }
    
    
    public String getMembershipType() {
        return membershipType;
     }


    /**
     * Displays the member's information, including name,
     * username, and membership type.
     */
    public void displayInfo() {
        System.out.printl("Trainer Name: " + name);
        System.out.printl("Trainer Username: " + username);
        System.out.printl("Trainer specialty: " + specialty);
    
    }
}
