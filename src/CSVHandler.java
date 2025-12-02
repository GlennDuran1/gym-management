import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.HashMap;

/**
 * CSVHandler is responsible for reading CSV files and loading
 * Members, Trainers, Admins, Workout Sessions, and Membership Plans
 * into the GymSystem.
 */

public class CSVHandler {
/**
 * Builds a column index map from a CSV header row.
 * Allows all CSV loading to work even when columns are in different orders.
 *
 * @param headerRow the header row split by commas
 * @return a map where keys = header names, value = column index
 */
private static HashMap<String, Integer> buildColumnMap(String[] headerRow) {
    HashMap<String, Integer> map = new HashMap<>();
    for (int i = 0; i < headerRow.length; i++) {
        map.put(headerRow[i].trim().toLowerCase(), i);
    }
    return map;
}
// Safe getter to avoid null and index-out-of-bounds
private static String getValue(HashMap<String, Integer> map, String key, String[] row) {
    Integer idx = map.get(key);
    if (idx == null) return "";
    if (idx < 0 || idx >= row.length) return "";
    return row[idx].trim();
}

    /**
     * Loads users from GymUsersData.csv and inserts them into GymSystem.
     * 
     * Columns can be in ANY order now.
     * Required column names: id, first name, last name, username, password, user type
     */
    public static void loadUsers(String filePath, GymSystem system) {
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

        String header = br.readLine();
        if (header == null) return;

        String[] columns = header.split(",");
        HashMap<String, Integer> map = buildColumnMap(columns);

        String line;
        while ((line = br.readLine()) != null) {

            if (line.trim().isEmpty()) continue;

            // KEEP EMPTY COLUMNS
            String[] raw = line.split(",", -1);

            int id = Integer.parseInt(getValue(map, "id", raw));
            String firstName = getValue(map, "first name", raw);
            String lastName = getValue(map, "last name", raw);
            String username = getValue(map, "username", raw);
            String password = getValue(map, "password", raw);
            String userType = getValue(map, "user type", raw).toLowerCase();

            String membership = getValue(map, "membership", raw);
            String startDate = getValue(map, "start date", raw);
            String endDate = getValue(map, "end date", raw);
            String speciality = getValue(map, "speciality", raw);

            String fullName = firstName + " " + lastName;

            switch (userType) {

                case "member":
                    system.addMember(
                        new Member(fullName, username, password, id, membership)
                    );
                    break;

                case "trainer":
                    system.addTrainer(
                        new Trainer(fullName, username, password, id, speciality)
                    );
                    break;

                case "admin":
                    system.addAdmin(
                        new Admin(fullName, username, password, id, system)
                    );
                    break;

                default:
                    System.out.println("Unknown user type: " + userType);
            }
        }

    } catch (Exception e) {
        System.out.println("Error reading users CSV: " + e.getMessage());
        e.printStackTrace();
    }
}



    /**
     * Loads sessions from GymSessions.csv
     * Columns:
     * SessionID,SessionName,Date,StartTime,EndTime,TrainerID
     */
    public static void loadSessions(String filePath, GymSystem system) {

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

        String header = br.readLine();
        if (header == null) return;

        String[] columns = header.split(",");
        HashMap<String, Integer> map = buildColumnMap(columns);

        String line;
        while ((line = br.readLine()) != null) {

            if (line.trim().isEmpty()) continue;

            String[] data = line.split(",", -1);

            int sessionID = Integer.parseInt(data[map.get("id")]);
            String type = data[map.get("type")];
            int capacity = Integer.parseInt(data[map.get("capacity")]);
            String date = data[map.get("date")];
            String startTime = data[map.get("start time")];
            String endTime = data[map.get("end time")];
            int trainerID = Integer.parseInt(data[map.get("trainer id")]);

            Trainer trainer = system.findTrainerById(trainerID);

            WorkoutSession ws = new WorkoutSession(
                sessionID, type, date, startTime, endTime, capacity, trainer
            );

            system.addSession(ws);
        }

    } catch (Exception e) {
        System.out.println("Error reading sessions CSV: " + e.getMessage());
        e.printStackTrace();
    }
}

    public static void saveToFile(Member member, List<Member> members) {

        try (FileWriter writer = new FileWriter("data/Members.csv")) {

            writer.write("ID,First Name,Last Name,Username,Password,User Type,Membership,Start Date,End Date\n");

            for (Member m : members) {

                String[] nameParts = m.getName().split(" ", 2);
                String firstName = nameParts[0];
                String lastName = nameParts.length > 1 ? nameParts[1] : "";

                writer.write(
                        m.getId() + "," +
                                firstName + "," +
                                lastName + "," +
                                m.getUserName() + "," +
                                m.getPassword() + "," +
                                "Member" + "," +
                                m.getMembershipType() + "," +
                                "" + "\n");
            }

        } catch (IOException e) {
            System.out.println("Error saving members CSV: " + e.getMessage());
        }
    }

    /**
     * Saves all session enrollment progress to progress.csv.
     * Each row contains: memberID, sessionID.
     *
     * @param progressList the list of enrollment records
     */
    public static void saveProgress(List<String[]> progressList) {
        try (FileWriter fw = new FileWriter("data/progress.csv")) {

            fw.write("memberId,sessionId\n");

            for (String[] entry : progressList) {
                fw.write(entry[0] + "," + entry[1] + "\n");
            }

        } catch (IOException e) {
            System.out.println("Error writing progress.csv: " + e.getMessage());
        }
    }

    /**
 * Loads progress.csv containing memberID → sessionID enrollments.
 *
 * This method restores both:
 * 1. The raw progress list (for saving back to CSV)
 * 2. The actual enrolledMembers inside each WorkoutSession object
 *
 * @param filePath path to progress.csv
 * @param system   reference to GymSystem to store progress and rebuild links
 */
public static void loadProgress(String filePath, GymSystem system) {
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

        String header = br.readLine(); // skip header
        if (header == null) return;

        String line;
        while ((line = br.readLine()) != null) {

            String[] parts = line.split(",");
            if (parts.length < 2) continue;

            int memberId = Integer.parseInt(parts[0].trim());
            int sessionId = Integer.parseInt(parts[1].trim());

            // Store raw record (for CSV saving)
            system.addProgressRecord(memberId, sessionId);

            // Rebuild in-memory enrollment
            Member m = system.searchMember(String.valueOf(memberId));
            WorkoutSession s = system.searchSession(String.valueOf(sessionId));

            if (m != null && s != null) {
                s.enrollMember(m);   // restores enrollment for trainer/member menus
            }
        }

    } catch (IOException e) {
        System.out.println("Error loading progress.csv: " + e.getMessage());
    }
}
public static void loadPlans(String filePath, GymSystem system) {

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

        String header = br.readLine();
        if (header == null) return;

        String[] columns = header.split(",");
        HashMap<String, Integer> map = buildColumnMap(columns);

        String line;
        while ((line = br.readLine()) != null) {

            if (line.trim().isEmpty()) continue;

            String[] data = line.split(",", -1);

            int id = Integer.parseInt(data[map.get("id")].trim());
            String planName = data[map.get("plan name")].trim();
            int duration = Integer.parseInt(data[map.get("duration in months")].trim());
            double price = Double.parseDouble(data[map.get("price")].trim());

            MembershipPlan plan = new MembershipPlan(id, planName, duration, price);
            system.addPlan(plan);
        }

    } catch (Exception e) {
        System.out.println("Error loading plans CSV: " + e.getMessage());
        e.printStackTrace();
    }
}


}
