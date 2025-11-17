/**
 * The Login interface defines a contract for authentication within the system.
 *
 */
public interface Login {
    /**
     * Verifies whether the provided username and password match
     * the stored credentials for the implementing user type.
     *
     * @param username the username being checked
     * @param password the password being checked
     * @return {@code true} if the credentials match, {@code false} otherwise
     */
    boolean checkCredentials(String username, String password);
}
