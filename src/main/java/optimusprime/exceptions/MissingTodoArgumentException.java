package optimusprime.exceptions;

/**
 * Exception thrown when a todo task is missing required arguments.
 */
public class MissingTodoArgumentException extends InvalidArgumentException {
    public MissingTodoArgumentException(String string) {
        super(string);
    }
}
