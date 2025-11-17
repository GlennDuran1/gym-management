import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

@Test
void createMember{
  Member membertest = new Member("Mike", "MikeBeast998", "Mike'sGymPassword", "1", "Basic");
  assertEquals("Mike", membertest.getName());
  assertEquals("MikeBeast998", membertest.getUserName());
  assertEquals(10, membertest.getId());
  
}

