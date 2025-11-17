import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


@Test
void testMember(){
  Member membertest = new Member("Mike", "MikeBeast998", "Mike'sGymPassword", "1", "Basic");
  assertEquals("Mike", membertest.getName());
  assertEquals("MikeBeast998", membertest.getUserName());
  assertEquals(10, membertest.getId());
  assertEquals("Basic", membertest.getMembershipType());
  assertTrue(membertest.checkCredentials("johnd", "pass123"));
}

@Test
void testTrainer(){
  Trainer trainertest = new Trainer("Haru Ur", "haru", "password123", 2, "Sprint");
  assertEquals("Sprint", trainertest.getSpeciality());
  trainertest.setSpecialty("Box");
  assertEquals("Box", trainertest.getSpeciality());
  assertTrue(trainertest.checkCredentials("haru", "pass123"));
}

@Test
void testAdmin(){
  GymSystem systemtest = new GymSystem();
  Admin admintest = new Admin("Admin User", "admin", "adminpass", 3, systemtest);
  assertEquals("Admin User", admin.getName());
  assertEquals("admin", admin.getUserName());
  assertTrue(admin.checkCredentials("admin", "adminpass"));
}

@Test
void testUsernameUnique(){
  Member membertest = new Member("Mike", "MikeBeast998", "Mike'sGymPassword", "1", "Basic");
  Trainer trainertest = new Trainer("Haru Ur", "haru", "password123", 2, "Sprint");
  GymSystem systemtest = new GymSystem();
  Admin admintest = new Admin("Admin User", "admin", "adminpass", 3, systemtest);
  systemtest.addMember(member);
  systemtest.addTrainer(trainer);
  systemtest.addAdmin(admin);

  assertTrue(system.isUsernameTaken("haru"));
  assertFalse(system.isUsernameTaken("lily"));
  assertTrue(system.isUsernameTaken("admin"));
  assertFalse(system.isUsernameTaken("mark"));
  assertTrue(system.isUsernameTaken("MikeBeast998"));
}
