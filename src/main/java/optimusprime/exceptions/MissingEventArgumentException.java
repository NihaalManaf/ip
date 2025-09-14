package optimusprime.exceptions;

/**
 * Exception thrown when an event task is missing required arguments.
 */
public class MissingEventArgumentException extends InvalidArgumentException {
    public MissingEventArgumentException(String string) {
        super(string);
    }
}
