package optimusprime.exceptions;

/**
 * Exception thrown when invalid delete arguments are provided.
 */
public class InvalidDeleteArgumentException extends InvalidArgumentException {
    public InvalidDeleteArgumentException(String name) {
        super(name);
    }
}
