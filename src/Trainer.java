/**
 * The Trainer class represents a fitness trainer in the GymSystem.
 * Each trainer has a name and a specialty area they focus on.
 */

public class Trainer extends Person {
    private String specialty;

    /**
     * Constructs a new Trainer with the inherited name and specialty.
     *
     * @param name      the trainer's full name
     * @param userName  the trainers username
     * @param password  the trainer's password
     * @param id        the trainer's id
     * @param specialty the trainer's area of specialization
     */
    public Trainer(String name, String userName, String password, int id, String specialty) {
        super(name, userName, password, id);
        this.specialty = specialty;
    }

    // Getter
    /**
     * Returns the area of expertise of the trainer.
     * 
     * @return the trainer's specialty
     */
    public String getSpecialty() {
        return specialty;
    }

    // setter
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setTrainerUsername(String newUsername) {
        super.setUserName(newUsername);
    }

    /**
     * Displays the trainer's information, including
     * name and area of specialty.
     */
    @Override
    public String toString() {
        return "Trainer: " + name + " | Username: " + userName + " | Specialty: " + specialty;
    }
}
