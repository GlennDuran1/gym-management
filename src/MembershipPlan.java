/**
 * The MembershipPlan class represents a gym membership plan.
 * For Part A, it only stores a name.
 */
public class MembershipPlan {

    private String name;

    /**
     * Constructs a MembershipPlan with the given name.
     *
     * @param name the name of the membership plan
     */
    public MembershipPlan(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the membership plan.
     *
     * @return the plan name
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Plan: " + name;
    }
}
