public class Trainer {

    // Attributes
    private String name;
    private String username;
    private String password;
    private String specialty;

    /**
     * Constructs a new Trainer with full info.
     *
     * @param name      the trainer's full name
     * @param username  the trainer's unique username
     * @param password  the trainer's password
     * @param specialty the trainer's area of specialization
     */
    public Trainer(String name, String username, String password, String specialty) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.specialty = specialty;
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

    public String getSpecialty() {
        return specialty;
    }

    /**
     * Display trainer's information.
     */
    public void displayInfo() {
        System.out.println("Trainer Name: " + name);
        System.out.println("Username: " + username);
        System.out.println("Specialty: " + specialty);
    }
}
