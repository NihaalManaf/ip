package optimusprime.exceptions;

/**
 * Exception thrown when a deadline task is missing required arguments.
 */
public class MissingDeadlineArgumentException extends InvalidArgumentException {
    public MissingDeadlineArgumentException(String string) {
        super(string);
    }
}
