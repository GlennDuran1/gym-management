import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

    private static final String LOG_FILE = "../data/log.txt";

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss MM/dd/yy");

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