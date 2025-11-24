/**
 * The Admin class represents an administrator in the GymSystem.
 * Admins have elevated privileges such as adding, deleting, and managing
 * Members, Trainers, and other Admins.
 *
 * <p>
 * This class extends Person, inheriting general user information such as
 * name, username, password, and ID.
 * </p>
 */
public class Admin extends Person {

    /** Reference to the GymSystem this admin interacts with. */
    private GymSystem system;

    /**
     * Constructs a new Admin with personal details and a reference to the system.
     *
     * @param name     the admin's full name
     * @param userName the admin's username
     * @param password the admin's password
     * @param id       unique numeric admin ID
     * @param system   the GymSystem instance this admin manages
     */
    public Admin(String name, String userName, String password, int id, GymSystem system) {
        super(name, userName, password, id);
        this.system = system;
    }

    /**
     * Adds a new user to the GymSystem.
     * Determines the appropriate category (Member, Trainer, or Admin)
     * and adds the user accordingly.
     *
     * @param p the Person object to add to the system
     */
    public void addUser(Person p) {
        if (p instanceof Member) {
            system.addMember((Member) p);
        } else if (p instanceof Trainer) {
            system.addTrainer((Trainer) p);
        } else if (p instanceof Admin) {
            system.addAdmin((Admin) p);
        }
    }

    /**
     * Deletes a user from the system by searching for their ID.
     *
     * @param id the unique ID of the user to delete
     */
    public void deleteUser(int id) {

        // Try to delete a member
        Member m = system.searchMember(String.valueOf(id));
        if (m != null) {
            system.deleteMember(m);
            return;
        }

        // Try to delete a trainer
        Trainer t = system.searchTrainer(String.valueOf(id));
        if (t != null) {
            system.deleteTrainer(t);
            return;
        }

        // Try to delete an admin
        Admin a = system.searchAdmin(String.valueOf(id));
        if (a != null) {
            system.deleteAdmin(a);
        }
    }

    /**
     * Adds a trainer to the GymSystem.
     *
     * @param trainer the Trainer object to be added
     */
    public void addTrainer(Trainer trainer) {
        system.addTrainer(trainer);
    }

    /**
     * Deletes a trainer from the system based on trainer ID.
     *
     * @param trainerId the ID of the trainer to delete
     */
    public void deleteTrainer(int trainerId) {
        Trainer t = system.searchTrainer(String.valueOf(trainerId));
        if (t != null) {
            system.deleteTrainer(t);
        }
    }

    /**
     * Displays all workout sessions currently stored in the system.
     */
    public void viewSessions() {
        system.displayAllSessions();
    }

    /**
     * Displays all membership plans in the GymSystem.
     */
    public void manageMembershipPlan() {
        system.displayAllPlans();
    }

    /**
     * Returns a string representation of this admin,
     * showing only name and username for security reasons.
     *
     * @return a formatted string with admin details
     */
    @Override
    public String toString() {
        return "Admin: " + name + " | Username: " + userName;
    }
}
