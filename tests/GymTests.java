import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GymTests {

    @Test
    void testMember() {
        Member membertest = new Member("Mike", "MikeBeast998", "Mike'sGymPassword", 1, "Basic");

        assertEquals("Mike", membertest.getName());
        assertEquals("MikeBeast998", membertest.getUserName());
        assertEquals(1, membertest.getId());
        assertEquals("Basic", membertest.getMembershipType());

        // correct credentials
        assertTrue(membertest.checkCredentials("MikeBeast998", "Mike'sGymPassword"));
    }

    @Test
    void testTrainer() {
        Trainer trainertest = new Trainer("Haru Ur", "haru", "password123", 2, "Sprint");

        assertEquals("Sprint", trainertest.getSpecialty());

        trainertest.setSpecialty("Box");
        assertEquals("Box", trainertest.getSpecialty());

        assertTrue(trainertest.checkCredentials("haru", "password123"));
    }

    @Test
    void testAdmin() {
        GymSystem systemtest = new GymSystem();
        Admin admintest = new Admin("Admin User", "admin", "adminpass", 3, systemtest);

        assertEquals("Admin User", admintest.getName());
        assertEquals("admin", admintest.getUserName());
        assertTrue(admintest.checkCredentials("admin", "adminpass"));
    }

    @Test
    void testUsernameUnique() {
        GymSystem systemtest = new GymSystem();

        Member membertest = new Member("Mike", "MikeBeast998", "Mike'sGymPassword", 1, "Basic");
        Trainer trainertest = new Trainer("Haru Ur", "haru", "password123", 2, "Sprint");
        Admin admintest = new Admin("Admin User", "admin", "adminpass", 3, systemtest);

        systemtest.addMember(membertest);
        systemtest.addTrainer(trainertest);
        systemtest.addAdmin(admintest);

        assertTrue(systemtest.isUsernameTaken("haru"));
        assertFalse(systemtest.isUsernameTaken("lily"));
        assertTrue(systemtest.isUsernameTaken("admin"));
        assertFalse(systemtest.isUsernameTaken("mark"));
        assertTrue(systemtest.isUsernameTaken("MikeBeast998"));
    }
}
