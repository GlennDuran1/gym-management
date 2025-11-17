import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Log class provides a simple logging mechanism for the GymSystem.
 * 
 */
public class Log {

    /** Path to the log file where all activity entries are stored. */
    private static final String LOG_FILE = "data/log.txt";

    /** Timestamp format used for each log entry. (Example: 14:05:21 11/16/24) */
    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("HH:mm:ss MM/dd/yy");

    /**
     * Writes a log entry to the log file.
     *
     * @param action a descriptive message of the action being logged
     */
    public static void write(String action) {
        try {
            FileWriter writer = new FileWriter(LOG_FILE, true);

            String timestamp = LocalDateTime.now().format(FORMAT);
            writer.write(timestamp + " - " + action + "\n");

            writer.close();

        } catch (IOException e) {
            System.out.println("Error writing to log file.");
        }
    }
}
