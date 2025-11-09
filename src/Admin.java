public class Admin {

    //Attributes
    private String name;
    private String username;
    private String password;

    //Constructor
    public Admin(String name, String username, String password) {
      
    }

    //Getters & Setters
    public String getName() {
         return name;
    }

    public boolean login(String user, String pass) {
         return username.equals(user) && password.equals(pass);
    }

    // public void viewReports()
    // public void deleteMember(Member m)
}
