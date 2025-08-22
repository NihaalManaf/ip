package exceptions;

public class MissingTodoArgumentException extends InvalidArugmentException {
    public MissingTodoArgumentException(String string) {
        super(string);
    }
}
