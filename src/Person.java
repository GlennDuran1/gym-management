/**
 * The abstract Person class represents a generic user in the gym system.
 * 
 */
public abstract class Person implements Login {

    /** Full name of the person */
    protected String name;

    /** Unique username for login */
    protected String userName;

    /** Password for login authentication */
    protected String password;

    /** Unique identifier for the person */
    protected int id;

    /**
     * Constructs a new Person with the specified details.
     *
     * @param name     the full name of the person
     * @param userName the unique username
     * @param password the password for login
     * @param id       the unique ID of the person
     */
    public Person(String name, String userName, String password, int id) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.id = id;
    }

    /**
     * Checks if the provided username and password match this person's credentials.
     *
     * @param u the username to check
     * @param p the password to check
     * @return true if the credentials match, false otherwise
     */
    @Override
    public boolean checkCredentials(String u, String p) {
        return userName.equals(u) && password.equals(p);
    }

    /**
     * Returns the name of the person.
     *
     * @return the person's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the username of the person.
     *
     * @return the person's username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Returns the unique ID of the person.
     *
     * @return the person's ID
     */
    public int getId() {
        return id;
    }
    public String getPassword() {
    return password;
}

    /**
     * Sets a new name for the person.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets a new username for the person.
     *
     * @param userName the new username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Sets a new password for the person.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
