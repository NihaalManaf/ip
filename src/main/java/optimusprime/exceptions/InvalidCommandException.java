package optimusprime.exceptions;

/**
 * Exception thrown when an invalid command is provided.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String name) {
        super(name);
    }
}
