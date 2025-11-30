/**
 * The MembershipPlan class represents a subscription plan in the gym system.
 * Each plan includes an ID, name, duration in months, and a price.
 */
public class MembershipPlan {

    private int id;
    private String planName;
    private int durationMonths;
    private double price;

    /**
     * Constructs a membership plan with all attributes.
     * 
     * @param id             unique identifier for the plan
     * @param planName       name of the membership plan
     * @param durationMonths duration in months
     * @param price          price of the plan
     */
    public MembershipPlan(int id, String planName, int durationMonths, double price) {
        this.id = id;
        this.planName = planName;
        this.durationMonths = durationMonths;
        this.price = price;
    }

    // -------- GETTERS & SETTERS --------

    /**
     * Returns the unique ID of the membership plan.
     *
     * @return the plan ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique ID for the membership plan.
     *
     * @param id the new plan ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the membership plan.
     *
     * @return the plan name
     */
    public String getPlanName() {
        return planName;
    }

    /**
     * Updates the name of the membership plan.
     *
     * @param planName the new name of the plan
     */
    public void setPlanName(String planName) {
        this.planName = planName;
    }

    /**
     * Returns the duration of the membership plan in months.
     *
     * @return number of months the plan lasts
     */
    public int getDurationMonths() {
        return durationMonths;
    }

    /**
     * Sets the duration of the membership plan in months.
     *
     * @param durationMonths the new duration for the plan
     */
    public void setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
    }

    /**
     * Returns the price of the membership plan.
     *
     * @return the plan price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Updates the price of the membership plan.
     *
     * @param price the new price of the plan
     */
    public void setPrice(double price) {
        this.price = price;
    }

    // -------- TO STRING --------

    @Override
    public String toString() {
        return "Plan ID: " + id +
                ", Name: " + planName +
                ", Duration: " + durationMonths + " months" +
                ", Price: $" + price;
    }
}