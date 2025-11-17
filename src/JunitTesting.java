import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


@Test
void testMember{
  Member membertest = new Member("Mike", "MikeBeast998", "Mike'sGymPassword", "1", "Basic");
  assertEquals("Mike", membertest.getName());
  assertEquals("MikeBeast998", membertest.getUserName());
  assertEquals(10, membertest.getId());
  assertEquals("Basic", membertest.getMembershipType());
  assertTrue(membertest.checkCredentials("johnd", "pass123"));
}

@Test
void testTrainer{
  Trainer trainertest = new Trainer("Alice Dow", "alice", "password123", 2, "Yoga");
  assertEquals("Yoga", trainertest.getSpeciality());
  trainertest.setSpecialty("Box");
  assertEquals("Box", trainertest.getSpeciality());
  assertTrue(trainertest.checkCredentials("alice", "pass123"));
}

@Test
void testAdmin{
   
}
