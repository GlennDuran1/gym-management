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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
    }

    public double getPrice() {
        return price;
    }

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