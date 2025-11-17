/**
 * The Admin class represents an administrator in the system.
 * It stores login credentials and provides authentication functionality.
 */

public class Admin extends Person{
 private GymSystem system;

/**
     * Constructs a new Admin.
     *
     * @param name      the admin's full name
     * @param userName  the admin's username
     * @param password  the admin's password
     * @param id        unique admin ID
     * @param system    reference to the GymSystem this admin manages
     */
    public Admin(String name, String userName, String password, int id, GymSystem system) {
        super(name, userName, password, id);
        this.system = system;
    }
    //Getters & Setters
    /**
    *this method should add user
    *@param p the person to add
    */
    public void addUser(Person p) {
        if (p instanceof Member) {
            system.addMember((Member)p);
        } else if (p instanceof Trainer) {
            system.addTrainer((Trainer)p);
        } else if (p instanceof Admin) {
            system.addAdmin((Admin)p);
        }
    }
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
    public void addTrainer(Trainer trainer) {
         system.addTrainer(trainer);
    }
    public void deleteTrainer(int trainerId) {
        Trainer t = system.searchTrainer(String.valueOf(trainerId));
        if (t != null) {
            system.deleteTrainer(t);
        }
    }
    public void viewSessions() {
       system.displayAllSessions();
    }
     public void manageMembershipPlan() {
        system.displayAllPlans();
    }
    @Override
    public String toString() {
        return "Admin: " + name + " | Username: " + userName;
    }
}
