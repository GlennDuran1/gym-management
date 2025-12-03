import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GymTestsPart2 {

    @Test
    void testMembershipPlan() {
        // Create a membership plan to test
        MembershipPlan plan = new MembershipPlan(
            1,
            "Premium",
            12,     // durationMonths
            49.99   // price
        );

        // Test attributes
        assertEquals(1, plan.getId());
        assertEquals("Premium", plan.getPlanName());
        assertEquals(12, plan.getDurationMonths());  // FIXED HERE
        assertEquals(49.99, plan.getPrice());

        // Modify attributes
        plan.setPlanName("Gold");
        plan.setDurationMonths(6);  // FIXED HERE
        plan.setPrice(29.99);

        // Check updated values
        assertEquals("Gold", plan.getPlanName());
        assertEquals(6, plan.getDurationMonths());  // FIXED HERE
        assertEquals(29.99, plan.getPrice());
    }

    @Test
    void testPlanToString() {
        MembershipPlan plan = new MembershipPlan(2, "Basic", 3, 19.99);

        String expected = "Plan ID: 2, Name: Basic, Duration: 3 months, Price: $19.99";
        assertEquals(expected, plan.toString());
    }
}
