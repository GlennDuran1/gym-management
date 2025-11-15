/**
 * The Admin class represents an administrator in the system.
 * It stores login credentials and provides authentication functionality.
 */

public class Admin {

    //Attributes
    private String name;
    private String username;
    private String password;


    /**
    *constructs a new admin object with specific attributes
    *@param name
    *@param username
    *@param password
    */
    public Admin(String name, String username, String password) {
      
    }

    //Getters & Setters
    /**
    *this method should return the admin's name
    *@return the admin's name
    */
    public String getName() {
         return name;
    }
    
    /**
    *this method should verify credentials
    *@param user
    *@param pass
    *@return true if credentials match
    */
    public boolean login(String user, String pass) {
         return username.equals(user) && password.equals(pass);
    }

    // public void viewReports()
    // public void deleteMember(Member m)
}
