/**
 * The Member class represents a gym member within the GymSystem.
 * It stores basic account information as well as the member's
 * selected membership type.
 */

public class Member extends Person {

    // Attributes
    private String membershipType;

    /**
     * Constructs a new Member object with the given information.
     *
     * @param name           the member's full name
     * @param username       the member's chosen username
     * @param password       the password for the account
     * @param id             the id unique to member
     * @param membershipType the membership plan the member has selected
     */
    public Member(String name, String username, String password, int id, String membershipType) {
        super(name, username, password, id);
        this.membershipType = membershipType;
    }

    // Getter
    /**
     * Returns the member's membership type.
     * 
     * @return the membership type
     */
    public String getMembershipType() {
        return membershipType;
    }

    // Setter
    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    // method
    /**
     * Displays the member's information, including name,
     * username, and membership type.
     */
    @Override
    public String toString() {
        return "Member: " + name + " | Username: " + userName + " | Plan: " + membershipType;
    }
}
