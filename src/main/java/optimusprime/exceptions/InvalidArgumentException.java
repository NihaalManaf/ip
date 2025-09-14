package optimusprime.exceptions;

/**
 * Exception thrown when invalid arguments are provided.
 */
public class InvalidArgumentException extends Exception {
    public InvalidArgumentException(String name) {
        super(name);
    }
}
