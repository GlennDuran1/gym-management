import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * CSVHandler is responsible for reading CSV files and loading
 * Members, Trainers, Admins, Workout Sessions, and Membership Plans
 * into the GymSystem.
 */
public class CSVHandler {

    /**
     * Loads users from GymUsersData.csv and inserts them into GymSystem.
     * 
     * Columns:
     * ID,First Name,Last Name,Username,Password,User Type,Membership,Start Date,End
     * Date,Speciality
     */
    public static void loadUsers(String filePath, GymSystem system) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {

                // Split only on commas, but allow extra commas
                String[] raw = line.split(",");

                // We expect at least 6 columns: ID, First, Last, Username, Password, Type
                if (raw.length < 6)
                    continue;

                int id = Integer.parseInt(raw[0].trim());

                String firstName = raw[1].trim();
                String lastName = raw[2].trim();

                String username = raw[3].trim();
                String password = raw[4].trim();
                String userType = raw[5]
                        .replace("\uFEFF", "")
                        .replace("\r", "")
                        .trim();

                // Safe read remaining columns (may be empty or missing)
                String membership = (raw.length > 6 ? raw[6].trim() : "");
                String startDate = (raw.length > 7 ? raw[7].trim() : "");
                String endDate = (raw.length > 8 ? raw[8].trim() : "");
                String speciality = (raw.length > 9 ? raw[9].trim() : "");

                String fullName = firstName + " " + lastName;

                switch (userType) {
                    case "Member":
                        system.addMember(new Member(fullName, username, password, id, membership));
                        Log.write("Member: " + username + " loaded in the system");
                        break;

                    case "Trainer":
                        system.addTrainer(new Trainer(fullName, username, password, id, speciality));
                        Log.write("Trainer: " + username + " loaded in the system");
                        break;

                    case "Admin":
                        system.addAdmin(new Admin(fullName, username, password, id, system));
                        Log.write("Admin: " + username + " loaded in the system");
                        break;

                    default:
                        System.out.println("Unknown user type: " + userType);
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading users CSV: " + e.getMessage());
        }
    }

    /**
     * Loads sessions from GymSessions.csv
     * Columns:
     * SessionID,SessionName,Date
     */
    public static void loadSessions(String filePath, GymSystem system) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length < 3)
                    continue;

                int sessionID = Integer.parseInt(data[0]);
                String sessionName = data[1];
                String date = data[2];

                WorkoutSession ws = new WorkoutSession(sessionID, sessionName, date);
                system.addSession(ws);
            }

        } catch (IOException e) {
            System.out.println("Error reading sessions CSV: " + e.getMessage());
        }
    }

    /**
     * Loads membership plans from GymPlans.csv
     * Column:
     * PlanName
     */
    public static void loadPlans(String filePath, GymSystem system) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty())
                    continue;

                MembershipPlan p = new MembershipPlan(line.trim());
                system.addPlan(p);
            }

        } catch (IOException e) {
            System.out.println("Error reading plans CSV: " + e.getMessage());
        }
    }
}
