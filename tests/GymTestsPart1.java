import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GymTestsPart1 {

    @Test
    void testMember() {
        Member memberTest = new Member("Mike", "MikeBeast998", "pass123", 1, "Basic");

        assertEquals("Mike", memberTest.getName());
        assertEquals("MikeBeast998", memberTest.getUserName());
        assertEquals(1, memberTest.getId());
        assertEquals("Basic", memberTest.getMembershipType());
        assertTrue(memberTest.checkCredentials("MikeBeast998", "pass123"));
    }

    @Test
    void testTrainer() {
        Trainer trainerTest = new Trainer("Haru Ur", "haru", "pass123", 2, "Sprint");

        assertEquals("Sprint", trainerTest.getSpecialty());
        trainerTest.setSpecialty("Box");
        assertEquals("Box", trainerTest.getSpecialty());

        assertTrue(trainerTest.checkCredentials("haru", "pass123"));
    }

    @Test
    void testAdmin() {
        GymSystem systemTest = new GymSystem();
        Admin adminTest = new Admin("Admin User", "admin", "adminpass", 3, systemTest);

        assertEquals("Admin User", adminTest.getName());
        assertEquals("admin", adminTest.getUserName());
        assertTrue(adminTest.checkCredentials("admin", "adminpass"));
    }
}
