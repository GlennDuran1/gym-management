/**
 * Exception thrown when a CSV file does not match the expected format.
 * <p>
 * This can be used to indicate issues such as missing required columns,
 * incorrect data layout, or invalid field values encountered while parsing
 * CSV data.
 * </p>
 */

public class InvalidCSVFormatException extends Exception {

    /**
     * Creates a new InvalidCSVFormatException with the specified
     * detail message.
     *
     * @param message a description of the formatting error encountered
     */
    public InvalidCSVFormatException(String message) {
        super(message);
    }
}
