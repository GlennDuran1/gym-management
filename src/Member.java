public class Member {

    // Attributes
    private String name;
    private String username;
    private String password;

    /**
     * Constructs a new Member object with the given information.
     *
     * @param name     the member's full name
     * @param username the member's chosen username
     * @param password the member's account password
     */
    public Member(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Displays the member's information.
     */
    public void displayInfo() {
        System.out.println("Member Name: " + name);
        System.out.println("Username: " + username);
    }
}
