import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Test suite for Gym Management System classes.
 */
public class JunitTesing {

    /**
     * Tests that a Member object is created correctly,
     * including getters, setters, and credential check.
     */
    @Test
    void testMember() {
        Member memberTest = new Member("Mike", "MikeBeast998", "pass123", 1, "Basic");

        assertEquals("Mike", memberTest.getName());
        assertEquals("MikeBeast998", memberTest.getUserName());
        assertEquals(1, memberTest.getId());
        assertEquals("Basic", memberTest.getMembershipType());
        assertTrue(memberTest.checkCredentials("MikeBeast998", "pass123"));

        memberTest.setMembershipType("Premium");
        assertEquals("Premium", memberTest.getMembershipType());
    }

    /**
     * Tests that a Trainer object is created correctly,
     * including getters, setters, and credential check.
     */
    @Test
    void testTrainer() {
        Trainer trainerTest = new Trainer("Haru Ur", "haru", "pass123", 2, "Sprint");

        assertEquals("Sprint", trainerTest.getSpecialty());
        trainerTest.setSpecialty("Box");
        assertEquals("Box", trainerTest.getSpecialty());

        assertTrue(trainerTest.checkCredentials("haru", "pass123"));
    }

    /**
     * Tests that an Admin object is created correctly
     * and credential check works.
     */
    @Test
    void testAdmin() {
        GymSystem systemTest = new GymSystem();
        Admin adminTest = new Admin("Admin User", "admin", "adminpass", 3, systemTest);

        assertEquals("Admin User", adminTest.getName());
        assertEquals("admin", adminTest.getUserName());
        assertTrue(adminTest.checkCredentials("admin", "adminpass"));
    }

    /**
     * Tests that GymSystem correctly identifies unique usernames
     * among members, trainers, and admins.
     */
    @Test
    void testUsernameUnique() {
        Member memberTest = new Member("Mike", "MikeBeast998", "pass123", 1, "Basic");
        Trainer trainerTest = new Trainer("Haru Ur", "haru", "pass123", 2, "Sprint");
        GymSystem systemTest = new GymSystem();
        Admin adminTest = new Admin("Admin User", "admin", "adminpass", 3, systemTest);

        systemTest.addMember(memberTest);
        systemTest.addTrainer(trainerTest);
        systemTest.addAdmin(adminTest);

        assertTrue(systemTest.isUsernameTaken("haru"));
        assertFalse(systemTest.isUsernameTaken("lily"));
        assertTrue(systemTest.isUsernameTaken("admin"));
        assertFalse(systemTest.isUsernameTaken("mark"));
        assertTrue(systemTest.isUsernameTaken("MikeBeast998"));
    }
}
